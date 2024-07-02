package com.example.tasks;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class TaskDialogFragment extends DialogFragment {
    private static final String ARG_TASK_NAME = "task_name";
    private static final String ARG_TASK_DESCRIPTION = "task_description";
    private static final String ARG_TASK_DATE = "task_date";
    private static final String ARG_TASK_INDEX = "task_index";

    private DailyTaskManager dailyTaskManager;
    private String taskDate;
    private int taskIndex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.TaskDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.task_dialog_fragment, container, false);

        String taskName = getArguments().getString(ARG_TASK_NAME);
        String taskDescription = getArguments().getString(ARG_TASK_DESCRIPTION);
        taskDate = getArguments().getString(ARG_TASK_DATE);
        taskIndex = getArguments().getInt(ARG_TASK_INDEX);

        TextView taskNameTextView = view.findViewById(R.id.taskName);
        TextView taskDescriptionTextView = view.findViewById(R.id.taskDescription);
        Button deleteTaskButton = view.findViewById(R.id.deleteTask);

        taskNameTextView.setText(taskName);
        taskDescriptionTextView.setText(taskDescription);

        deleteTaskButton.setOnClickListener(v -> {
            dailyTaskManager.deleteTask(taskDate, taskIndex);
            dailyTaskManager.displayTasks(taskDate);
            dismiss();
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    public static TaskDialogFragment newInstance(String taskName, String taskDescription, String taskDate, int taskIndex) {
        TaskDialogFragment fragment = new TaskDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TASK_NAME, taskName);
        args.putString(ARG_TASK_DESCRIPTION, taskDescription);
        args.putString(ARG_TASK_DATE, taskDate);
        args.putInt(ARG_TASK_INDEX, taskIndex);
        fragment.setArguments(args);
        return fragment;
    }

    public void setDailyTaskManager(DailyTaskManager dailyTaskManager) {
        this.dailyTaskManager = dailyTaskManager;
    }
}
