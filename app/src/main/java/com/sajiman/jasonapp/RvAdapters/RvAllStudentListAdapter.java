package com.sajiman.jasonapp.RvAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sajiman.jasonapp.Dto.StudentDto;
import com.sajiman.jasonapp.R;

import java.util.List;

public class RvAllStudentListAdapter extends RecyclerView.Adapter<RvAllStudentListAdapter.ViewHolder> {

    private Context context;
    private List<StudentDto> studentDtoList;
    private RvALLStudentListItemClickListener rvALLStudentListItemClickListener;

    public RvAllStudentListAdapter(Context context, List<StudentDto> studentDtoList) {
        this.context = context;
        this.studentDtoList = studentDtoList;
    }

    public void setOnAllStudentItemClickListener(RvALLStudentListItemClickListener rvALLStudentListItemClickListener) {
        this.rvALLStudentListItemClickListener = rvALLStudentListItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_student, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        StudentDto studentDto = studentDtoList.get(position);
        holder.mTvName.setText(studentDto.getName());
        holder.mTvStudentId.setText(String.format("Student Id : %s", studentDto.getStudentId()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvALLStudentListItemClickListener.onAllStudentItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentDtoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvName;
        private TextView mTvStudentId;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tvName);
            mTvStudentId = itemView.findViewById(R.id.tvStudentId);
        }
    }




   /* @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return SEARCH_VIEW;
        } else {
            return LIST_VIEW;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SEARCH_VIEW) {
            return new SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.row_search, parent, false));
        }
        if (viewType == LIST_VIEW) {
            return new ListViewHolder(LayoutInflater.from(context).inflate(R.layout.row_student, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        public SearchViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        public ListViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return studentDtoList.size();
    }*/
}
