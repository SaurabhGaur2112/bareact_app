package com.example.gaursaurabh.bareact.ChapterFIle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gaursaurabh.bareact.R;
import com.example.gaursaurabh.bareact.Section;

import java.util.List;

/**
 * Created by Saurabh Gaur on 9/22/2017.
 */

public class ChapterListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ChapterFeedItem> chapterFeedItems;

    public ChapterListAdapter(Activity activity, List<ChapterFeedItem> chapterFeedItems){
        this.activity = activity;
        this.chapterFeedItems = chapterFeedItems;
    }

    @Override
    public int getCount() {
        return chapterFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return chapterFeedItems.get(location);
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
            convertView = inflater.inflate(R.layout.list_chapter, null);

        CardView chapter_card = (CardView) convertView.findViewById(R.id.chapterCard);
        TextView chapter_num = (TextView) convertView.findViewById(R.id.chapterTxt);
        TextView chapter_title = (TextView) convertView.findViewById(R.id.chapterTitle);
        TextView chapter_section = (TextView) convertView.findViewById(R.id.chapterSectionNum);

        final ChapterFeedItem item = chapterFeedItems.get(position);

        chapter_num.setText("Chapter "+item.getChapter_num());
        chapter_title.setText(item.getTitle());
        chapter_section.setText("Section "+item.getSection_num());

        chapter_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, Section.class);
                i.putExtra("chapter_id",item.getChapter_id());
                activity.startActivity(i);
            }
        });

        return convertView;
    }
}
