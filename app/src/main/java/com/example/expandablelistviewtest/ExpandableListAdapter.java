package com.example.expandablelistviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeTingwei on 2017/10/23.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    String[] groupData;//组名
    String[][] childData;//字列表的子项数据
    List<ImageView> imgList;//暂存imageview对象，便于调整展开和收拢的样式

    public ExpandableListAdapter(String[] groupData, String[][] childData) {
        this.groupData = groupData;
        this.childData = childData;
        imgList = new ArrayList<>();
    }

    @Override
    public int getGroupCount() {
        return groupData.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childData[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {

        return groupData[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childData[groupPosition][childPosition];
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
        groupViewHolder.groupTv.setText(groupData[groupPosition]);
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

        childViewHolder.childTv.setText(childData[groupPosition][childPosition]);
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
        //最后一项有问题，前面imglist最后两项：最后一个对，倒数第二个没用。imgList多出一项（倒数第二个）
        if (groupPosition == groupData.length - 1) {
            if (isExpanded) {
                imgList.get(groupPosition+1).setImageResource(R.drawable.ic_expand_less);
            } else {
                imgList.get(groupPosition+1).setImageResource(R.drawable.ic_expand_more);
            }
        }

    }
}
