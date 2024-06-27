package com.example.tasks;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private DailyTaskManager dailyTaskManager;
    private PercentageCircleView percentageCircleView;

    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        percentageCircleView = findViewById(R.id.percentageCircle);

        LinearLayout taskContainer = findViewById(R.id.taskContainer);
        dailyTaskManager = new DailyTaskManager(this, taskContainer);

        selectedDate = getCurrentDate();

        dailyTaskManager.addTask(selectedDate, "Task 1", "Description for Task 1",true);
        dailyTaskManager.addTask(selectedDate, "Task 2", "Description for Task 2",true);
        dailyTaskManager.addTask(selectedDate, "Task 3", "Description for Task 3",false);

        dailyTaskManager.displayTasks(selectedDate);
        updatePercentage(selectedDate);
        ImageButton calendarButton = findViewById(R.id.calendarButton);
        calendarButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(intent);
        });
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }
    private void updatePercentage(String date) {
        double percentage = dailyTaskManager.getPercentageDone(date);
        percentageCircleView.setPercentage((int) percentage);
    }
}

