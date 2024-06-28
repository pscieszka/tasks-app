package com.example.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyTaskManager {
    private Map<String, List<Task>> dailyTasks;
    private LinearLayout taskContainer;
    private LayoutInflater inflater;
    private Context context;

    public DailyTaskManager(Context context, LinearLayout taskContainer) {
        this.dailyTasks = new HashMap<>();
        this.taskContainer = taskContainer;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void addTask(String date, String title, String description, boolean status) {
        List<Task> tasks = dailyTasks.getOrDefault(date, new ArrayList<>());
        Task newTask = new Task(title, description, status);
        tasks.add(newTask);
        dailyTasks.put(date, tasks);
    }

    public void deleteTask(String date, int index) {
        List<Task> tasks = dailyTasks.get(date);
        if (tasks != null && index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public void updateTask(String date, int index, String newTitle, String newDescription) {
        List<Task> tasks = dailyTasks.get(date);
        if (tasks != null && index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setTitle(newTitle);
            task.setDescription(newDescription);
        }
    }

    public void displayTasks(String date) {
        taskContainer.removeAllViews();
        List<Task> tasks = dailyTasks.get(date);
        if (tasks != null) {
            for (Task task : tasks) {
                View taskView = createTaskView(task);
                taskContainer.addView(taskView);
            }
        }
    }

    private View createTaskView(Task task) {
        View taskView = inflater.inflate(R.layout.task_card, null);
        TextView titleView = taskView.findViewById(R.id.taskTitle);
        TextView descriptionView = taskView.findViewById(R.id.taskDescription);

        titleView.setText(task.getTitle());
        descriptionView.setText(task.getDescription());

        taskView.setOnClickListener(v -> {
            String taskName = task.getTitle();
            String taskDescription = task.getDescription();

            TaskDialogFragment taskDialogFragment = TaskDialogFragment.newInstance(taskName, taskDescription);
            taskDialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "TaskDialogFragment");
        });

        return taskView;
    }

    public double getPercentageDone(String date) {
        List<Task> tasks = dailyTasks.get(date);
        if (tasks == null || tasks.isEmpty()) {
            return 100.0;
        }
        int cnt = 0;
        for (Task task : tasks) {
            if (task.getStatus()) {
                cnt++;
            }
        }
        double percentage = (cnt / (double) tasks.size()) * 100;

        String formattedPercentage = String.format("%.2f", percentage);
        return Double.parseDouble(formattedPercentage);
    }
}
