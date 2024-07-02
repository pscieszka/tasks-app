package com.example.tasks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    private FooterManager footerManager;
    private DailyTaskManager dailyTaskManager;

    private EditText titleInput;
    private EditText descriptionInput;
    private EditText dueDateInput;
    private EditText estimatedTimeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        LinearLayout footerLayout = findViewById(R.id.footer);
        footerManager = new FooterManager(this, footerLayout);

        footerManager.setActiveButton(findViewById(R.id.addButton));

        dailyTaskManager = new DailyTaskManager(this, findViewById(R.id.taskContainer));

        titleInput = findViewById(R.id.title_input);
        descriptionInput = findViewById(R.id.description_input);
        dueDateInput = findViewById(R.id.due_date_input);
        estimatedTimeInput = findViewById(R.id.estimated_time_input);

        findViewById(R.id.add_task_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        String title = titleInput.getText().toString().trim();
        String description = descriptionInput.getText().toString().trim();
        String dueDate = dueDateInput.getText().toString().trim();
        String estimatedTime = estimatedTimeInput.getText().toString().trim();
        System.out.println(title+description+dueDate+estimatedTime);

        if (title.isEmpty() || description.isEmpty() || dueDate.isEmpty() || estimatedTime.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        dailyTaskManager.addTask(getFormattedDate(), title, description, false);


    }

    private String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }
}
