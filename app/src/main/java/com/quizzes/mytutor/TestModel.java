package com.quizzes.mytutor;

// Class representing a model for Test data
public class TestModel {
    // Private member variables to store test details
    private String testID;
    private int topScore;
    private int time;

    // Constructor to initialize TestModel with testID, topScore, and time
    public TestModel(String testID, int topScore, int time) {
        this.testID = testID;
        this.topScore = topScore;
        this.time = time;
    }

    // Getter method for testID
    public String getTestID() {
        return testID;
    }

    // Setter method for testID
    public void setTestID(String testID) {
        this.testID = testID;
    }

    // Getter method for topScore
    public int getTopScore() {
        return topScore;
    }

    // Setter method for topScore
    public void setTopScore(int topScore) {
        this.topScore = topScore;
    }

    // Getter method for time
    public int getTime() {
        return time;
    }

    // Setter method for time
    public void setTime(int time) {
        this.time = time;
    }
}