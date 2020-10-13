package com.sabekur2017.tenminuteschooltask;

import java.util.ArrayList;

public class ChapterModel {
    public enum STATE {
        CLOSED,
        OPENED
    }
    String name;
    int level;
    STATE state = STATE.CLOSED;
    ArrayList<ChapterModel> chapterModels=new ArrayList<>();
    public ChapterModel(String name, int level) {
        this.name = name;
        this.level = level;
    }
}
