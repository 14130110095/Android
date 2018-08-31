package com.example.zff.aidlservicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.zff.aidlservicetest.bean.Person;

import java.util.ArrayList;
import java.util.List;

public class MyAidlService extends Service {

    private ArrayList<Person> arrayList;

    public MyAidlService() {
    }

    private IBinder iBinder = new IMyAidl.Stub() {
        @Override
        public void addPerson(Person person) throws RemoteException {
            arrayList.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return arrayList;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MyAidlService","onBind");
        arrayList = new ArrayList<>();
        return iBinder;
    }

}
