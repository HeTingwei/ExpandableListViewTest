package com.example.expandablelistviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    public static final String[] BOOKS = {"西游记", "水浒传", "三国演义", "红楼梦"};
    public static final String[][] FIGURES = {
            {"唐三藏", "孙悟空", "猪八戒", "沙和尚"},
            {"宋江", "林冲", "李逵", "鲁智深"},
            {"曹操", "刘备", "孙权", "诸葛亮", "周瑜"},
            {"贾宝玉", "林黛玉", "薛宝钗", "王熙凤"}
    };
    private  ArrayList<String> BOOKSArray ;//= Arrays.asList("西游记", "水浒传", "三国演义", "红楼梦");
    public  ArrayList<ArrayList<String>> FIGURESArray;


    //初始化ArrayList
    private void initArrayList() {
        BOOKSArray = new ArrayList<>();
        for (int i = 0; i <BOOKS.length ; i++) {
            BOOKSArray.add(BOOKS[i]);
        }
    }
    private void initAA(){
        FIGURESArray = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < FIGURES.length; i++) {
            ArrayList<String>temp=new ArrayList<>();
            for (int j = 0; j <FIGURES[i].length ; j++) {
                temp.add(FIGURES[i][j]);
            }
            FIGURESArray.add(temp);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initArrayList();
        initAA();
        initView();
    }

    private void initView() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListAdapter = new ExpandableListAdapter(BOOKSArray, FIGURESArray);
        expandableListView.setAdapter(expandableListAdapter);

        //点击子项（展开的项）
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Toast.makeText(MainActivity.this, expandableListAdapter.getChildDataString(groupPosition,childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //点击组项
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                Toast.makeText(MainActivity.this, expandableListAdapter.getGroupDataString(groupPosition), Toast.LENGTH_SHORT).show();
                boolean isExpand = expandableListView.isGroupExpanded(groupPosition);
                expandableListAdapter.setIndicatorState(groupPosition, isExpand);
                return false;//当返回true时，无法展开下一级列表
            }
        });
    }

    public void click(View v) {
        ArrayList<String> child=new ArrayList<>() ;
        child.add("继承");
        child.add("多态");
        child.add("接口");
        expandableListAdapter.addGroup("Java入门", child);
    }

    public void removeClick(View v) {
        expandableListAdapter.removeChild(0,0);
    }

}
