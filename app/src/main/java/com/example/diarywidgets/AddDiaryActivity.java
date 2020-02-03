package com.example.diarywidgets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diarywidgets.adapter.DiaryAdapter;
import com.example.diarywidgets.adapter.MyAdapter;
import com.example.diarywidgets.database.DiaryDataSource;
import com.example.diarywidgets.model.Diary;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDiaryActivity extends AppCompatActivity {
    EditText etTite, etDescription;
    int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @BindView(R.id.swipe_view)
    SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerView;
    MyAdapter adapter;
    ArrayList<Diary> data;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        etTite = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        recyclerView = findViewById(R.id.recylcerview);
        DiaryDataSource dds = new DiaryDataSource(this);
        ButterKnife.bind(this);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //Define your work here .

                swipeRefreshLayout.setRefreshing(false);

            }
        });
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "FAB Clicked", Snackbar.LENGTH_LONG).show();
            }
        });

        data = dds.getAllDiaries();

        adapter = new MyAdapter(this, data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SwipeHelper swipeHelper = new SwipeHelper(this, recyclerView) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Delete",
                        0,
                        Color.parseColor("#FF3C30"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: onDelete
                                Toast.makeText(AddDiaryActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                            }
                        }
                ));

                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Transfer",
                        0,
                        Color.parseColor("#FF9502"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: OnTransfer
                                Toast.makeText(AddDiaryActivity.this, "Transfer", Toast.LENGTH_SHORT).show();
                            }
                        }
                ));
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Unshare",
                        0,
                        Color.parseColor("#C7C7CB"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: OnUnshare
                                Toast.makeText(AddDiaryActivity.this, "Unshare", Toast.LENGTH_SHORT).show();
                            }
                        }
                ));
            }
        };


        Intent intent = getIntent();
        appWidgetId = intent.getIntExtra("appWidgetId", 0);
    }

    public void Click(View view) {
        if (etTite.getText().toString().equals("")) {
            Snackbar.make(view, "عنوان را وارد نمایید.", Snackbar.LENGTH_LONG).show();
            return;
        }
        DiaryDataSource dds = new DiaryDataSource(this);
        Diary diary = new Diary();
        diary.setTitle(etTite.getText().toString());
        diary.setDescription(etDescription.getText().toString());
        dds.addDiary(diary);

        Intent intent_DiaryWidget = new Intent(this, DiaryWidgetProvider.class);
        intent_DiaryWidget.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        sendBroadcast(intent_DiaryWidget);

        finish();
    }


}
