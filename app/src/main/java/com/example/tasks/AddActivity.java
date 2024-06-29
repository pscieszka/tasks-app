package com.example.tasks;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    private FooterManager footerManager;
    private EditText taskDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        LinearLayout footerLayout = findViewById(R.id.footer);
        footerManager = new FooterManager(this, footerLayout);

        footerManager.setActiveButton(findViewById(R.id.addButton));



    }

}
