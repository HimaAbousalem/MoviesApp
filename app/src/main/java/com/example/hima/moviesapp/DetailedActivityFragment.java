package com.example.hima.moviesapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailedActivityFragment extends Fragment {
    public String id;
    FetchMovieTask fetchMovieTask;
    Uri builtUri;
    List<Movie> data;
    ListView list;
    int index;

    public DetailedActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detailed, container, false);

        TextView title = (TextView) rootView.findViewById(R.id.movie_title);
        TextView vote = (TextView) rootView.findViewById(R.id.movie_vote_average);
        TextView release = (TextView) rootView.findViewById(R.id.movie_release_date);
        TextView review = (TextView) rootView.findViewById(R.id.movie_overview);
        ImageView poster = (ImageView) rootView.findViewById(R.id.movie_poster);
        Intent i = getActivity().getIntent();
        Movie myMovieObject = (Movie) i.getParcelableExtra("movie");
        Picasso.with(getContext()).load(myMovieObject.getMoviePoster()).into(poster);
        title.setText(myMovieObject.getTitle());
        vote.setText(myMovieObject.getVoteAverage());
        review.setText(myMovieObject.getPlotSynopsis());
        release.setText(myMovieObject.getRelease_data());
        list = (ListView)rootView.findViewById(R.id.list_trailers);

        getTrailer();
        data=fetchMovieTask.getData();
         list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             //         String uri =list.get(position);
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=nIGtF3J5kn8")));
             }
         });
        return rootView;
    }
    private void getTrailer() {
        final String FETCH_MOVIE_BASE_URL = "http://api.themoviedb.org/3/movie/";
        final String API_KEY = "api_key";
        builtUri = Uri.parse(FETCH_MOVIE_BASE_URL).buildUpon()
                .appendPath(id)
                .appendPath("videos")
                .appendQueryParameter(API_KEY, BuildConfig.Movie_App_API_KEY)
                .build();
        fetchMovieTask = new FetchMovieTask(builtUri, list, getActivity(), "trailer",index);
        fetchMovieTask.execute();
    }
}