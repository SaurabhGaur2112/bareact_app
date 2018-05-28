package com.example.gaursaurabh.bareact;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaursaurabh.bareact.SectionFile.SectionValues;
import com.example.gaursaurabh.bareact.Session.Session;

public class Compare extends AppCompatActivity {

    Session session;
    TextView first_title,first_desc,first_foot,second_title,second_desc,second_foot;

    SectionValues sectionId;
    String getValue[];
    String title,desc,foot,secId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        session = new Session(this);
        first_title = (TextView) findViewById(R.id.title_first);
        first_desc = (TextView) findViewById(R.id.desc_first);
        first_foot = (TextView) findViewById(R.id.footnote_first);
        second_title = (TextView) findViewById(R.id.title_second);
        second_desc = (TextView) findViewById(R.id.desc_second);
        second_foot = (TextView) findViewById(R.id.footnote_second);

        first_title.setText(session.getSectionTitle());
        first_desc.setText(session.getSectionDesc());
        first_foot.setText(session.getSectionFootnote());

        Intent i = getIntent();
        secId = i.getStringExtra("section_id");
        sectionId = new SectionValues();
        getValue = sectionId.getSectionValues(secId);
        title = getValue[2];
        desc = getValue[3];
        foot = getValue[4];

        second_title.setText(title);
        second_desc.setText(desc);

        first_foot.setText(foot);

    }
}
