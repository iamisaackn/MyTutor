package com.quizzes.mytutor;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private RecyclerView testView;
    private Toolbar toolbar;
    private List<TestModel> testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enables edge-to-edge display
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);

        // Setup Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // Get category index from Intent extras
        int catIndex = getIntent().getIntExtra("CAT_INDEX", 0);
        getSupportActionBar().setTitle(CategoryFragment.catList.get(catIndex).getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Setup RecyclerView
        testView = findViewById(R.id.test_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        testView.setLayoutManager(layoutManager);

        // Load test data into the list
        loadTestData();

        // Set the adapter for RecyclerView
        TestAdapter adapter = new TestAdapter(testList);
        testView.setAdapter(adapter);
    }

    // Method to load test data into the list
    private void loadTestData() {
        testList = new ArrayList<>();
        testList.add(new TestModel("1", 50, 20));
        testList.add(new TestModel("2", 80, 20));
        testList.add(new TestModel("3", 0, 25));
        testList.add(new TestModel("4", 10, 40));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle toolbar back button click
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close the activity and go back
        }
        return super.onOptionsItemSelected(item);
    }
}