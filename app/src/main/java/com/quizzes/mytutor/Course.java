package com.quizzes.mytutor;

public class Course {
    private final String title;
    private final int imageResId;
    private boolean isSelected;

    public Course(String title, int imageResId, boolean isSelected) {
        this.title = title;
        this.imageResId = imageResId;
        this.isSelected = isSelected;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public int getImageResId() { return imageResId; }
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
}