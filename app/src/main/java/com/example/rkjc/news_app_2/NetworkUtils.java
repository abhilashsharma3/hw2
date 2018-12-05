package com.example.rkjc.news_app_2;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    final static String BASE_URL="https://newsapi.org/v1/articles";
    final static String PARAM_SOURCE="source";
    final static String PARAM_SORT="sortBy";
    final static String PARAM_APIKEY="apiKey";
    final static String sortBy="latest";
    final static String apiKey="d537d517bc944f2ebbf474351435fa26";
    final static String source="the-next-web";


    public static URL buildURL(){
        Uri builtURI=Uri.parse(BASE_URL).buildUpon().appendQueryParameter(PARAM_SOURCE,source)
                .appendQueryParameter(PARAM_SORT,sortBy)
                .appendQueryParameter(PARAM_APIKEY,apiKey).build();

        URL buildURL=null;
        try{
            buildURL=new URL(builtURI.toString());
        }
        catch (MalformedURLException ex){
            ex.printStackTrace();
        }
        return buildURL;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
        try{
            InputStream in= urlConnection.getInputStream();

            Scanner scanner=new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput=scanner.hasNext();
            if (hasInput){
                return scanner.next();
            }
            else {
                return null;
            }

        }
        finally {
            urlConnection.disconnect();
        }

    }
}
