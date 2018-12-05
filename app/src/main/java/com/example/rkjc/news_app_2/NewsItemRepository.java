package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsItemRepository {
    private static NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllNewsitem;

    //Display the data
    public NewsItemRepository(Application application){
        NewsItemDatabase database=NewsItemDatabase.getDatabase(application.getApplicationContext());
        mNewsItemDao=database.newsItemDao();
        mAllNewsitem=mNewsItemDao.loadAllNewsItems();
    }

   public LiveData<List<NewsItem>> getAllNewsitem() {
        return mAllNewsitem;
    }


    public static void insert() {
        new insertAsyncTask(mNewsItemDao).execute(NetworkUtils.buildURL());
    }

//    public void delete(NewsItem newsItem){
//        new deleteAsyncTask(mNewsItemDao).execute(newsItem);
//    }


    public static class insertAsyncTask extends AsyncTask<URL, Void, String> {
       private   NewsItemDao mAsyncTaskDao;
        public static ArrayList<NewsItem> newsItems;
        insertAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        String j = null;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
          mNewsItemDao.clearAll();
        }

        @Override
        protected String doInBackground(URL... newsItem) {

            try {
                j = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return j;
        }

        @Override
        protected void onPostExecute(String jstring){
            super.onPostExecute(jstring);
            ArrayList<NewsItem> n=JsonUtils.parseNews(jstring);
            Log.d("doinbackground_newsRepo","Newtork JSON string "+jstring);
            newsItems = n;
            Log.d("doinbackground_newsRepo","Newsitem lenght "+newsItems.get(2).getUrl());
            //newsRecyclerViewAdapter.setNews(this.newsItems);
            mAsyncTaskDao.insert(newsItems);

        }

    }

    private static void populateRepo(String jstring) {

    }


//    private static class deleteAsyncTask extends AsyncTask<NewsItem, Void, Void> {
//        private NewsItemDao mAsyncTaskDao;
//        deleteAsyncTask(NewsItemDao dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(final NewsItem... params) {
//            mAsyncTaskDao.clearAll();
//            return null;
//        }
//    }

}
