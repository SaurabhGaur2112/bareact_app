package com.example.gaursaurabh.bareact.BareActFile;

/**
 * Created by Saurabh Gaur on 9/22/2017.
 */

public class BareActFeedItem {

    private String id,bareact;

    public BareActFeedItem(){

    }

    public BareActFeedItem(String id,String bareact){
        this.id = id;
        this.bareact = bareact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBareact() {
        return bareact;
    }

    public void setBareact(String bareact) {
        this.bareact = bareact;
    }
}
