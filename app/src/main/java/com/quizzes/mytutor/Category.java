package com.quizzes.mytutor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class Category extends Fragment {

    public Category() {
        // Required empty public constructor
    }
    private GridView catView;
    private List<CategoryModel> catlist = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        catView = view.findViewById(R.id.category_grid);

        loadCategories();

        CategoryAdapter adapter = new CategoryAdapter(catlist);
        catView.setAdapter(adapter);

        return view;
    }

    private void loadCategories(){
        catlist.clear();

        catlist.add(new CategoryModel("1","AI",13));
        catlist.add(new CategoryModel("2","PROGRAMMING",16));
        catlist.add(new CategoryModel("3","ENVIRONMENT",12));
        catlist.add(new CategoryModel("4","ROBOTICS",8));
        catlist.add(new CategoryModel("5","ART & DESIGN",10));
        catlist.add(new CategoryModel("6","MATH",14));

    }
}