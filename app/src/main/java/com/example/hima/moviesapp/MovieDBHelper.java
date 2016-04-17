package com.example.hima.moviesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hima on 4/17/2016.
 */
public class MovieDBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "movie.db";
    private static final int DATABASE_VERSION = 1;
    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE "+MovieContract.MovieEntry.TABLE_NAME+" ("+
                MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY, "+
                MovieContract.MovieEntry.MOVIE_TITLE+" TEXT NOT NULL, "+
                MovieContract.MovieEntry.MOVIE_RELEASE+" TEXT NOT NULL, "+
                MovieContract.MovieEntry.MOVIE_VOTE+" TEXT NOT NULL, "+
                MovieContract.MovieEntry.MOVIE_OVERVIEW+" TEXT NOT NULL, "+
                MovieContract.MovieEntry.MOVIE_POSTER+" TEXT NOT NULL, "+
                "UNIQUE ("+MovieContract.MovieEntry._ID+") ON CONFLICT IGNORE);";
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        onCreate(db);
    }
    public  void insertMovie(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MovieContract.MovieEntry._ID,movie.getiD());
        contentValues.put(MovieContract.MovieEntry.MOVIE_TITLE,movie.getTitle());
        contentValues.put(MovieContract.MovieEntry.MOVIE_RELEASE,movie.getRelease_data());
        contentValues.put(MovieContract.MovieEntry.MOVIE_VOTE,movie.getVoteAverage());
        contentValues.put(MovieContract.MovieEntry.MOVIE_OVERVIEW,movie.getPlotSynopsis());
        contentValues.put(MovieContract.MovieEntry.MOVIE_POSTER,movie.getMoviePoster());
        db.insert(MovieContract.MovieEntry.TABLE_NAME,null,contentValues);
        db.close();
    }


}
