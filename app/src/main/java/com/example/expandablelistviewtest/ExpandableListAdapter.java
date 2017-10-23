package com.example.expandablelistviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HeTingwei on 2017/10/23.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    ArrayList<String> groupData;//组名
    ArrayList<ArrayList<String>> childData;//字列表的子项数据
    ArrayList<ImageView> imgList;//暂存imageview对象，便于调整展开和收拢的样式

    public ExpandableListAdapter(ArrayList<String>groupData, ArrayList<ArrayList<String>> childData) {
        this.groupData = groupData;
        this.childData = childData;
        imgList = new ArrayList<>();
    }

    @Override
    public int getGroupCount() {
        return groupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return groupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_layout, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.img = convertView.findViewById(R.id.img);
            groupViewHolder.groupTv = convertView.findViewById(R.id.group_tv);
            imgList.add(groupViewHolder.img);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.groupTv.setText(groupData.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_layout, parent, false);
            childViewHolder.childTv = convertView.findViewById(R.id.child_tv);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        childViewHolder.childTv.setText(childData.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private  class GroupViewHolder {
        TextView groupTv;
        ImageView img;
    }

    private  class ChildViewHolder {
        TextView childTv;
    }

    //根据分组的展开闭合状态设置指示器
    public void setIndicatorState(int groupPosition, boolean isExpanded) {
        if (isExpanded) {
            imgList.get(groupPosition).setImageResource(R.drawable.ic_expand_less);
        } else {
            imgList.get(groupPosition).setImageResource(R.drawable.ic_expand_more);
        }

    }
/*
*
* 不是必须的部分
*
*
* */

    //添加一组
    public void addGroup(String group,ArrayList<String>child) {
        groupData.add(group);
        childData.add(child);
        notifyDataSetChanged();
    }

    //去掉对应位置子项
    public void removeChild(int groupPosition,int childPosition){
        if (groupPosition >= groupData.size() || childData.get(groupPosition).size() <= childPosition||groupPosition<0||childPosition<0) {
            return;
        }

        childData.get(groupPosition).remove(childPosition);
        notifyDataSetChanged();
    }

    //获取对应位置组的内容字符串
    public   String getGroupDataString(int groupPosition){
        if (groupPosition >= groupData.size()||groupPosition<0) {
            return null;
        }
        return  groupData.get(groupPosition);
    }
    //获取对应位置子项的内容字符串
    public String getChildDataString(int groupPosition,int childPosition){
        if (groupPosition >= groupData.size() || childData.get(groupPosition).size() <= childPosition||groupPosition<0||childPosition<0) {
            return null;
        }
        return  childData.get(groupPosition).get(childPosition);
    }

}
