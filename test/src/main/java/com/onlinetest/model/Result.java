package com.onlinetest.model;

public class Result {
    private int id;
    private int userId;
    private int score;

    public Result() {}

    public Result(int id, int userId, int score) {
        this.id = id;
        this.userId = userId;
        this.score = score;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
