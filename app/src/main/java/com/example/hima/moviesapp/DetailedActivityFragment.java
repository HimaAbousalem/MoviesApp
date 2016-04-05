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
  /* public class FetchAdditionalData extends AsyncTask<String, Void, String[]> {
        private final String LOG_TAG = FetchAdditionalData.class.getSimpleName();
        @Override
        protected String[] doInBackground(String... params) {
            if(params.length==0){
                return null;
            }
            HttpURLConnection urlConnection= null;
            BufferedReader reader = null;
            String AdditionalJsonStr = null;
            final Uri[] builtUri = new Uri[1];
            try {
                final String FETCH_MOVIE_TOP_RATED_BASE_URL = "http://api.themoviedb.org/3/movie/";
                final String API_KEY = "api_key";
                final String Review= "reviews";
                final String Trailer = "videos";

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch(v.getId()) {
                            case R.id.trailers:
                                builtUri[0] = Uri.parse(FETCH_MOVIE_TOP_RATED_BASE_URL).buildUpon()
                                        .appendPath(id)
                                        .appendPath(Trailer)
                                        .appendQueryParameter(API_KEY, BuildConfig.Movie_App_API_KEY)
                                        .build();
                                break;
                            case R.id.reviews:
                                builtUri[0] = Uri.parse(FETCH_MOVIE_TOP_RATED_BASE_URL).buildUpon()
                                        .appendPath(id)
                                        .appendPath(Review)
                                        .appendQueryParameter(API_KEY, BuildConfig.Movie_App_API_KEY)
                                        .build();
                                break;
                        }
                    }
                };
                URL url = new URL(builtUri[0].toString());
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
                AdditionalJsonStr = buffer.toString();
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
                return getMovieDataFromJson(AdditionalJsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }
            return null;
        }

        private String[] getMovieDataFromJson(String additionalJsonStr) throws JSONException{
            final String WEB_RESULT = "results";
            JSONObject initial =new JSONObject(additionalJsonStr);
            return new String[0];
        }

    }*/

}
