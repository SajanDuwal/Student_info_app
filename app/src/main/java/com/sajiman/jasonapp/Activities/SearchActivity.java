package com.sajiman.jasonapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import com.sajiman.jasonapp.Database.StudentDao;
import com.sajiman.jasonapp.Dto.StudentDto;
import com.sajiman.jasonapp.R;
import com.sajiman.jasonapp.RvAdapters.RvALLStudentListItemClickListener;
import com.sajiman.jasonapp.RvAdapters.RvAllStudentListAdapter;
import com.sajiman.jasonapp.RvAdapters.RvItemDecoration;
import com.sajiman.jasonapp.Utils.AppLogUtils;
import com.sajiman.jasonapp.Utils.MiscelloneousUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private Toolbar mTbSearch;
    private ActionBar actionBar;
    private EditText mEtSearch;
    private RecyclerView rvSearchList;
    private List<StudentDto> studentDtoList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mTbSearch = findViewById(R.id.tbSearch);
        setSupportActionBar(mTbSearch);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mEtSearch = findViewById(R.id.etSearch);

        mEtSearch.addTextChangedListener(mOnSearchTextChanged);
        rvSearchList = findViewById(R.id.rvSearchList);
        rvSearchList.setLayoutManager(new LinearLayoutManager(this));
        rvSearchList.addItemDecoration(new RvItemDecoration());
    }


    TextWatcher mOnSearchTextChanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String arrangeName = MiscelloneousUtils.capitalize(String.valueOf(s));
            String name = String.valueOf(arrangeName);
            AppLogUtils.showLog("Edit Activity", "name -- >" + name);
            StudentDao studentDao = new StudentDao(SearchActivity.this);

            studentDtoList = studentDao.getStudentByName(name);

            List<String> outputName = new ArrayList<>();

            for (StudentDto tempVar : studentDtoList) {
                outputName.add(String.valueOf(tempVar));
                AppLogUtils.showLog("Search Activity", "name in search " + outputName);
            }

            RvAllStudentListAdapter rvAllStudentListAdapter = new RvAllStudentListAdapter(SearchActivity.this, studentDtoList);
            rvAllStudentListAdapter.setOnAllStudentItemClickListener(mOnRvItemSelected);
            rvSearchList.setAdapter(rvAllStudentListAdapter);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    RvALLStudentListItemClickListener mOnRvItemSelected = new RvALLStudentListItemClickListener() {
        @Override
        public void onAllStudentItemClicked(int position) {
            StudentDto studentDto = studentDtoList.get(position);
            Intent updateIntent = new Intent(SearchActivity.this, UpdateActivity.class);
            updateIntent.putExtra("STUDENT_DTO_LIST", studentDto);
            startActivity(updateIntent);
            finish();
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(SearchActivity.this, EditActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SearchActivity.this, EditActivity.class));
        finish();
    }
}
