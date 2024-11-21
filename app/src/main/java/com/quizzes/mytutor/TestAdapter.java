package com.quizzes.mytutor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starttest.QuestionActivity;
import com.example.starttest.R;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private List<TestModel> testList;

    // Constructor to initialize the test list
    public TestAdapter(List<TestModel> testList) {
        this.testList = testList;
    }

    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {
        // Bind data to the ViewHolder
        int progress = testList.get(position).getTopScore();
        holder.setData(position, progress);
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the list
        return testList.size();
    }

    // ViewHolder class to represent each item in the RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView testNo;
        private TextView topScore;
        private ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views
            testNo = itemView.findViewById(R.id.test_no);
            topScore = itemView.findViewById(R.id.scoretest);
            progressBar = itemView.findViewById(R.id.testProgressBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), QuestionActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        // Method to set data for each item
        private void setData(int pos, int progress) {
            testNo.setText("Test No " + (pos + 1));
            topScore.setText(progress + "%");
            progressBar.setProgress(progress);

            itemView.setOnClickListener(view -> {
                com.quizzes.mytutor.DbQuery.g_selected_test_index = pos;
                Intent intent = new Intent(itemView.getContext(), com.mtc.exampractice.StartTestActivity.class);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
