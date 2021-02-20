package com.crejo.review.service;

import com.crejo.review.exception.MovieNotReleasedException;
import com.crejo.review.exception.MultipleReviewException;
import com.crejo.review.models.Movie;
import com.crejo.review.models.Pair;
import com.crejo.review.models.User;
import com.crejo.review.models.UserLevel;

import java.util.*;

public class MovieReviewService {

    private Map<String, Movie> movieMap;
    private Map<String, User> userMap;

    public MovieReviewService(){
        movieMap = new HashMap<>();
        userMap = new HashMap<>();
    }

    /**
     * Add a new user
     * @param name
     */
    public void addUser(String name){
        User user = new User();
        user.setName(name);
        user.setUserLevel(UserLevel.USER);
        userMap.put(name, user);
    }

    /**
     * Add a new movie
     * @param name
     * @param yearOfRelease
     * @param genres
     */
    public void addMovie(String name, Integer yearOfRelease, Collection<String> genres){
        Movie movie = new Movie();
        movie.setName(name);
        movie.setYearOfRelease(yearOfRelease);
        movie.setGenres(genres);
        movieMap.put(name, movie);
    }

    /**
     * Add a review of movie and also update corresponding user
     * @param userName
     * @param movieName
     * @param rating
     * @throws Exception
     */
    public void addReview(String userName, String movieName, Integer rating) throws Exception{
        Movie movie = movieMap.get(movieName);
        Date now = new Date();
        if(rating < 1 && rating > 10){
            throw new IllegalArgumentException("rating should be between 1 to 10");
        }
        else if(now.getYear()+1900 < movie.getYearOfRelease()){
            throw new MovieNotReleasedException("User Can't review unreleased movie");
        }
        else if(movie.getReviews().containsKey(userName)){
            throw new MultipleReviewException("User can't review a movie multiple times");
        }
        else{
            updateMovie(movie, userName, rating);
            updateUser(userName, movieName, rating);
        }
    }

    private void updateMovie(Movie movie, String userName, Integer rating){
        Integer totalReviews = movie.getTotalReviews();
        Double totalScore = movie.getAvgReview() * totalReviews;
        movie.setTotalReviews(totalReviews + 1);
        totalScore += rating;
        movie.setAvgReview(totalScore / (totalReviews + 1));
        movie.getReviews().put(userName, rating);
    }

    private void updateUser(String userName, String movieName, Integer rating){
        User user = userMap.get(userName);
        user.setReviewCount(user.getReviewCount()+1);
        user.getReview().put(movieName, rating);
        checkAndUpgradeUser(user);
    }

    private void checkAndUpgradeUser(User user){
        if(user.getReviewCount() == 3){
            user.setUserLevel(UserLevel.CRITIC);
        }
    }

    /**
     * Get average rating for movie
     * @param movieName
     * @return
     */
    public Double getAverageMovieReview(String movieName){
        return movieMap.get(movieName).getAvgReview();
    }

    /**
     * Get top N movies reviewed by critics based on total score by critics
     * @param genre
     * @param n
     * @return
     */
    public List<String> getTopReviewsByCriticsForGenre(String genre, int n){
        Map<String, Integer> movieByGenre = new HashMap<>();
        for(User user : userMap.values()){
            if(user.getUserLevel() == UserLevel.CRITIC){
                Map<String, Integer> userReviews = user.getReview();
                for(String movieName : userReviews.keySet()){
                    Movie movie = movieMap.get(movieName);
                    if(movie.getGenres().contains(genre)){
                        Integer ratingGiven = userReviews.get(movieName);
                        if(!movieByGenre.containsKey(movieName)){
                            movieByGenre.put(movieName, 0);
                        }
                        Integer totalScore = movieByGenre.get(movieName);
                        movieByGenre.put(movieName, totalScore+ratingGiven);
                    }
                }
            }
        }
        List<Pair> pairList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : movieByGenre.entrySet()){
            Pair pair = new Pair();
            pair.first = entry.getKey();
            pair.second = entry.getValue();
            pairList.add(pair);
        }
        Collections.sort(pairList, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return (Integer)o1.second - (Integer)o2.second;
            }
        });
        List<String> topMovies = new ArrayList<>();
        for(Pair pair : pairList){
            if(n==0){
                break;
            }
            topMovies.add((String)pair.first);
            n--;
        }
        return topMovies;
    }

    /**
     * Get average score for all movies released in a year
     * @param year
     * @return
     */
    public Double getAverageScoreForReleaseYear(Integer year){
        Double avgReview = 0.0;
        Integer totalMovies = 0;
        for(Movie movie : movieMap.values()){
            if(movie.getYearOfRelease().equals(year)){
                avgReview += movie.getAvgReview();
                totalMovies++;
            }
        }
        return avgReview/totalMovies;
    }
}
