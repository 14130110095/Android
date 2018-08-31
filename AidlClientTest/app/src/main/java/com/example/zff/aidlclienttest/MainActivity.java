package com.example.zff.aidlclienttest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zff.aidlservicetest.IMyAidl;
import com.example.zff.aidlservicetest.bean.Person;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Intent serviceIntent;
    private TextView textView1;
    private Button button1;
    private IMyAidl iMyAidl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView)findViewById(R.id.textview);
        button1 = (Button)findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                Person person = new Person("shixin" + random.nextInt(10));

                try {
                    iMyAidl.addPerson(person);
                    List<Person> personList = iMyAidl.getPersonList();
                    textView1.setText(personList.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        serviceIntent = new Intent();
        //启动服务的包名和类名
        ComponentName comp = new ComponentName("com.example.zff.aidlservicetest", "com.example.zff.aidlservicetest.MyAidlService");
        serviceIntent.setComponent(comp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(serviceIntent);
        bindService(serviceIntent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(serviceIntent);
        unbindService(serviceConnection);
    }

    private  ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidl = IMyAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMyAidl = null;
        }
    };
}
