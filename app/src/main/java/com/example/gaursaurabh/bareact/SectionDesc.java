package com.example.gaursaurabh.bareact;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaursaurabh.bareact.SQLite.DBHelper;
import com.example.gaursaurabh.bareact.SectionFile.SectionValues;
import com.example.gaursaurabh.bareact.Session.Session;

public class SectionDesc extends AppCompatActivity {

    SectionValues sectionId;
    String getValue[];
    String id,number,title,desc,foot,secId;
    DBHelper dbHelper;
    ImageView fav,select_fav;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_desc);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dbHelper = new DBHelper(this);
        session = new Session(this);

        Intent i = getIntent();
        secId = i.getStringExtra("section_id");
        sectionId = new SectionValues();
        getValue = sectionId.getSectionValues(secId);
        id = getValue[0];
        number = getValue[1];
        title = getValue[2];
        desc = getValue[3];
        foot = getValue[4];

        fav = (ImageView) findViewById(R.id.save);
        select_fav = (ImageView) findViewById(R.id.save_select);

        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Title :\n"+title+"\n\nDescription :\n"+desc+"\n\nFootnote :\n"+foot);
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(sendIntent, ""));
                startActivity(sendIntent);
            }
        });

        findViewById(R.id.mail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "Section : "+number);
                intent.putExtra(Intent.EXTRA_TEXT, "Title :\n"+title+"\n\nDescription :\n"+desc+"\n\nFootnote :\n"+foot);
                startActivity(Intent.createChooser(intent, ""));
            }
        });

        findViewById(R.id.compare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setSectionTitle(title);
                session.setSectionDesc(desc);
                session.setSectionFootnote(foot);
                startActivity(new Intent(SectionDesc.this,CompareBareAct.class));
            }
        });

        String fav_exist = dbHelper.checkIfExist(secId);
        if(fav_exist.equals("true"))
        {
            select_fav.setVisibility(View.VISIBLE);
        }
        if(fav_exist.equals("false"))
        {
            fav.setVisibility(View.VISIBLE);
        }

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addFavourites(id);
                fav.setVisibility(View.GONE);
                select_fav.setVisibility(View.VISIBLE);
            }
        });

        select_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteFav(id);
                select_fav.setVisibility(View.GONE);
                fav.setVisibility(View.VISIBLE);
            }
        });

        TextView title_view = (TextView) findViewById(R.id.section_title);
        title_view.setText(title);

        TextView desc_view = (TextView) findViewById(R.id.section_desc);
        desc_view.setText(desc);

        TextView foot_view = (TextView) findViewById(R.id.section_footnote);
        View line = (View) findViewById(R.id.foot_line);

        if(!foot.equals(""))
        {
            foot_view.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
            foot_view.setText(foot);
        }
    }
}