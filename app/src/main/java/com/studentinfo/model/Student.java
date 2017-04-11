package com.studentinfo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by osp158 on 4/4/17.
 */

public class Student implements Parcelable {

    private int rollNo=0;
    private String name="";
    private String schollName="";
    private String email="";
    private String gender="";


    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchollName() {
        return schollName;
    }

    public void setSchollName(String schollName) {
        this.schollName = schollName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.rollNo);
        dest.writeString(this.name);
        dest.writeString(this.schollName);
        dest.writeString(this.email);
        dest.writeString(this.gender);
    }

    public Student() {
    }

    protected Student(Parcel in) {
        this.rollNo = in.readInt();
        this.name = in.readString();
        this.schollName = in.readString();
        this.email = in.readString();
        this.gender = in.readString();
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
