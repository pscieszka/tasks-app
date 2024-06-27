package com.example.tasks;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

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
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveButton(addButton);
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveButton(calendarButton);
            }
        });
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveButton(statisticsButton);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveButton(settingsButton);
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

}
