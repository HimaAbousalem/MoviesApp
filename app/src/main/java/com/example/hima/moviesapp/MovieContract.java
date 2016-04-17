package com.example.hima.moviesapp;

import android.provider.BaseColumns;

/**
 * Created by Hima on 4/17/2016.
 */
public class MovieContract {
    public static final class MovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "movie";
        public static final String  MOVIE_TITLE = "title";
        public static final String  MOVIE_RELEASE ="year";
        public static final String  MOVIE_POSTER = "poster";
        public static final String  MOVIE_OVERVIEW ="overview";
        public static final String  MOVIE_VOTE ="vote";
    }
}
