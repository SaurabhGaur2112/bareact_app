package com.example.gaursaurabh.bareact.SectionFile;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Saurabh Gaur on 9/25/2017.
 */

public class SectionValues {

    public static String[] getSectionValues(String sectionId)
    {
        InputStream is = null;
        String line = null;
        String result = null;
        String temp_id = "",temp_number = "",temp_title = "",temp_desc = "",temp_foot = "";
        String[] arr_id,arr_number,arr_title,arr_desc,arr_foot;
        String getid = null,getnumber = null,gettitle = null,getdesc = null,getfoot = null;

        List<NameValuePair> sectionSend = new ArrayList<NameValuePair>(1);
        sectionSend.add(new BasicNameValuePair("section_id",sectionId));

        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://wallnit.com/bareactjson/section_desc.php");
            httpPost.setEntity(new UrlEncodedFormEntity(sectionSend));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e){

        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null)
                sb.append(line + "\n");
            result = sb.toString();
            is.close();
        } catch (Exception e) {

        }

        try{
            JSONArray jsonArray = new JSONArray(result);
            int count = jsonArray.length();

            for(int i = 0; i < count; i++ ) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                temp_id += jsonObject.getString("id");
                temp_number += jsonObject.getString("number");
                temp_title += jsonObject.getString("title");
                temp_desc += jsonObject.getString("description");
                temp_foot += jsonObject.getString("footnote");
            }

            arr_id = temp_id.split(":");
            arr_number = temp_number.split(":");
            arr_title = temp_title.split(":");
            arr_desc = temp_desc.split(":");
            arr_foot = temp_foot.split(":");

            getid = Arrays.toString(arr_id).replace("[","").replace("]","");
            getnumber = Arrays.toString(arr_number).replace("[","").replace("]","");
            gettitle = Arrays.toString(arr_title).replace("[","").replaceAll("]","");
            getdesc = Arrays.toString(arr_desc).replace("[","").replace("]","");
            getfoot = Arrays.toString(arr_foot).replace("[","").replace("]","");
        } catch (Exception e){

        }

        return new String[]
                {
                    getid,getnumber,gettitle,getdesc,getfoot
                };
    }
}
