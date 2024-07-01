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

public class RvStudentListAdapter extends RecyclerView.Adapter<RvStudentListAdapter.ViewHolder> {
    private Context context;
    private List<StudentDto> studentDtoList;
    private RvStudentListItemLongClickListener rvStudentListItemLongClickListener;

    public RvStudentListAdapter(Context context, List<StudentDto> studentDtoList) {
        this.context = context;
        this.studentDtoList = studentDtoList;
    }

    public void setOnItemLongClick(RvStudentListItemLongClickListener rvStudentListItemLongClickListener) {
        this.rvStudentListItemLongClickListener = rvStudentListItemLongClickListener;
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

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                rvStudentListItemLongClickListener.onItemLongClicked(position);
                return false;
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
}
