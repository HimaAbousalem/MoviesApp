package com.example.hima.moviesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class GridViewFragment extends Fragment {
    public  List<Movie> moviesList;
    GridView gridView;
    FetchMovieTask fetchMovieTask;
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
        fetchData();
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
                moviesList = fetchMovieTask.getData();
                Intent intent = new Intent(getActivity(), DetailedActivity.class);
                intent.putExtra("movie",moviesList.get(position));
                startActivity(intent);
            }
        });
        return rootView;
    }
  private void fetchData(){
      Uri builtUri;
      SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
      String sortingmethod = sharedPreferences.getString(getString(R.string.sort_type), getString(R.string.pref_default_value));
      final String FETCH_MOVIE_TOP_RATED_BASE_URL = "http://api.themoviedb.org/3/movie/top_rated?";
      final String FETCH_MOVIE_popular_BASE_URL = "http://api.themoviedb.org/3/movie/popular?";
      final String API_KEY = "api_key";

      if (sortingmethod.equals("popular")) {
          builtUri = Uri.parse(FETCH_MOVIE_popular_BASE_URL).buildUpon()
                  .appendQueryParameter(API_KEY, BuildConfig.Movie_App_API_KEY)
                  .build();
      }
      else{
          builtUri = Uri.parse(FETCH_MOVIE_TOP_RATED_BASE_URL).buildUpon()
                  .appendQueryParameter(API_KEY, BuildConfig.Movie_App_API_KEY)
                  .build();
      }

      fetchMovieTask= new FetchMovieTask(builtUri,gridView,getActivity(),"movie");
      fetchMovieTask.execute();

  }

}
