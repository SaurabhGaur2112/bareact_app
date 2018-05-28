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
import com.example.gaursaurabh.bareact.ChapterFIle.ChapterFeedItem;
import com.example.gaursaurabh.bareact.ChapterFIle.ChapterListAdapter;
import com.example.gaursaurabh.bareact.CompareChapterFile.CompareChapterListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CompareChapter extends AppCompatActivity {

    String bareactId;
    private static final String TAG = CompareChapter.class.getSimpleName();
    private ListView listView;
    private CompareChapterListAdapter listAdapter;
    private List<ChapterFeedItem> chapterFeedItems;
    private String URL_FEED = "http://wallnit.com/bareactjson/chapter_list.php?bareact_id=";
    ProgressBar progressBar;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_chapter);

        listView = (ListView) findViewById(R.id.compareChapterList);
        progressBar = (ProgressBar) findViewById(R.id.compareChapterProgress);

        chapterFeedItems = new ArrayList<ChapterFeedItem>();

        listAdapter = new CompareChapterListAdapter(this, chapterFeedItems);
        listView.setAdapter(listAdapter);

        Intent bareact = getIntent();
        bareactId = bareact.getStringExtra("bareact_id");

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(bareactId), null, new Response.Listener<JSONObject>() {
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

                ChapterFeedItem item = new ChapterFeedItem();
                item.setChapter_id(feedObj.getString("chapter_id"));
                item.setChapter_num(feedObj.getString("chapter_number"));
                item.setTitle(feedObj.getString("chapter_title"));
                item.setSection_num(feedObj.getString("section_number"));

                chapterFeedItems.add(item);
            }
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
