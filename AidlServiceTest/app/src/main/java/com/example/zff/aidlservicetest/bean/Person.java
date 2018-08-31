package com.example.zff.aidlservicetest.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    private String mName;

    public Person(String mName) {
        this.mName = mName;
    }

    protected Person(Parcel in){
        this.mName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "mName='" + mName + '\'' +
                '}';
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
