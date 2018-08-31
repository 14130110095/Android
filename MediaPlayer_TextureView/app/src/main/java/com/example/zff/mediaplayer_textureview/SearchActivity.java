package com.example.zff.mediaplayer_textureview;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private EditText editText;
    private ImageView imageView_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar_search);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_back_arrow);
        editText = (EditText)findViewById(R.id.EditText_toolbar_search);
        imageView_search = (ImageView)findViewById(R.id.imageview_search);
        imageView_search.setOnClickListener(this);
    }

    public static void actionStartSearchActivity(Context context){
        Intent intent = new Intent(context,SearchActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageview_search:
                Toast.makeText(this,"搜索",Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
