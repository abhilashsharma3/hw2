package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {
    public static ArrayList<NewsItem> parseNews(String JSONString){
        ArrayList<NewsItem> newsItems=new ArrayList<>();
        try{
            JSONObject jsonObject=new JSONObject(JSONString);
            JSONArray jsonArray=jsonObject.getJSONArray("articles");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject article=jsonArray.getJSONObject(i);
                newsItems.add(new NewsItem(article.getString("author"),
                        article.getString("title"),article.getString("description"),
                        article.getString("url"),article.getString("publishedAt")
                ));
            }
        }
        catch (JSONException ex){
            ex.printStackTrace();

        }
        return newsItems;
    }

}


