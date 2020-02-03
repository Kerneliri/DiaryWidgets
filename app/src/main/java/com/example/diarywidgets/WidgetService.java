package com.example.diarywidgets;


import android.content.Intent;
import android.widget.RemoteViewsService;


import com.example.diarywidgets.adapter.DiaryAdapter;





public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new DiaryAdapter(this, intent);
    }
}
