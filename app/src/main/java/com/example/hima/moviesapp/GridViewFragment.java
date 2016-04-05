package com.example.hima.moviesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
 * A placeholder fragment containing a simple view.
 */
public class GridViewFragment extends Fragment {
    public static List<Movie> moviesList;
    GridView gridView;
    public GridViewFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
       new FetchMovieTask().execute();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
         gridView = (GridView) rootView.findViewById(R.id.postersgridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(

        ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailedActivity.class);
                intent.putExtra("moviePoster", moviesList.get(position).getMoviePoster());
                intent.putExtra("movieTitle", moviesList.get(position).getTitle());
                intent.putExtra("movieRelease", moviesList.get(position).getRelease_data());
                intent.putExtra("moviePlot", moviesList.get(position).getPlotSynopsis());
                intent.putExtra("movieVote", moviesList.get(position).getVoteAverage());
                intent.putExtra("movieID", moviesList.get(position).getiD());
                startActivity(intent);
            }
        });
        return rootView;
    }
    public class FetchMovieTask extends AsyncTask<String,Void,List<Movie>>{
        private final String LOG_TAG = FetchMovieTask.class.getSimpleName();
        private List<Movie> getMovieDataFromJson(String MovieJsonStr)
        throws JSONException{
            moviesList = new ArrayList<Movie>();
            final String WEB_RESULT = "results";
          JSONObject initial = new JSONObject(MovieJsonStr);
            JSONArray  moviesArray = initial.getJSONArray(WEB_RESULT);
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
                    moviesList.add(movie);
                }
            }
            return moviesList;
        }
        @Override
        protected List<Movie> doInBackground(String... params) {

             HttpURLConnection urlConnection = null;
             BufferedReader reader = null;
             String MovieJsonStr = null;
            Uri builtUri;
            try {
                final String FETCH_MOVIE_TOP_RATED_BASE_URL = "http://api.themoviedb.org/3/movie/top_rated?";
                final String FETCH_MOVIE_popular_BASE_URL = "http://api.themoviedb.org/3/movie/popular?";
                final String API_KEY = "api_key";
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String sortingmethod = sharedPreferences.getString(getString(R.string.sort_type), getString(R.string.pref_default_value));
                if (sortingmethod.equals("popularity")) {
                   builtUri = Uri.parse(FETCH_MOVIE_popular_BASE_URL).buildUpon()
                            .appendQueryParameter(API_KEY, BuildConfig.Movie_App_API_KEY)
                            .build();
                }
                else{
                    builtUri = Uri.parse(FETCH_MOVIE_TOP_RATED_BASE_URL).buildUpon()
                            .appendQueryParameter(API_KEY, BuildConfig.Movie_App_API_KEY)
                            .build();
                }
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
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }
                return null;
        }
        @Override
        protected void onPostExecute(List<Movie> result){
            gridView.setAdapter(new GridViewAdapter(getActivity(),R.layout.gridview_item, result));

        }
    }
}
