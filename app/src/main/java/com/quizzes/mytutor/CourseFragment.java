package com.quizzes.mytutor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CourseFragment extends Fragment {

    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;
    private List<Course> courseList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewCourses);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Load and display all courses
        loadCourses();
        displayCourses(courseList);

        // Button to show selected courses only
        Button showSelectedButton = view.findViewById(R.id.btnShowSelected);
        showSelectedButton.setOnClickListener(v -> displayCourses(getSelectedCourses()));

        return view;
    }

    private void loadCourses() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("SelectedCourses", Context.MODE_PRIVATE);
        courseList = new ArrayList<>();
        courseList.add(new Course("Art", R.drawable.art_mytutor, sharedPreferences.getBoolean("Art", false)));
        courseList.add(new Course("AI", R.drawable.ai_mytutor, sharedPreferences.getBoolean("AI", false)));
        // Add more courses similarly...
    }

    private List<Course> getSelectedCourses() {
        List<Course> selectedCourses = new ArrayList<>();
        for (Course course : courseList) {
            if (course.isSelected()) {
                selectedCourses.add(course);
            }
        }
        return selectedCourses;
    }

    private void displayCourses(List<Course> coursesToShow) {
        courseAdapter = new CourseAdapter(coursesToShow, getContext());
        recyclerView.setAdapter(courseAdapter);
    }
}