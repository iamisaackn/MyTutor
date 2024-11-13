package com.quizzes.mytutor;

public class CategoryModel {
    private String docID;
    private String Name;
    private int Numtest;

    public CategoryModel( String docID,String name, int numtest) {
        this.docID = docID;
        Name = name;
        Numtest = numtest;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNumtest() {
        return Numtest;
    }

    public void setNumtest(int numtest) {
        Numtest = numtest;
    }
}
