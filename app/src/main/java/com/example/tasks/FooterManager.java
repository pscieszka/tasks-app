package com.example.tasks;

import android.content.Context;
import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class FooterManager {

    private Context context;
    private LinearLayout footerLayout;
    private ImageButton homeButton;
    private ImageButton addButton;
    private ImageButton calendarButton;
    private ImageButton statisticsButton;
    private ImageButton settingsButton;

    public FooterManager(Context context, LinearLayout footerLayout) {
        this.context = context;
        this.footerLayout = footerLayout;
        initViews();
        setupListeners();
    }

    private void initViews() {
        homeButton = footerLayout.findViewById(R.id.homeButton);
        addButton = footerLayout.findViewById(R.id.addButton);
        calendarButton = footerLayout.findViewById(R.id.calendarButton);
        statisticsButton = footerLayout.findViewById(R.id.statisticsButton);
        settingsButton = footerLayout.findViewById(R.id.settingsButton);
    }

    private void setupListeners() {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveButton(homeButton);
                if (!isCurrentActivity(MainActivity.class)) {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveButton(addButton);
                if (!isCurrentActivity(AddActivity.class)) {
                    Intent intent = new Intent(context, AddActivity.class);
                    context.startActivity(intent);
                }

            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setActiveButton(calendarButton);
                if (!isCurrentActivity(CalendarActivity.class)) {
                    Intent intent = new Intent(context, CalendarActivity.class);
                    context.startActivity(intent);
                }
            }
        });
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveButton(statisticsButton);
                if (!isCurrentActivity(StatsActivity.class)) {
                    Intent intent = new Intent(context, StatsActivity.class);
                    context.startActivity(intent);
                }
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveButton(settingsButton);
                if (!isCurrentActivity(SettingsActivity.class)) {
                    Intent intent = new Intent(context, SettingsActivity.class);
                    context.startActivity(intent);
                }
            }
        });



    }

    public void setActiveButton(ImageButton activeButton) {
        resetButtonBackgrounds();
        activeButton.setBackgroundColor(Constants.WHITE);
    }

    private void resetButtonBackgrounds() {
        homeButton.setBackgroundColor(Color.TRANSPARENT);
        addButton.setBackgroundColor(Color.TRANSPARENT);
        calendarButton.setBackgroundColor(Color.TRANSPARENT);
        statisticsButton.setBackgroundColor(Color.TRANSPARENT);
        settingsButton.setBackgroundColor(Color.TRANSPARENT);
    }
    private boolean isCurrentActivity(Class<?> activityClass) {
        return context instanceof AppCompatActivity &&
                ((AppCompatActivity) context).getClass().equals(activityClass);
    }

}
