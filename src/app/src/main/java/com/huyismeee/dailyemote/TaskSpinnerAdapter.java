package com.huyismeee.dailyemote;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.huyismeee.dailyemote.database.TaskTimer;

import java.util.ArrayList;

public class TaskSpinnerAdapter extends ArrayAdapter<TaskTimer> {
    public TaskSpinnerAdapter(Context context, ArrayList<TaskTimer> taskList) {
        super(context, android.R.layout.simple_spinner_item, taskList);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        TaskTimer task = getItem(position);
        if (task != null) {
            textView.setText(task.getNametag());
        }
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
        TaskTimer task = getItem(position);
        if (task != null) {
            textView.setText(task.getNametag());
        }
        return textView;
    }
}

