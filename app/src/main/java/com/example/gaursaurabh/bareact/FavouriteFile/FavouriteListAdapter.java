package com.example.gaursaurabh.bareact.FavouriteFile;

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
import com.example.gaursaurabh.bareact.SectionDesc;

import java.util.List;

/**
 * Created by Saurabh Gaur on 9/26/2017.
 */

public class FavouriteListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<FavouriteFeedItem> favouriteFeedItems;

    public FavouriteListAdapter(Activity activity, List<FavouriteFeedItem> favouriteFeedItems){
        this.activity = activity;
        this.favouriteFeedItems = favouriteFeedItems;
    }

    @Override
    public int getCount() {
        return favouriteFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return favouriteFeedItems.get(location);
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
            convertView = inflater.inflate(R.layout.list_favourite, null);

        CardView cardLayout = (CardView) convertView.findViewById(R.id.favouriteCard);
        TextView title = (TextView) convertView.findViewById(R.id.favouriteTitle);
        TextView desc = (TextView) convertView.findViewById(R.id.favouriteDesc);
        TextView num = (TextView) convertView.findViewById(R.id.favouriteNum);

        final FavouriteFeedItem item = favouriteFeedItems.get(position);

        title.setText(item.getFavourite_title());
        desc.setText(item.getFavourite_desc());
        num.setText(item.getFavourite_num());

        cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, SectionDesc.class);
                i.putExtra("section_id",item.getFavourite_id());
                activity.startActivity(i);
            }
        });

        return convertView;
    }
}
