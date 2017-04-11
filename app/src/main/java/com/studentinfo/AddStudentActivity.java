package com.studentinfo;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.studentinfo.model.Student;

public class AddStudentActivity extends AppCompatActivity {

    private EditText nameEt ,rollNoEt ,schoolNameEt ,emailEt;
    private TextView saveStudent;
    private RadioGroup genderRadioGroup;
    private RadioButton radioMale ,radioFemale;

    private String name= "", schoolName="", email="";
    private int rollNo=0;

    private Student student=new Student();

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        nameEt=(EditText)findViewById(R.id.et_name);
        rollNoEt=(EditText)findViewById(R.id.et_rollNo);
        schoolNameEt=(EditText)findViewById(R.id.et_schoolName);
        emailEt=(EditText)findViewById(R.id.et_email);
        genderRadioGroup=(RadioGroup)findViewById(R.id.rg_gender);
        radioMale=(RadioButton)findViewById(R.id.radio_male);
        radioFemale=(RadioButton)findViewById(R.id.radio_female);
        saveStudent=(TextView)findViewById(R.id.saveStudent);

        if (getSupportActionBar() != null) {
            toolbar=(Toolbar)findViewById(R.id.saveStudentToolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Save Student");
        }


        radioMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    student.setGender("MALE");
                }
            }
        });

        radioFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    student.setGender("FEMALE");
                }
            }
        });


        saveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shallWeProceed()){
                    HomeActivity.studentArrayList.add(student);
                    Toast.makeText(AddStudentActivity.this, "Student successfully added", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private boolean shallWeProceed() {
        name=nameEt.getText().toString();
        email=emailEt.getText().toString();
        schoolName=schoolNameEt.getText().toString();

        if(!rollNoEt.getText().toString().equalsIgnoreCase("")){
            rollNo=Integer.parseInt(rollNoEt.getText().toString());
        }

        if(name!=null && !name.equalsIgnoreCase("")){
            student.setName(name);
        }else{
            nameEt.setError("please provide name");
        }

        if(rollNo!=0){
            student.setRollNo(rollNo);
        }else{
            rollNoEt.setError("please provide roll number");
        }

        if(schoolName!=null && !schoolName.equalsIgnoreCase("")){
            student.setSchollName(schoolName);
        }else{
            schoolNameEt.setError("please provide school name");
        }

        if(email!=null && !email.equalsIgnoreCase("")){
            student.setEmail(email);
        }else{
            emailEt.setError("please provide email");
        }

        if(student.getRollNo()!=0 && !student.getName().equalsIgnoreCase("") && !student.getSchollName().equalsIgnoreCase("") && !student.getEmail().equalsIgnoreCase("")){
            return true;
        }else{
            return false;
        }
    }
}
