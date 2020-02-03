package com.example.diarywidgets.model;

public class Diary {
    private int Diaryid;
    private String Title;
    private String Description;

    public Diary() {
    }

    public Diary(int diaryid, String title, String description) {
        Diaryid = diaryid;
        Title = title;
        Description = description;
    }

    public int getDiaryid() {
        return Diaryid;
    }

    public void setDiaryid(int diaryid) {
        Diaryid = diaryid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
