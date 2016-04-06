package com.example.hima.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailedActivityFragment extends Fragment {
    public String id;
    public int clicked;
    public DetailedActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detailed, container, false);
        Intent intent = getActivity().getIntent();
        TextView title = (TextView) rootView.findViewById(R.id.movie_title);
        TextView vote = (TextView) rootView.findViewById(R.id.movie_vote_average);
        TextView release = (TextView) rootView.findViewById(R.id.movie_release_date);
        TextView review = (TextView) rootView.findViewById(R.id.movie_review);
        ImageView poster = (ImageView) rootView.findViewById(R.id.movie_poster);
        Button mTrailer = (Button) rootView.findViewById(R.id.trailers);
        Button mReview = (Button) rootView.findViewById(R.id.reviews);

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
        id = intent.getStringExtra("id");
        return rootView;
    }

}
