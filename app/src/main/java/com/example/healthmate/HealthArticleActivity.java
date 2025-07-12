package com.example.healthmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class    HealthArticleActivity extends AppCompatActivity {

    private String [][] health_details = {
            {"Walking daily", "Boost your physical and mental health with a simple daily walk.","",""," CLick for More Details"},
            {"All about Mental Health", "Understand and nurture your emotional well-being.","",""," CLick for More Details"},
            {"Healthy Living Habits", "Small everyday choices that lead to a healthier life.","",""," CLick for More Details"},
            {"Mentrual Health", "Learn to manage periods and hormonal health effectively.","",""," CLick for More Details"},
            {"The Good Mood Guide", "Simple tips to lift your mood and stay positive.","",""," CLick for More Details"}

    };
    private int[] images = {
           R.drawable.healthart1,
            R.drawable.healthart2,
            R.drawable.healthart3,
            R.drawable.healthart4,
            R.drawable.healthart5
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnBack;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_article);

        ListView lst = findViewById(R.id.listViewHA);
        btnBack = findViewById(R.id.buttonHABack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticleActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i =0 ; i<health_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",health_details[i][0]);
            item.put("line2",health_details[i][1]);
            item.put("line3",health_details[i][2]);
            item.put("line4",health_details[i][3]);
            item.put("line5",health_details[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(HealthArticleActivity.this, HealthArticlesDetailsActivity.class);
                it.putExtra("text1", health_details[i][0]);
                it.putExtra("text2",images[i]);
                startActivity(it);
            }
        });
    }
}