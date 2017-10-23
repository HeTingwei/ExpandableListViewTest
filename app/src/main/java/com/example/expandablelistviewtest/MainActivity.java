package com.example.expandablelistviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;

    public static final String[] BOOKS = {"西游记", "水浒传", "三国演义", "红楼梦"};
    public static final String[][] FIGURES = {
            {"唐三藏", "孙悟空", "猪八戒", "沙和尚"},
            {"宋江", "林冲", "李逵", "鲁智深"},
            {"曹操", "刘备", "孙权", "诸葛亮", "周瑜"},
            {"贾宝玉", "林黛玉", "薛宝钗", "王熙凤"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        expandableListView= (ExpandableListView) findViewById(R.id.expandableListView);
        final ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(BOOKS, FIGURES);
        expandableListView.setAdapter(expandableListAdapter);

        //点击子项
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Toast.makeText(MainActivity.this,FIGURES[groupPosition][childPosition] , Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //点击组
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                boolean isExpand = expandableListView.isGroupExpanded(groupPosition);
                expandableListAdapter.setIndicatorState(groupPosition,isExpand);
                return false;//当返回true时，无法展开下一级列表
            }
        });
    }


}
