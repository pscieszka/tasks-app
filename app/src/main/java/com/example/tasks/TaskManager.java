package com.example.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private LinearLayout taskContainer;
    private LayoutInflater inflater;

    public TaskManager(Context context, LinearLayout taskContainer) {
        this.tasks = new ArrayList<>();
        this.taskContainer = taskContainer;
        this.inflater = LayoutInflater.from(context);
    }

    public void addTask(String title, String description) {
        Task newTask = new Task(title, description);
        tasks.add(newTask);
        View taskView = createTaskView(newTask);
        taskContainer.addView(taskView);
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            taskContainer.removeViewAt(index);
        }
    }

    public void updateTask(int index, String newTitle, String newDescription) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setTitle(newTitle);
            task.setDescription(newDescription);

            View taskView = taskContainer.getChildAt(index);
            TextView titleView = taskView.findViewById(R.id.taskTitle);
            TextView descriptionView = taskView.findViewById(R.id.taskDescription);

            titleView.setText(newTitle);
            descriptionView.setText(newDescription);
        }
    }

    private View createTaskView(Task task) {
        View taskView = inflater.inflate(R.layout.task_card, null);
        TextView titleView = taskView.findViewById(R.id.taskTitle);
        TextView descriptionView = taskView.findViewById(R.id.taskDescription);

        titleView.setText(task.getTitle());
        descriptionView.setText(task.getDescription());

        return taskView;
    }

    private static class Task {
        private String title;
        private String description;

        public Task(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}

