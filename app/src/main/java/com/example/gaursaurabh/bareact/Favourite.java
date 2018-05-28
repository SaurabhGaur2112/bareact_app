package com.example.gaursaurabh.bareact;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gaursaurabh.bareact.CacheFile.AppController;
import com.example.gaursaurabh.bareact.FavouriteFile.FavouriteFeedItem;
import com.example.gaursaurabh.bareact.FavouriteFile.FavouriteListAdapter;
import com.example.gaursaurabh.bareact.SQLite.DBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Favourite extends AppCompatActivity {

    DBHelper db;
    String[] dbValue;
    int arraySize;
    String favValue = "";
    private static final String TAG = Favourite.class.getSimpleName();
    private ListView listView;
    private FavouriteListAdapter listAdapter;
    private List<FavouriteFeedItem> favouriteFeedItems;
    private String URL_FEED = "http://wallnit.com/bareactjson/favourites_list.php?section_data=";
    ProgressBar progressBar;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        listView = (ListView) findViewById(R.id.favouriteList);
        progressBar = (ProgressBar) findViewById(R.id.favouriteProgress);

        favouriteFeedItems = new ArrayList<FavouriteFeedItem>();

        listAdapter = new FavouriteListAdapter(this,favouriteFeedItems);
        listView.setAdapter(listAdapter);

        db = new DBHelper(this);
        dbValue = db.getFavourites();
        arraySize = dbValue.length;

        for(int i=0; i < arraySize ; i++){
            favValue += dbValue[i]+',';
        }

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(favValue), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, "Response: " + response.toString());
                if(response != null){
                    progressBar.setVisibility(View.GONE);
                    parseJsonFeed(response);
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error:    " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(jsonReq);
    }

    private void parseJsonFeed(JSONObject response){
        try{
            JSONArray feedArray = response.getJSONArray("feed");

            for(int i=0; i<feedArray.length(); i++){
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                FavouriteFeedItem item = new FavouriteFeedItem();
                item.setFavourite_title(feedObj.getString("title"));
                item.setFavourite_desc(feedObj.getString("description"));
                item.setFavourite_num(feedObj.getString("number"));
                item.setFavourite_id(feedObj.getString("id"));

                favouriteFeedItems.add(item);
            }
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
