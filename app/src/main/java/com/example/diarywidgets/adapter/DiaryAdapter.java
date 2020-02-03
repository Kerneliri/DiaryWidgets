package com.example.diarywidgets.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.TextView;

import com.example.diarywidgets.R;
import com.example.diarywidgets.database.DiaryDataSource;
import com.example.diarywidgets.model.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryAdapter implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = "WidgetDataProvider";

    List<String> mCollection = new ArrayList<>();
    Context mContext = null;

    public DiaryAdapter(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mCollection.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                R.layout.inflate_diary);
        view.setTextViewText(R.id.tvTitle, mCollection.get(position));


        Intent fillInIntent = new Intent();
        fillInIntent.putExtra("name", mCollection.get(position));
        view.setOnClickFillInIntent(R.id.tvTitle, fillInIntent);
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private void initData() {
        mCollection.clear();
        DiaryDataSource dds = new DiaryDataSource(mContext);
        List<Diary> diary_all = dds.getAllDiaries();
        for (Diary diary : diary_all) {
            mCollection.add(diary.getTitle());
        }
    }
}
