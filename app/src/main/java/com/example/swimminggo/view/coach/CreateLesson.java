package com.example.swimminggo.view.coach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.CustomExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateLesson extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> listdataHeader;
    HashMap<String,List<String>> listdataChild;
    CustomExpandableListView customExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lesson);
        addControl();
        customExpandableListView = new CustomExpandableListView(CreateLesson.this,listdataHeader,listdataChild);
        expandableListView.setAdapter(customExpandableListView);
        clickGroup();
        clickChild();
        closeGroup();
        openGroup();
    }

    private void openGroup() {
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupP) {
                //su kien mo group
            }
        });
    }

    private void closeGroup() {
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupP) {
                //su kien dong group
            }
        });
    }

    private void clickChild() {
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupP, int childP, long l) {
                //su kien click item
                return false;
            }
        });
    }

    private void clickGroup() {
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupP, long l) {
                //su kien click group
                return false;
            }
        });
    }

    private void addControl() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        listdataHeader = new ArrayList<>();
        listdataChild = new HashMap<String, List<String>>();

        listdataHeader.add("Khởi động");
        listdataHeader.add("Bơi chính");
        listdataHeader.add("Bơi phụ");
        listdataHeader.add("Thả lỏng");

        List<String> khoidong = new ArrayList<String>();
        khoidong.add("1");
        khoidong.add("2");
        khoidong.add("3");
        khoidong.add("4");

        List<String> boichinh = new ArrayList<String>();
        boichinh.add("1");

        List<String> boiphu = new ArrayList<String>();
        boiphu.add("1");

        List<String> thalong = new ArrayList<String>();
        thalong.add("1");

        listdataChild.put(listdataHeader.get(0),khoidong);
        listdataChild.put(listdataHeader.get(1),boichinh);
        listdataChild.put(listdataHeader.get(2),boiphu);
        listdataChild.put(listdataHeader.get(3),thalong);


    }
}

