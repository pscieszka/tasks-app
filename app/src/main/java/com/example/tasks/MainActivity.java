package com.example.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private DailyTaskManager dailyTaskManager;
    private PercentageCircleView percentageCircleView;
    private FooterManager footerManager;

    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout footerLayout = findViewById(R.id.footer);
        footerManager = new FooterManager(this, footerLayout);

        percentageCircleView = findViewById(R.id.percentageCircle);
        footerManager.setActiveButton(findViewById(R.id.homeButton));

        LinearLayout taskContainer = findViewById(R.id.taskContainer);
        dailyTaskManager = new DailyTaskManager(this, taskContainer);

        selectedDate = getCurrentDate();

        dailyTaskManager.displayTasks(selectedDate);
        updatePercentage(selectedDate);

        handleNewTask();
    }

    private void handleNewTask() {
        Intent intent = getIntent();
        if (intent.hasExtra("title") && intent.hasExtra("description")) {
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            boolean status = intent.getBooleanExtra("status", false);

            dailyTaskManager.addTask(selectedDate, title, description, status);
            dailyTaskManager.displayTasks(selectedDate);

            updatePercentage(selectedDate);
        }
    }

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }

    public void updatePercentage(String date) {
        double percentage = dailyTaskManager.getPercentageDone(date);
        percentageCircleView.setPercentage((int) percentage);
    }
}
