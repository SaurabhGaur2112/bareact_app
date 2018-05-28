package com.example.gaursaurabh.bareact.CompareSectionFile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gaursaurabh.bareact.Compare;
import com.example.gaursaurabh.bareact.R;
import com.example.gaursaurabh.bareact.SectionDesc;
import com.example.gaursaurabh.bareact.SectionFile.SectionFeedItem;

import java.util.List;

/**
 * Created by Saurabh Gaur on 9/27/2017.
 */

public class CompareSectionListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SectionFeedItem> sectionFeedItems;

    public CompareSectionListAdapter(Activity activity, List<SectionFeedItem> sectionFeedItems){
        this.activity = activity;
        this.sectionFeedItems = sectionFeedItems;
    }


    @Override
    public int getCount() {
        return sectionFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return sectionFeedItems.get(location);
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
            convertView = inflater.inflate(R.layout.list_section, null);

        CardView cardLayout = (CardView) convertView.findViewById(R.id.sectionCard);
        TextView title = (TextView) convertView.findViewById(R.id.sectionTitle);
        TextView desc = (TextView) convertView.findViewById(R.id.sectionDesc);
        TextView sec = (TextView) convertView.findViewById(R.id.sectionNum);

        final SectionFeedItem item = sectionFeedItems.get(position);

        title.setText(item.getSection_title());
        desc.setText(item.getSection_desc());
        sec.setText(item.getSection_number());

        cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, Compare.class);
                i.putExtra("section_id",item.getSection_id());
                activity.startActivity(i);
            }
        });
        return convertView;
    }
}
