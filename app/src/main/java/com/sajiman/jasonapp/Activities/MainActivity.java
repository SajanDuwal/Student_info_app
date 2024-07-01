package com.sajiman.jasonapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sajiman.jasonapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mTbMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTbMainActivity = findViewById(R.id.tbMain);
        setSupportActionBar(mTbMainActivity);

        initButtons();
    }

    private void initButtons() {

        findViewById(R.id.btnAddStudent).setOnClickListener(this);
        findViewById(R.id.btnEditStudent).setOnClickListener(this);
        findViewById(R.id.btnViewAllStudent).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddStudent:
                startActivity(new Intent(MainActivity.this, AddNewStudentActivity.class));
                finish();
                break;

            case R.id.btnEditStudent:
                startActivity(new Intent(MainActivity.this, EditActivity.class));
                finish();
                break;

            case R.id.btnViewAllStudent:
                startActivity(new Intent(MainActivity.this, ViewAllActivity.class));
                finish();
                break;
        }
    }
}
