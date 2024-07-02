package com.example.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyTaskManager {
    private Map<String, List<Task>> dailyTasks;
    private LinearLayout taskContainer;
    private LayoutInflater inflater;
    private Context context;

    private static final String FILE_NAME = "daily_tasks.json";
    private static final Gson gson = new Gson();

    public DailyTaskManager(Context context, LinearLayout taskContainer) {
        this.dailyTasks = readTasksFromFile(context);
        if (this.dailyTasks == null) {
            this.dailyTasks = new HashMap<>();
        }
        this.taskContainer = taskContainer;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void addTask(String date, String title, String description, boolean status) {
        List<Task> tasks = dailyTasks.getOrDefault(date, new ArrayList<>());
        Task newTask = new Task(title, description, status);
        tasks.add(newTask);
        dailyTasks.put(date, tasks);
        saveTasksToFile();
    }

    public void deleteTask(String date, int index) {
        List<Task> tasks = dailyTasks.get(date);
        if (tasks != null && index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasksToFile();
        }
    }

    public void editTask(String date, int index, String newTitle, String newDescription) {
        List<Task> tasks = dailyTasks.get(date);
        if (tasks != null && index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            saveTasksToFile();
        }
    }

    public void updateTask(String date, int index, String newTitle, String newDescription) {
        List<Task> tasks = dailyTasks.get(date);
        if (tasks != null && index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            saveTasksToFile();
        }
    }

    public void displayTasks(String date) {
        taskContainer.removeAllViews();
        List<Task> tasks = dailyTasks.get(date);
        if (tasks != null) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                View taskView = createTaskView(task, date, i);
                taskContainer.addView(taskView);
            }
        }
    }

    private View createTaskView(Task task, String date, int index) {
        View taskView = inflater.inflate(R.layout.task_card, null);
        TextView titleView = taskView.findViewById(R.id.taskTitle);
        TextView descriptionView = taskView.findViewById(R.id.taskDescription);

        titleView.setText(task.getTitle());
        descriptionView.setText(task.getDescription());

        taskView.setOnClickListener(v -> {
            String taskName = task.getTitle();
            String taskDescription = task.getDescription();

            TaskDialogFragment taskDialogFragment = TaskDialogFragment.newInstance(taskName, taskDescription, date, index);
            taskDialogFragment.setDailyTaskManager(this);
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

    private void saveTasksToFile() {
        String json = gson.toJson(dailyTasks);
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Map<String, List<Task>> readTasksFromFile(Context context) {
        FileInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = context.openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fis));
            Type type = new TypeToken<Map<String, List<Task>>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
