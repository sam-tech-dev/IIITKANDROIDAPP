package com.ssverma.iiitkota.admission;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import com.ssverma.iiitkota.R;

/**
 * Created by IIITK on 6/12/2016.
 */
public class ExpandableList_Adapter extends BaseExpandableListAdapter{

    private Context context;
    private ArrayList<String> ques;
    private ArrayList<String> ans;

    ExpandableList_Adapter(Context context , ArrayList<String> ques , ArrayList<String> ans){
        this.context = context;
        this.ques = ques;
        this.ans = ans;
    }

    @Override
    public int getGroupCount() {
        return ques.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1; // every time only one answer of each question
    }

    @Override
    public Object getGroup(int groupPosition) {
        return ques.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return ans.get(groupPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_header_group , null);
        }

        TextView question = (TextView) convertView.findViewById(R.id.question);
        question.setText((String) getGroup(groupPosition));
        TextView counter = (TextView) convertView.findViewById(R.id.counter);
        counter.setText((groupPosition + 1) + ".");

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_item , null);
        }

        TextView answer = (TextView) convertView.findViewById(R.id.answer);
        answer.setText((CharSequence) getChild(groupPosition , childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
