package com.sajiman.jasonapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sajiman.jasonapp.Database.StudentDao;
import com.sajiman.jasonapp.Dto.StudentDto;
import com.sajiman.jasonapp.R;
import com.sajiman.jasonapp.RvAdapters.RvItemDecoration;
import com.sajiman.jasonapp.RvAdapters.RvStudentListAdapter;
import com.sajiman.jasonapp.RvAdapters.RvStudentListItemLongClickListener;
import com.sajiman.jasonapp.Utils.AppLogUtils;
import com.sajiman.jasonapp.Utils.MiscelloneousUtils;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity implements View.OnClickListener {
    private static int position;
    private Toolbar mTbViewAll;
    private Spinner mSpinnerFacultyName;
    private RecyclerView mRecyclerView;
    private TextView mTvEmptyMessage;
    private BottomSheetDialog bottomSheetDialogMain;
    private BottomSheetDialog bottomSheetDialogConfirmDelete;
    private BottomSheetDialog bottomSheetDialogStudentInfo;
    private TextView memberName;
    private List<StudentDto> studentDtoList;
    private TextView confirmDeleteInfo;

    private TextView tvStudentName;
    private TextView tvOrganization;
    private TextView tvFaculty;
    private TextView tvRoll;

    private String facultyName;

    private RvStudentListAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_view_all);

        mTbViewAll = findViewById(R.id.tbViewAll);
        setSupportActionBar(mTbViewAll);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mTvEmptyMessage = findViewById(R.id.tvEmptyMessage);
        mRecyclerView = findViewById(R.id.rvStudentList);

        mRecyclerView.addItemDecoration(new RvItemDecoration());
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViewButton();
        generateBottomDialog();
        generateConfirmDeleteDialog();
        generateStudentInfoDialog();
    }

    private void generateStudentInfoDialog() {
        bottomSheetDialogStudentInfo = new BottomSheetDialog(ViewAllActivity.this);
        View bottomSheetDialogStudentInfoView = ViewAllActivity.this.getLayoutInflater()
                .inflate(R.layout.bottom_sheet_dialog_student_info, null, false);
        bottomSheetDialogStudentInfo.setContentView(bottomSheetDialogStudentInfoView);

        tvStudentName = bottomSheetDialogStudentInfoView.findViewById(R.id.tvNameStudent);
        tvOrganization = bottomSheetDialogStudentInfoView.findViewById(R.id.tvOrganization);
        tvFaculty = bottomSheetDialogStudentInfoView.findViewById(R.id.tvFaculty);
        tvRoll = bottomSheetDialogStudentInfoView.findViewById(R.id.tvRoll);

    }

    private void generateConfirmDeleteDialog() {
        bottomSheetDialogConfirmDelete = new BottomSheetDialog(ViewAllActivity.this);
        View bottomSheetDialogConfirmDeleteView = ViewAllActivity.this.getLayoutInflater()
                .inflate(R.layout.bottom_sheet_dialog_confirm_delete, null, false);
        bottomSheetDialogConfirmDelete.setContentView(bottomSheetDialogConfirmDeleteView);
        bottomSheetDialogConfirmDelete.setCancelable(true);
        confirmDeleteInfo = bottomSheetDialogConfirmDeleteView.findViewById(R.id.tvConfirmDeleteInfo);
        TextView cancelDelete = bottomSheetDialogConfirmDeleteView.findViewById(R.id.tvCancel);
        TextView confirmDelete = bottomSheetDialogConfirmDeleteView.findViewById(R.id.tvDelete);

        cancelDelete.setOnClickListener(this);
        confirmDelete.setOnClickListener(this);
    }

    private void generateBottomDialog() {
        bottomSheetDialogMain = new BottomSheetDialog(ViewAllActivity.this);
        View bottomSheetDialogMainView = ViewAllActivity.this.getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_main, null, false);
        bottomSheetDialogMain.setContentView(bottomSheetDialogMainView);
        memberName = bottomSheetDialogMainView.findViewById(R.id.tvStudentName);
        TextView memberInfo = bottomSheetDialogMainView.findViewById(R.id.tvStudentInfo);
        TextView deleteMember = bottomSheetDialogMainView.findViewById(R.id.tvStudentDelete);

        memberInfo.setOnClickListener(this);
        deleteMember.setOnClickListener(this);
    }

    private void initViewButton() {

        List<String> facultyName = new ArrayList<>();
        facultyName.add("Bachelor in Civil Engineering");
        facultyName.add("Bachelor in Computer Engineering");
        facultyName.add("Bachelor in Electronic Engineering");

        mSpinnerFacultyName = findViewById(R.id.spinnerFacultyName);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ViewAllActivity.this,
                android.R.layout.simple_list_item_1, android.R.id.text1, facultyName);
        mSpinnerFacultyName.setAdapter(adapter);

        mSpinnerFacultyName.setOnItemSelectedListener(mOnSpinnerFacultySelected);
    }

    AdapterView.OnItemSelectedListener mOnSpinnerFacultySelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            facultyName = (String) parent.getAdapter().getItem(position);
            showFacultyStudent(facultyName);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void showFacultyStudent(String facultyName) {
        if (facultyName.equals("Bachelor in Civil Engineering")) {
            StudentDao studentDao = new StudentDao(ViewAllActivity.this);
            studentDtoList = studentDao.getFacultyStudents("Bachelor in Civil Engineering");

            if (studentDtoList.isEmpty()) {
                if (mRecyclerView.getVisibility() == View.VISIBLE) {
                    mRecyclerView.setVisibility(View.GONE);
                }
                mTvEmptyMessage.setText("Student list is empty in Bachelor in Civil Engineering");
                mTvEmptyMessage.setVisibility(View.VISIBLE);
            } else {
                if (mRecyclerView.getVisibility() == View.GONE) {
                    mTvEmptyMessage.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                MiscelloneousUtils.readyToDisplay(studentDtoList);
                adapter = new RvStudentListAdapter(ViewAllActivity.this, studentDtoList);
                adapter.setOnItemLongClick(mOnItemLongClicked);
                mRecyclerView.setAdapter(adapter);
            }
            /*Intent civilStudentIntent = new Intent(ViewAllActivity.this, ViewStudentList.class);
            civilStudentIntent.putParcelableArrayListExtra("LIST", (ArrayList<? extends Parcelable>) studentDtoList);*/
        }
        if (facultyName.equals("Bachelor in Computer Engineering")) {
            StudentDao studentDao = new StudentDao(ViewAllActivity.this);
            studentDtoList = studentDao.getFacultyStudents("Bachelor in Computer Engineering");

            if (studentDtoList.isEmpty()) {
                if (mRecyclerView.getVisibility() == View.VISIBLE) {
                    mRecyclerView.setVisibility(View.GONE);
                }
                mTvEmptyMessage.setText("Student list is empty in Bachelor in Computer Engineering");
                mTvEmptyMessage.setVisibility(View.VISIBLE);
            } else {
                if (mRecyclerView.getVisibility() == View.GONE) {
                    mTvEmptyMessage.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                MiscelloneousUtils.readyToDisplay(studentDtoList);
                adapter = new RvStudentListAdapter(ViewAllActivity.this, studentDtoList);
                adapter.setOnItemLongClick(mOnItemLongClicked);
                mRecyclerView.setAdapter(adapter);
            }
        }
        if (facultyName.equals("Bachelor in Electronic Engineering")) {
            StudentDao studentDao = new StudentDao(ViewAllActivity.this);
            studentDtoList = studentDao.getFacultyStudents("Bachelor in Electronic Engineering");

            if (studentDtoList.isEmpty()) {
                if (mRecyclerView.getVisibility() == View.VISIBLE) {
                    mRecyclerView.setVisibility(View.GONE);
                }
                mTvEmptyMessage.setText("Student list is empty in Bachelor in Electronic Engineering");
                mTvEmptyMessage.setVisibility(View.VISIBLE);
            } else {
                if (mRecyclerView.getVisibility() == View.GONE) {
                    mTvEmptyMessage.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                MiscelloneousUtils.readyToDisplay(studentDtoList);
                adapter = new RvStudentListAdapter(ViewAllActivity.this, studentDtoList);
                adapter.setOnItemLongClick(mOnItemLongClicked);
                mRecyclerView.setAdapter(adapter);
            }
        }
    }

    RvStudentListItemLongClickListener mOnItemLongClicked = new RvStudentListItemLongClickListener() {
        @Override
        public void onItemLongClicked(int position) {
            ViewAllActivity.position = position;
            StudentDto studentDto = studentDtoList.get(position);
            memberName.setText(studentDto.getName());
            bottomSheetDialogMain.show();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(ViewAllActivity.this, MainActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ViewAllActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvStudentInfo:
                bottomSheetDialogMain.dismiss();
                StudentDto studentDto1 = studentDtoList.get(position);
                tvStudentName.setText(studentDto1.getName());
                tvOrganization.setText(studentDto1.getOrganization());
                tvFaculty.setText(studentDto1.getFaculty());
                AppLogUtils.showLog("ViewAllActivity ", studentDto1.toString());
                tvRoll.setText(String.valueOf(studentDto1.getRoll()));
                bottomSheetDialogStudentInfo.show();
                break;

            case R.id.tvStudentDelete:
                bottomSheetDialogMain.dismiss();
                StudentDto studentDto = studentDtoList.get(position);
                confirmDeleteInfo.setText(String.format("Do you want to delete %s?", studentDto.getName()));
                bottomSheetDialogConfirmDelete.show();
                break;

            case R.id.tvCancel:
                bottomSheetDialogConfirmDelete.dismiss();
                break;

            case R.id.tvDelete:
                bottomSheetDialogConfirmDelete.dismiss();
                StudentDto studentDto2 = studentDtoList.get(position);
                StudentDao studentDao = new StudentDao(ViewAllActivity.this);
                int affectedRow;
                affectedRow = studentDao.delete(studentDto2);
                if (affectedRow > 0) {
                    Toast.makeText(ViewAllActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    studentDtoList.remove(position);
                    adapter.notifyDataSetChanged();
                    if (facultyName.equals("Bachelor in Civil Engineering")) {
                        if (studentDtoList.isEmpty()) {
                            if (mRecyclerView.getVisibility() == View.VISIBLE) {
                                mRecyclerView.setVisibility(View.GONE);
                            }
                            mTvEmptyMessage.setText("Student list is empty in Bachelor in Civil Engineering");
                            mTvEmptyMessage.setVisibility(View.VISIBLE);
                        }
                    }
                    if (facultyName.equals("Bachelor in Computer Engineering")) {
                        if (studentDtoList.isEmpty()) {
                            if (mRecyclerView.getVisibility() == View.VISIBLE) {
                                mRecyclerView.setVisibility(View.GONE);
                            }
                            mTvEmptyMessage.setText("Student list is empty in Bachelor in Computer Engineering");
                            mTvEmptyMessage.setVisibility(View.VISIBLE);
                        }
                    }
                    if (facultyName.equals("Bachelor in Electronic Engineering")) {
                        if (studentDtoList.isEmpty()) {
                            if (mRecyclerView.getVisibility() == View.VISIBLE) {
                                mRecyclerView.setVisibility(View.GONE);
                            }
                            mTvEmptyMessage.setText("Student list is empty in Bachelor in Electronic Engineering");
                            mTvEmptyMessage.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    Toast.makeText(ViewAllActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}