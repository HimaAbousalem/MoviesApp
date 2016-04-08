package com.example.hima.moviesapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    String[] trailersLinks;
    List<Movie> data;
    ArrayAdapter<String> adapter;
    ListView list;
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
        Intent intent = getActivity().getIntent();

        TextView title = (TextView) rootView.findViewById(R.id.movie_title);
        TextView vote = (TextView) rootView.findViewById(R.id.movie_vote_average);
        TextView release = (TextView) rootView.findViewById(R.id.movie_release_date);
        TextView review = (TextView) rootView.findViewById(R.id.movie_overview);
        ImageView poster = (ImageView) rootView.findViewById(R.id.movie_poster);

        String image = intent.getStringExtra("moviePoster");
        Picasso.with(getContext()).load(image).into(poster);
        String Title = intent.getStringExtra("movieTitle");
        title.setText(Title);
        String voteString = intent.getStringExtra("movieVote");
        vote.setText(voteString);
        String Review = intent.getStringExtra("moviePlot");
        review.setText(Review);
        String Release = intent.getStringExtra("movieRelease");
        release.setText(Release);
        id = intent.getStringExtra("movieID");
        getTrailer();
        list = (ListView)rootView.findViewById(R.id.list_trailers);
       // data = fetchMovieTask.getData();
        //  trailersLinks= data.getTrailers();

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
        fetchMovieTask = new FetchMovieTask(builtUri, list, getActivity(), "trailer");
        fetchMovieTask.execute();
    }
}