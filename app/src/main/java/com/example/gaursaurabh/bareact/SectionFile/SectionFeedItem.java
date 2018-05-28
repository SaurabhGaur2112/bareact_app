package com.example.gaursaurabh.bareact.SectionFile;

/**
 * Created by Saurabh Gaur on 9/22/2017.
 */

public class SectionFeedItem {

    private String section_id,section_number,section_title,section_desc;

    public SectionFeedItem(){

    }

    public SectionFeedItem(String section_id,String section_number,String section_title,String section_desc){
        super();
        this.section_id = section_id;
        this.section_number = section_number;
        this.section_title = section_title;
        this.section_desc = section_desc;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getSection_number() {
        return section_number;
    }

    public void setSection_number(String section_number) {
        this.section_number = section_number;
    }

    public String getSection_title() {
        return section_title;
    }

    public void setSection_title(String section_title) {
        this.section_title = section_title;
    }

    public String getSection_desc() {
        return section_desc;
    }

    public void setSection_desc(String section_desc) {
        this.section_desc = section_desc;
    }

}
