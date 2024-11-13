package com.quizzes.mytutor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> courseList;
    private Context context;
    private SharedPreferences sharedPreferences;

    public CourseAdapter(List<Course> courseList, Context context) {
        this.courseList = courseList;
        this.context = context;
        sharedPreferences = context.getSharedPreferences("SelectedCourses", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);

        holder.courseImage.setImageResource(course.getImageResId());
        holder.courseTitle.setText(course.getTitle());
        holder.courseCheckBox.setChecked(course.isSelected());

        // Set selection state
        holder.courseCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            course.setSelected(isChecked);
            saveSelection(course.getTitle(), isChecked);
        });

        // Open course content on item click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CourseContentActivity.class);
            intent.putExtra("courseTitle", course.getTitle());
            intent.putExtra("courseImageResId", course.getImageResId());
            context.startActivity(intent);
        });
    }

    private void saveSelection(String title, boolean isSelected) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(title, isSelected);
        editor.apply();
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        ImageView courseImage;
        TextView courseTitle;
        CheckBox courseCheckBox;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseImage = itemView.findViewById(R.id.courseImage);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseCheckBox = itemView.findViewById(R.id.courseCheckBox);
        }
    }
}