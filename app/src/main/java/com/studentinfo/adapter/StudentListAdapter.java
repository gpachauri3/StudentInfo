package com.studentinfo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.studentinfo.R;
import com.studentinfo.model.Student;

import java.util.ArrayList;

/**
 * Created by osp158 on 4/4/17.
 */

public class StudentListAdapter extends ArrayAdapter<Student> {

    private ArrayList<Student> studentArrayList=new ArrayList<>();
    private Context context;


    public StudentListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        this.context=context;
    }

    public void setStudentArrayList(ArrayList<Student> studentArrayList){
        this.studentArrayList=studentArrayList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.tv_name);
        TextView rollNo = (TextView) rowView.findViewById(R.id.tv_rollNo);
        TextView schoolName = (TextView) rowView.findViewById(R.id.tv_schoolName);
        TextView email = (TextView) rowView.findViewById(R.id.tv_email);

        name.setText("Name:"+studentArrayList.get(position).getName());
        rollNo.setText("Roll No:"+studentArrayList.get(position).getRollNo()+"");
        schoolName.setText("School Name:"+studentArrayList.get(position).getSchollName());
        email.setText("Email:"+studentArrayList.get(position).getEmail());

        return rowView;
    }

    @Override
    public int getCount() {
        return studentArrayList.size();
    }
}
