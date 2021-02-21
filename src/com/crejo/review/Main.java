package com.crejo.review;

import com.crejo.review.constants.GenreConstants;
import com.crejo.review.service.MovieReviewService;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        MovieReviewService service = new MovieReviewService();
        service.addMovie("Don", 2006, Arrays.asList(new String[] {GenreConstants.ACTION, GenreConstants.COMEDY}));
        service.addMovie("Tiger", 2008, Arrays.asList(new String[] {GenreConstants.DRAMA}));
        service.addMovie("Padmaavat", 2006, Arrays.asList(new String[] {GenreConstants.ACTION, GenreConstants.COMEDY}));
        service.addMovie("LunchBox", 2022, Arrays.asList(new String[] {GenreConstants.DRAMA}));
        service.addMovie("Guru", 2006, Arrays.asList(new String[] {GenreConstants.DRAMA}));
        service.addMovie("Metro", 2006, Arrays.asList(new String[] {GenreConstants.ACTION}));
        service.addUser("SRK");
        service.addUser("Salman");
        service.addUser("Deepika");
        try {
            System.out.println(service.addReview("SRK", "Don", 2));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(service.addReview("SRK", "Padmaavat", 8));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(service.addReview("Salman", "Don", 5));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(service.addReview("Deepika", "Don", 9));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(service.addReview("Deepika", "Guru", 6));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(service.addReview("SRK", "Don", 10));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(service.addReview("Deepika", "LunchBox", 5));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(service.addReview("SRK", "Tiger", 5));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(service.addReview("SRK", "Metro", 7));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        service.getAverageMovieReview("Don");
        service.getAverageScoreForReleaseYear(2006);
        service.getTopReviewsByCriticsForGenre(GenreConstants.DRAMA, 2);
    }
}
