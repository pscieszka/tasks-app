package com.example.tasks;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {
    private DailyTaskManager dailyTaskManager;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView calendarView = findViewById(R.id.calendarView);
        LinearLayout taskContainer = findViewById(R.id.taskContainer);
        dailyTaskManager = new DailyTaskManager(this, taskContainer);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        selectedDate = sdf.format(new Date());

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
            dailyTaskManager.displayTasks(selectedDate);
            Toast.makeText(CalendarActivity.this, "Selected date: " + selectedDate, Toast.LENGTH_SHORT).show();
        });

        dailyTaskManager.addTask(selectedDate, "Task 1", "Description for Task 1",true);
        dailyTaskManager.addTask(selectedDate, "Task 2", "Description for Task 2",true);
        dailyTaskManager.addTask(selectedDate, "Task 3", "Description for Task 3",true);

        dailyTaskManager.displayTasks(selectedDate);
    }
}

