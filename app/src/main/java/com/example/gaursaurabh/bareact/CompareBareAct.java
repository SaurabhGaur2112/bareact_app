package com.example.gaursaurabh.bareact;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gaursaurabh.bareact.BareActFile.BareActFeedItem;
import com.example.gaursaurabh.bareact.CacheFile.AppController;
import com.example.gaursaurabh.bareact.CompareBareActFile.CompareBareActListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CompareBareAct extends AppCompatActivity {

    private static final String TAG = CompareBareAct.class.getSimpleName();
    private ListView listView;
    private CompareBareActListAdapter listAdapter;
    private List<BareActFeedItem> bareActFeedItems;
    private String URL_FEED = "http://wallnit.com/bareactjson/bareact_list.php";
    ProgressBar progressBar;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_bare);

        listView = (ListView) findViewById(R.id.compareBareActList);
        progressBar = (ProgressBar) findViewById(R.id.compareBareActProgress);

        bareActFeedItems = new ArrayList<BareActFeedItem>();

        listAdapter = new CompareBareActListAdapter(this, bareActFeedItems);
        listView.setAdapter(listAdapter);

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED, null, new Response.Listener<JSONObject>() {
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

            for (int i=0; i<feedArray.length(); i++){
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                BareActFeedItem item = new BareActFeedItem();
                item.setId(feedObj.getString("bareact_id"));
                item.setBareact(feedObj.getString("bareact_name"));

                bareActFeedItems.add(item);
            }
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
