package com.crejo.review.models;

import java.util.*;

public class Movie {
    public Movie(){
        reviews = new HashMap<>();
        avgReview = 0.0;
        totalReviews = 0;
    }

    private String name;
    private Integer yearOfRelease;
    private Set<String> genres;
    private Map<String, Integer> reviews;
    private Double avgReview;
    private Integer totalReviews;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Collection<String> genres) {
        this.genres = new HashSet<>(genres);
    }

    public Map<String, Integer> getReviews() {
        return reviews;
    }

    public void setReviews(Map<String, Integer> reviews) {
        this.reviews = reviews;
    }

    public Double getAvgReview() {
        return avgReview;
    }

    public void setAvgReview(Double avgReview) {
        this.avgReview = avgReview;
    }

    public Integer getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(Integer totalReviews) {
        this.totalReviews = totalReviews;
    }
}
