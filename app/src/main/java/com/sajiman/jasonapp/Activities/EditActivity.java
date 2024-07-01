package com.sajiman.jasonapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sajiman.jasonapp.Database.StudentDao;
import com.sajiman.jasonapp.Dto.StudentDto;
import com.sajiman.jasonapp.R;
import com.sajiman.jasonapp.RvAdapters.RvALLStudentListItemClickListener;
import com.sajiman.jasonapp.RvAdapters.RvAllStudentListAdapter;
import com.sajiman.jasonapp.RvAdapters.RvItemDecoration;
import com.sajiman.jasonapp.Utils.MiscelloneousUtils;

import java.util.List;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvAllStudentList;
    private Toolbar mEditToolbar;
    private List<StudentDto> studentDtoList;
    private RvAllStudentListAdapter rvAllStudentListAdapter;
    private ActionBar actionBar;
    private ImageButton imgBtnSearch;
    private TextView mTvEmptyMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        StudentDao studentDao = new StudentDao(EditActivity.this);

        studentDtoList = studentDao.getAllStudent();

        mEditToolbar = findViewById(R.id.tbEdit);
        setSupportActionBar(mEditToolbar);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mTvEmptyMessage = findViewById(R.id.tvEmptyMessageDisplay);


        rvAllStudentList = findViewById(R.id.rvAllStudentList);

        MiscelloneousUtils.readyToDisplay(studentDtoList);
        if (studentDtoList.isEmpty()) {
            if (rvAllStudentList.getVisibility() == View.VISIBLE) {
                rvAllStudentList.setVisibility(View.GONE);
            }
            mTvEmptyMessage.setText("Student list is empty");
            mTvEmptyMessage.setVisibility(View.VISIBLE);
        } else {
            if (mTvEmptyMessage.getVisibility() == View.VISIBLE) {
                mTvEmptyMessage.setVisibility(View.GONE);
                rvAllStudentList.setVisibility(View.VISIBLE);
            }
            rvAllStudentListAdapter = new RvAllStudentListAdapter(EditActivity.this, studentDtoList);
            rvAllStudentList.setLayoutManager(new LinearLayoutManager(this));
            rvAllStudentList.addItemDecoration(new RvItemDecoration());
            rvAllStudentListAdapter.setOnAllStudentItemClickListener(mOnRvAllStudentListClickListener);
            rvAllStudentList.setAdapter(rvAllStudentListAdapter);
        }
        imgBtnSearch = findViewById(R.id.imgBtnSearch);
        imgBtnSearch.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(EditActivity.this, MainActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    RvALLStudentListItemClickListener mOnRvAllStudentListClickListener = new RvALLStudentListItemClickListener() {
        @Override
        public void onAllStudentItemClicked(int position) {
            StudentDto studentDto = studentDtoList.get(position);
            Intent updateIntent = new Intent(EditActivity.this, UpdateActivity.class);
            updateIntent.putExtra("STUDENT_DTO_LIST", studentDto);
            startActivity(updateIntent);
            finish();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtnSearch:
                startActivity(new Intent(EditActivity.this, SearchActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditActivity.this, MainActivity.class));
        finish();
    }
}
