package com.example.gaursaurabh.bareact.ChapterFIle;

/**
 * Created by Saurabh Gaur on 9/22/2017.
 */

public class ChapterFeedItem {

    private String chapter_num,title,section_num,chapter_id;

    public ChapterFeedItem(){

    }

    public ChapterFeedItem(String chapter_num,String title,String section_num,String chapter_id){
        super();
        this.chapter_num = chapter_num;
        this.title = title;
        this.section_num = section_num;
        this.chapter_id = chapter_id;
    }

    public String getChapter_num() {
        return chapter_num;
    }

    public void setChapter_num(String chapter_num) {
        this.chapter_num = chapter_num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection_num() {
        return section_num;
    }

    public void setSection_num(String section_num) {
        this.section_num = section_num;
    }

    public String getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }
}
