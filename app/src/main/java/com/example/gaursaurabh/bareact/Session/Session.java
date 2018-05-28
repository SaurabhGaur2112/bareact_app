package com.example.gaursaurabh.bareact.Session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Saurabh Gaur on 9/27/2017.
 */

public class Session {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    String title,description,footnote;

    public Session(Context context){
        this.context = context;
        preferences = context.getSharedPreferences("bareActApplication", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setSectionTitle(String sec_title){
        title = sec_title;
        editor.putString("sectionTitle",title);
        editor.commit();
    }

    public String getSectionTitle(){
        return preferences.getString("sectionTitle",title);
    }

    public void setSectionDesc(String sec_desc){
        description = sec_desc;
        editor.putString("sectionDescription",description);
        editor.commit();
    }

    public String getSectionDesc(){
        return preferences.getString("sectionDescription",description);
    }

    public void setSectionFootnote(String sec_foot){
        footnote = sec_foot;
        editor.putString("sectionFootnote",footnote);
        editor.commit();
    }

    public String getSectionFootnote(){
        return preferences.getString("sectionFootnote",footnote);
    }
}
