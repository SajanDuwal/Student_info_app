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

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mTbUpdateStudent;
    private ImageView ivUpdatedStudentImage;
    private TextView tvUpdatedStudentId;
    private EditText etUpdatedName;
    private EditText etUpdatedOrganization;
    private Spinner spinnerUpdatedFaculty;
    private EditText etUpdatedRoll;

    private String spinnerFaculty;
    private String firstIdContain;

    private StudentDto beforeUpdatedStudentDto;

    private List<String> studentId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mTbUpdateStudent = findViewById(R.id.tbUpdate);
        setSupportActionBar(mTbUpdateStudent);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        beforeUpdatedStudentDto = getIntent().getParcelableExtra("STUDENT_DTO_LIST");

        String stdID = beforeUpdatedStudentDto.getStudentId();
        StudentDao studentDao = new StudentDao(UpdateActivity.this);
        List<StudentDto> studentIdList = studentDao.getAllStudentId();
        studentId = new ArrayList<>();
        for (StudentDto studentDto : studentIdList) {
            if (!studentDto.getStudentId().equals(stdID)) {
                studentId.add(studentDto.getStudentId());
            }
        }
        initView();
    }

    private void initView() {
        ivUpdatedStudentImage = findViewById(R.id.ivUpdatedStudentImage);
        tvUpdatedStudentId = findViewById(R.id.tvUpdatedStudentId);
        etUpdatedName = findViewById(R.id.etUpdatedName);
        etUpdatedOrganization = findViewById(R.id.etUpdatedOrganization);
        spinnerUpdatedFaculty = findViewById(R.id.spinnerUpdatedFaculty);
        etUpdatedRoll = findViewById(R.id.etUpdatedRoll);

        etUpdatedName.setText(beforeUpdatedStudentDto.getName());
        etUpdatedOrganization.setText(beforeUpdatedStudentDto.getOrganization());
        etUpdatedRoll.setText(String.valueOf(beforeUpdatedStudentDto.getRoll()));

        String faculty = beforeUpdatedStudentDto.getFaculty();
        List<String> facultyList = new ArrayList<>();
        facultyList.add("Bachelor in Civil Engineering");
        facultyList.add("Bachelor in Computer Engineering");
        facultyList.add("Bachelor in Electronic Engineering");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(UpdateActivity.this,
                android.R.layout.simple_list_item_1, android.R.id.text1, facultyList);
        spinnerUpdatedFaculty.setAdapter(arrayAdapter);
        int facultyPosition = arrayAdapter.getPosition(faculty);
        spinnerUpdatedFaculty.setSelection(facultyPosition);

        spinnerUpdatedFaculty.setOnItemSelectedListener(mOnSpinnerItemSelected);
        etUpdatedRoll.addTextChangedListener(mOnRollChange);
        findViewById(R.id.btnUpdate).setOnClickListener(this);
        ivUpdatedStudentImage.setOnClickListener(this);
    }

    AdapterView.OnItemSelectedListener mOnSpinnerItemSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            spinnerFaculty = (String) parent.getAdapter().getItem(position);
            String date = DateUtils.getCurrentNepaliYear();
            String year = date.substring(1);
            String faculty = facultyName(spinnerFaculty);
            firstIdContain = year + faculty;

            AppLogUtils.showLog("UpdateActivity", "" + firstIdContain);
            String roll = etUpdatedRoll.getText().toString();
            tvUpdatedStudentId.setText(String.format("%s%s", firstIdContain, roll));
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

            String roll = etUpdatedRoll.getText().toString();

            if (!TextUtils.isEmpty(roll)) {
                String id = firstIdContain;
                tvUpdatedStudentId.setText(String.format("%s%s", id, s));

                AppLogUtils.showLog("AddNewStudentActivity", "" + id + s);
            } else {
                tvUpdatedStudentId.setText(firstIdContain);
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
            case R.id.btnUpdate:
                if (valid()) {
                    String imagePath = "";
                    String mainId = tvUpdatedStudentId.getText().toString();
                    String name = MiscelloneousUtils.capitalize(etUpdatedName.getText().toString());
                    String organization = MiscelloneousUtils.capitalize(etUpdatedOrganization.getText().toString());
                    String faculty = spinnerFaculty;
                    int roll = Integer.parseInt(etUpdatedRoll.getText().toString());

                    StudentDto studentDto = new StudentDto();
                    studentDto.setId(beforeUpdatedStudentDto.getId());
                    studentDto.setStudentId(mainId);
                    studentDto.setImagePath(imagePath);
                    studentDto.setName(name);
                    studentDto.setOrganization(organization);
                    studentDto.setFaculty(faculty);
                    studentDto.setRoll(roll);

                    AppLogUtils.showLog("UpdateActivity", studentDto.toString());

                    if (!studentId.contains(tvUpdatedStudentId.getText().toString())) {
                        StudentDao studentDao = new StudentDao(UpdateActivity.this);
                        int affectedRow = studentDao.update(studentDto);
                        if (affectedRow > 0) {
                            Toast.makeText(UpdateActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateActivity.this, EditActivity.class));
                            finish();
                        } else {
                            Toast.makeText(UpdateActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        etUpdatedRoll.setError("duplicate roll no");
                        etUpdatedRoll.requestFocus();
                    }
                }
                break;

            case R.id.ivUpdatedStudentImage:
                new AlertDialog.Builder(UpdateActivity.this)
                        .setItems(new String[]{"Take a photo", "Choose from Gallery"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        Toast.makeText(UpdateActivity.this, "Take a photo", Toast.LENGTH_SHORT).show();
                                        break;

                                    case 1:
                                        Toast.makeText(UpdateActivity.this, "Choose from Gallery", Toast.LENGTH_SHORT).show();
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

        if (TextUtils.isEmpty(etUpdatedName.getText().toString())) {
            etUpdatedName.setError("Field is empty!");
            etUpdatedName.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(etUpdatedOrganization.getText().toString())) {
            etUpdatedOrganization.setError("Field is empty!");
            etUpdatedOrganization.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etUpdatedRoll.getText().toString())) {
            etUpdatedRoll.setError("Field is empty!");
            etUpdatedRoll.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(UpdateActivity.this, EditActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UpdateActivity.this, EditActivity.class));
        finish();
    }
}