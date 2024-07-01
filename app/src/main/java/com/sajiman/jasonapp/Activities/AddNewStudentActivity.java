package com.sajiman.jasonapp.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sajiman.jasonapp.Database.StudentDao;
import com.sajiman.jasonapp.Dto.StudentDto;
import com.sajiman.jasonapp.R;
import com.sajiman.jasonapp.Utils.AppLogUtils;
import com.sajiman.jasonapp.Utils.DateUtils;
import com.sajiman.jasonapp.Utils.MiscelloneousUtils;

import java.util.ArrayList;
import java.util.List;

public class AddNewStudentActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mTbAddNewStudent;
    private TextView mTvStudentId;
    private ImageView mIvStudentImage;
    private EditText mEtName;
    private EditText mEtOrganization;
    private EditText mEtRoll;
    private Spinner mSpinnerFaculty;
     private String spinnerFaculty;
    private String firstIdContain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        mTbAddNewStudent = findViewById(R.id.tbAdd);
        setSupportActionBar(mTbAddNewStudent);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initElements();
    }

    private void initElements() {

        mTvStudentId = findViewById(R.id.tvStudentId);
        mIvStudentImage = findViewById(R.id.ivStudentImage);
        mEtName = findViewById(R.id.etName);
        mEtOrganization = findViewById(R.id.etOrganization);
        mEtRoll = findViewById(R.id.etRoll);
        mSpinnerFaculty = findViewById(R.id.spinnerFaculty);
        findViewById(R.id.btnSave).setOnClickListener(this);
        mIvStudentImage.setOnClickListener(this);

        mEtRoll.addTextChangedListener(mOnRollChange);

        List<String> facultyList = new ArrayList<>();
        facultyList.add("Bachelor in Civil Engineering");
        facultyList.add("Bachelor in Computer Engineering");
        facultyList.add("Bachelor in Electronic Engineering");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AddNewStudentActivity.this,
                android.R.layout.simple_list_item_1, android.R.id.text1, facultyList);
        mSpinnerFaculty.setAdapter(arrayAdapter);

        mSpinnerFaculty.setOnItemSelectedListener(mOnSpinnerItemSelected);
    }

    AdapterView.OnItemSelectedListener mOnSpinnerItemSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            spinnerFaculty = (String) parent.getAdapter().getItem(position);

            String date = DateUtils.getCurrentNepaliYear();
            String year = date.substring(1);
            String faculty = facultyName(spinnerFaculty);
            firstIdContain = year + faculty;

            AppLogUtils.showLog("AddNewStudentActivity", "" + firstIdContain);
            String roll = mEtRoll.getText().toString();
            mTvStudentId.setText(String.format("%s%s", firstIdContain, roll));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    TextWatcher mOnRollChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String roll = mEtRoll.getText().toString();

            if (!TextUtils.isEmpty(roll)) {
                String id = firstIdContain;
                mTvStudentId.setText(String.format("%s%s", id, s));

                AppLogUtils.showLog("AddNewStudentActivity", "" + id + s);
            } else {
                mTvStudentId.setText(firstIdContain);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public String facultyName(String facultyName) {
        switch (facultyName) {
            case "Bachelor in Civil Engineering":
                return "BCV";

            case "Bachelor in Computer Engineering":
                return "BCE";

            case "Bachelor in Electronic Engineering":
                return "BEE";
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                if (valid()) {
                    String imagePath = "";
                    String mainId = mTvStudentId.getText().toString();
                    String name = MiscelloneousUtils.capitalize(mEtName.getText().toString());
                    String organization = MiscelloneousUtils.capitalize(mEtOrganization.getText().toString());
                    String faculty = spinnerFaculty;
                    int roll = Integer.parseInt(mEtRoll.getText().toString());

                    StudentDto studentDto = new StudentDto();
                    studentDto.setStudentId(mainId);
                    studentDto.setImagePath(imagePath);
                    studentDto.setName(name);
                    studentDto.setOrganization(organization);
                    studentDto.setFaculty(faculty);
                    studentDto.setRoll(roll);

                    StudentDao studentDao = new StudentDao(AddNewStudentActivity.this);
                    boolean isUniqueId = studentDao.uniqueIdCheck(studentDto);
                    if (isUniqueId) {
                        long insertTest = studentDao.insert(studentDto);
                        if (insertTest == -1) {
                            Toast.makeText(AddNewStudentActivity.this, "failed to register", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddNewStudentActivity.this, "registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddNewStudentActivity.this, MainActivity.class));
                            finish();
                        }
                    } else {
                        mEtRoll.setError("duplicate roll no");
                        mEtRoll.requestFocus();
                    }
                }
                break;

            case R.id.ivStudentImage:
                new AlertDialog.Builder(AddNewStudentActivity.this)
                        .setItems(new String[]{"Take a photo", "Choose from Gallery"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        Toast.makeText(AddNewStudentActivity.this, "Take a photo", Toast.LENGTH_SHORT).show();
                                        break;

                                    case 1:
                                        Toast.makeText(AddNewStudentActivity.this, "Choose from Gallery", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();

                break;
        }
    }

    private boolean valid() {

        if (TextUtils.isEmpty(mEtName.getText().toString())) {
            mEtName.setError("Field is empty!");
            mEtName.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(mEtOrganization.getText().toString())) {
            mEtOrganization.setError("Field is empty!");
            mEtOrganization.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(mEtRoll.getText().toString())) {
            mEtRoll.setError("Field is empty!");
            mEtRoll.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(AddNewStudentActivity.this, MainActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddNewStudentActivity.this, MainActivity.class));
        finish();
    }
}