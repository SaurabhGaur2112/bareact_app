package com.example.gaursaurabh.bareact.FavouriteFile;

/**
 * Created by Saurabh Gaur on 9/26/2017.
 */

public class FavouriteFeedItem {

    private String favourite_id,favourite_num,favourite_title,favourite_desc;

    public FavouriteFeedItem(){

    }

    public FavouriteFeedItem(String favourite_id,String favourite_num,String favourite_title,String favourite_desc){
        super();
        this.favourite_id = favourite_id;
        this.favourite_num = favourite_num;
        this.favourite_title = favourite_title;
        this.favourite_desc = favourite_desc;
    }

    public String getFavourite_id() {
        return favourite_id;
    }

    public void setFavourite_id(String favourite_id) {
        this.favourite_id = favourite_id;
    }

    public String getFavourite_num() {
        return favourite_num;
    }

    public void setFavourite_num(String favourite_num) {
        this.favourite_num = favourite_num;
    }

    public String getFavourite_title() {
        return favourite_title;
    }

    public void setFavourite_title(String favourite_title) {
        this.favourite_title = favourite_title;
    }

    public String getFavourite_desc() {
        return favourite_desc;
    }

    public void setFavourite_desc(String favourite_desc) {
        this.favourite_desc = favourite_desc;
    }
}
