package com.example.hima.moviesapp;

import java.util.ArrayList;

/**
 * Created by Hima on 3/25/2016.
 */
public class Movie {
   private String  title, release_data, moviePoster, voteAverage, plotSynopsis, iD, reviews;
   private ArrayList<String> trailers;

    public Movie(){
        trailers = new ArrayList<String>();
      }
    public void setTitle(String title){
        this.title=title;
    }
    public void setRelease_data(String release_data){
        if(release_data.length()>4) {
            release_data = release_data.substring(0, 4);
        }
        this.release_data =release_data;
    }
    public void setMoviePoster(String moviePoster){
        String MOVIES_BASE_URL= "http://image.tmdb.org/t/p/w185/";
        this.moviePoster= MOVIES_BASE_URL+moviePoster;
    }
    public void setiD(String iD){
        this.iD=iD;
    }
    public void setVoteAverage(String voteAverage){
        this.voteAverage=voteAverage;
    }
    public void setPlotSynopsis(String plotSynopsis){
        this.plotSynopsis= plotSynopsis;
    }
    public void setReviews(String Reviews){
        this.reviews=Reviews;
    }
    //  Movie Trailers !
    public void setTrailers(String Trailers){
      trailers.add("https://www.youtube.com/watch?v="+Trailers);
    }

    public String getTitle(){
        return title;
    }
    public String getRelease_data(){
        return release_data;
    }
    public String getMoviePoster(){
        return moviePoster;
    }
    public String getVoteAverage(){
        return voteAverage+"/10";
    }
    public String getPlotSynopsis(){
        return plotSynopsis;
    }
    public String getiD(){
        return iD;
    }
    public String getReviews(){
        return reviews;
    }
    public ArrayList<String> getTrailers(){
        return trailers;
    }

}