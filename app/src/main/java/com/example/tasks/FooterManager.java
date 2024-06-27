package com.example.tasks;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class FooterManager {

    private Context context;
    private LinearLayout footerLayout;
    private ImageButton homeButton;
    private ImageButton addButton;
    private ImageButton calendarButton;

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
    }

    public void setActiveButton(ImageButton activeButton) {
        resetButtonBackgrounds();

        activeButton.setBackgroundColor(Constants.PURPLE);
    }

    private void resetButtonBackgrounds() {
        homeButton.setBackgroundColor(Color.TRANSPARENT);
        addButton.setBackgroundColor(Color.TRANSPARENT);
        calendarButton.setBackgroundColor(Color.TRANSPARENT);
    }
}
