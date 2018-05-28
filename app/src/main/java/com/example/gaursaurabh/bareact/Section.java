package com.example.gaursaurabh.bareact;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.gaursaurabh.bareact.CacheFile.AppController;
import com.example.gaursaurabh.bareact.SectionFile.SectionFeedItem;
import com.example.gaursaurabh.bareact.SectionFile.SectionListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Section extends AppCompatActivity {

    String chapterId;
    private static final String TAG = Section.class.getSimpleName();
    private ListView listView;
    private SectionListAdapter listAdapter;
    private List<SectionFeedItem> sectionFeedItems;
    private String URL_FEED = "http://wallnit.com/bareactjson/section_list.php?chapter_id=";
    ProgressBar progressBar;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        listView = (ListView) findViewById(R.id.sectionList);
        progressBar = (ProgressBar) findViewById(R.id.sectionProgress);

        sectionFeedItems = new ArrayList<SectionFeedItem>();

        listAdapter = new SectionListAdapter(this, sectionFeedItems);
        listView.setAdapter(listAdapter);

        Intent chapter = getIntent();
        chapterId = chapter.getStringExtra("chapter_id");

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(chapterId), null, new Response.Listener<JSONObject>() {
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

                SectionFeedItem item = new SectionFeedItem();
                item.setSection_id(feedObj.getString("section_id"));
                item.setSection_number(feedObj.getString("section_number"));
                item.setSection_title(feedObj.getString("section_title"));
                item.setSection_desc(feedObj.getString("section_desc"));

                sectionFeedItems.add(item);
            }

            listAdapter.notifyDataSetChanged();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
