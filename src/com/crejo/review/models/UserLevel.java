package com.crejo.review.models;

public enum UserLevel {
    USER(1),
    CRITIC(2),
    EXPERT(3),
    ADMIN(4);

    private int level;

    UserLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
