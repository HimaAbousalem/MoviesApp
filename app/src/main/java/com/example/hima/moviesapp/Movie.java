package com.example.hima.moviesapp;

/**
 * Created by Hima on 3/25/2016.
 */
public class Movie {
   private String  title, release_data, moviePoster, voteAverage, plotSynopsis, iD, reviews;
   private String[] trailers;
    public void setTitle(String title){
        this.title=title;
    }
    public void setRelease_data(String release_data){
        this.release_data=release_data;
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
    public void setTrailers(String[] Trailers){
        this.trailers= Trailers;
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
    public String[] getTrailers(){
        return trailers;
    }

}