package com.example.rkjc.news_app_2;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class MainActivity extends AppCompatActivity implements NewsRecyclerViewAdapter.ItemClick {
    private RecyclerView recyclerView;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private ArrayList<NewsItem>  newsIte;
    private NewsItemViewModel mNewsItemViewModel;
    private NewsItemRepository newsItemRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.news_recyclerview);
      //  MenuItem item=findViewById(R.menu.main_menu);
        mNewsItemViewModel= ViewModelProviders.of(this).get(NewsItemViewModel.class);
        mNewsItemViewModel.getAllNewsitem().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(new ArrayList<NewsItem>(newsItems),  MainActivity.this);
                newsIte=NewsItemRepository.insertAsyncTask.newsItems;
                recyclerView.setAdapter(newsRecyclerViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setHasFixedSize(true);

            }
        });

//        NewsQueryTask task = new NewsQueryTask();
//        task.execute();

    }

    public void populateRecyclerView(String jstring) {
        mNewsItemViewModel.getAllNewsitem().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable final List<NewsItem> newsItems) {
                // Update the cached copy of the words in the adapter.
               newsRecyclerViewAdapter.setNews((ArrayList<NewsItem>) newsItems);
            }
    });

    }


    class NewsQueryTask extends AsyncTask<String, Void, String> {
        private final String TAG = "MainActivity Async";
        private String jstring;

        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "Async task do in background");
            String jstring = null;
            try {
                jstring = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jstring;
        }

        @Override
        protected void onPostExecute(String newsResult) {
            populateRecyclerView(newsResult);
        }
    }
    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int newsclicked=item.getItemId();
        if(newsclicked==R.id.action_search){
            mNewsItemViewModel.insert();
//            newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(this, newsItems,mNewsItemViewModel);
//            recyclerView.setAdapter(newsRecyclerViewAdapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            NewsQueryTask task = new NewsQueryTask();
//            task.execute();
        }
        if (newsclicked==R.menu.main_menu){
            mNewsItemViewModel.insert();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(final int i){
        Log.d("OnCLick","Adapter Position"+i);
        String url1=newsIte.get(i).getUrl();
        Log.d("OnCLick","Adapter Position"+url1);
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url1));
        startActivity(intent);
    }

}

