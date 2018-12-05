package com.example.rkjc.news_app_2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;


@Entity(tableName = "news_item")
public class NewsItem {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id=0;

    private String url;

    private String author;

    private String title;

    private String description;

    private String date;


    public NewsItem(int id,  String author, String title, String description,  String url,  String date) {
        this.id++;
        this.author=author;
        this.description=description;
        this.title=title;
        this.url=url;
        this.date=date;
    }

    @Ignore
    public NewsItem(@NonNull String author, @NonNull String title, @NonNull String description, @NonNull String url , @NonNull String date) {
        this.author=author;
        this.description=description;
        this.title=title;
        this.url=url;
        this.date=date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}

