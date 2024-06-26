package com.example.tasks;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TaskManager taskManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout taskContainer = findViewById(R.id.taskContainer);
        taskManager = new TaskManager(this, taskContainer);
        for(int i =0 ; i<20; i++){
            taskManager.addTask("Task "+ i , "Description");
        }


    }
}