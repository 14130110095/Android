package com.example.zff.alertdialogtest;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button;
    private AlertDialog alertDialog;
    private String[] str = {"one","two","three","four","five"};
    Button hiddenPassword;
    EditText editText;
    EditText editText2;
    private boolean isHidden = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button_select);
        button.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请登录");
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this,R.layout.item_layout){
//            @Override
//            public int getCount() {
//                return str.length;
//            }
//
//            @NonNull
//            @Override
//            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                View view = convertView;
//                if(view == null){
//                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
//                }
//                TextView textView = (TextView)view.findViewById(R.id.textview);
//                textView.setText(str[position]);
//                return view;
//            }
//        };

//        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String s = str[which];
//                button.setText(s);
//            }
//        });
        View view = LayoutInflater.from(this).inflate(R.layout.customview_layout,null);
        builder.setView(view);
        editText = (EditText)view.findViewById(R.id.edit1);
        editText2 = (EditText)view.findViewById(R.id.edit2);
        editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        hiddenPassword = (Button)view.findViewById(R.id.hidden_Password);
        hiddenPassword.setOnClickListener(this);
        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String account = "";
                String password = "";
                account = editText.getText().toString();
                password = editText2.getText().toString();
                if(account.equals("111") && password.equals("111")){
                    editText.setText("");
                    editText2.setText("");
                    Toast.makeText(getApplicationContext() ,"登陆成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog = builder.create();
    }



    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case  R.id.button_select:
                alertDialog.show();
                break;
            case R.id.hidden_Password:
                if(!isHidden){
                    //密文
                    editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isHidden = true;
                }else{
                    //明文
                    editText2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isHidden = false;
                }
                break;
            default:
                break;
        }

    }
}
