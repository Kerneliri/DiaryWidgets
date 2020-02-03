package com.example.diarywidgets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class DetailActivity extends AppCompatActivity {
    LinearLayout linearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        linearlayout = findViewById(R.id.linear);
        Snackbar.make(linearlayout.getRootView(), getIntent().getStringExtra("name"), Snackbar.LENGTH_LONG).show();

    }


}
