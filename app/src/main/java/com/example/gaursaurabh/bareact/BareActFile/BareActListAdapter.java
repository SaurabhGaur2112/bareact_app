package com.example.gaursaurabh.bareact.BareActFile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaursaurabh.bareact.Chapters;
import com.example.gaursaurabh.bareact.R;

import java.util.List;

/**
 * Created by Saurabh Gaur on 9/22/2017.
 */

public class BareActListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<BareActFeedItem> bareActFeedItems;

    public BareActListAdapter(Activity activity, List<BareActFeedItem> bareActFeedItems){
        this.activity = activity;
        this.bareActFeedItems = bareActFeedItems;
    }


    @Override
    public int getCount() {
        return bareActFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return bareActFeedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
            convertView = inflater.inflate(R.layout.list_bareact, null);

        CardView bareactCard = (CardView) convertView.findViewById(R.id.bareActCard);
        TextView bareactText = (TextView) convertView.findViewById(R.id.bareActTxt);

        final BareActFeedItem item = bareActFeedItems.get(position);

        bareactText.setText(item.getBareact());
        bareactCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, Chapters.class);
                i.putExtra("bareact_id",item.getId());
                activity.startActivity(i);
            }
        });
        return convertView;
    }
}
