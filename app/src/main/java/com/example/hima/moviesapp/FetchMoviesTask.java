package com.example.hima.moviesapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hima on 4/6/2016.
 */
class FetchMovieTask extends AsyncTask<String, Void, List<Movie>> {
    private final String LOG_TAG = FetchMovieTask.class.getSimpleName();
    private List<Movie> data;
    private Uri builtUri;
    private Context mContext;
    private String determineJSONFunction;
    private GridView gridView;
    public FetchMovieTask(Uri builtUri,GridView gridView,Context mContext,String determineJSONFunction){
        this.builtUri=builtUri;
        this.gridView=gridView;
        this.mContext=mContext;
        this.determineJSONFunction=determineJSONFunction;
    }
    public List<Movie> getMovieDataFromJson(String MovieJsonStr)
            throws JSONException {
        data = new ArrayList<Movie>();
        final String WEB_RESULT = "results";
        JSONObject initial = new JSONObject(MovieJsonStr);
        JSONArray moviesArray = initial.getJSONArray(WEB_RESULT);
        if(moviesArray==null){
            return null;
        }
        else{
            for(int i=0;i<moviesArray.length();i++){
                JSONObject movieDetail = moviesArray.getJSONObject(i);
                Movie movie = new Movie();
                movie.setMoviePoster(movieDetail.getString("poster_path"));
                movie.setTitle(movieDetail.getString("title"));
                movie.setRelease_data(movieDetail.getString("release_date"));
                movie.setVoteAverage(movieDetail.getString("vote_average"));
                movie.setPlotSynopsis(movieDetail.getString("overview"));
                movie.setiD(movieDetail.getString("id"));
                data.add(movie);
            }
        }
        return data;
    }
    @Override
    protected List<Movie> doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String MovieJsonStr = null;

        try {
            URL url = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                Log.d(LOG_TAG, "input stream is null");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0){
                return null;
            }
            MovieJsonStr = buffer.toString();
        }catch(IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        try {

                    return getMovieDataFromJson(MovieJsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(List<Movie> result){

            gridView.setAdapter(new GridViewAdapter(mContext, R.layout.gridview_item, result));


    }
   public List<Movie> getData(){return data;}

}