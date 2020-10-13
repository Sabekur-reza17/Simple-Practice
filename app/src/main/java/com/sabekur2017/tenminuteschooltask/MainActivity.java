package com.sabekur2017.tenminuteschooltask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    LinearLayout videolay,documentlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        videolay=findViewById(R.id.lay_videolayout);
        documentlay=findViewById(R.id.lay_documentlay);
        recyclerAdapter = new RecyclerAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setData(getList());
        videolay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Video item", Toast.LENGTH_SHORT).show();
            }
        });
        documentlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Document Item", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public ArrayList<ChapterModel> getList() {
        ArrayList<ChapterModel> models = new ArrayList<>();
        ChapterModel model1_2 = new ChapterModel("Chapters", 1);
        ChapterModel model1_2_1 = new ChapterModel("Chapters 1", 2);
        ChapterModel model1_chapter_2 = new ChapterModel("Chapters 2", 2);

        ChapterModel model1_2_1_1 = new ChapterModel("Video 1_1", 3);
        ChapterModel model1_2_1_2 = new ChapterModel("Document 1_1", 3);
        ChapterModel model1_2_1_3 = new ChapterModel("Video 1_2", 3);

        ChapterModel model1_2_d_1 = new ChapterModel("Video 2_2", 3);
        ChapterModel model1_2_d_2 = new ChapterModel("Document 2_2", 3);
        ChapterModel model1_2_d_3 = new ChapterModel("Video 2_2", 3);


        model1_2_1.chapterModels.add(model1_2_1_1);
        model1_2_1.chapterModels.add(model1_2_1_2);
        model1_2_1.chapterModels.add(model1_2_1_3);

        model1_chapter_2.chapterModels.add(model1_2_d_1);
        model1_chapter_2.chapterModels.add(model1_2_d_2);
        model1_chapter_2.chapterModels.add(model1_2_d_3);

        model1_2.chapterModels.add(model1_2_1);
        model1_2.chapterModels.add(model1_chapter_2);
        models.add(model1_2);
        return models;
    }


}