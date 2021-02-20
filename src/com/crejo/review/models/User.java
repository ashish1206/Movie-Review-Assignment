package com.crejo.review.models;

import java.util.HashMap;
import java.util.Map;

public class User {

    public User(){
        review = new HashMap<>();
        reviewCount = 0;
    }

    private String name;
    private UserLevel userLevel;
    private Integer reviewCount;
    private Map<String, Integer> review;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Map<String, Integer> getReview() {
        return review;
    }

    public void setReview(Map<String, Integer> review) {
        this.review = review;
    }
}
