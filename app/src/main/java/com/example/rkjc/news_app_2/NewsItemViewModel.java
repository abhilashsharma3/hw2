package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

    private NewsItemRepository mRepo;

    private LiveData<List<NewsItem>> mAllNewsitem;

    public NewsItemViewModel (Application application) {
        super(application);
        mRepo = new NewsItemRepository(application);
        mAllNewsitem = mRepo.getAllNewsitem();
    }

    public LiveData<List<NewsItem>> getAllNewsitem() {
        return mAllNewsitem;
    }

    public void insert() {
        mRepo.insert();
    }

//    public void delete(NewsItem newsItem){
//        mRepo.delete(newsItem);
//    }
}
