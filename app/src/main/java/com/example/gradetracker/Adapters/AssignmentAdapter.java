package com.example.gradetracker.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gradetracker.AssignmentActivity;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.EditAssignmentActivity;
import com.example.gradetracker.Model.Assignment;
import com.example.gradetracker.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class  AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {
    private ArrayList<Assignment> mAssignmentList;


    public AssignmentAdapter(ArrayList<Assignment> assignmentList) {
        mAssignmentList = assignmentList;
    }

    public static class AssignmentViewHolder extends RecyclerView.ViewHolder {
        public TextView mDescription;
        public TextView mAssignedDate;
        public TextView mDueDate;
        public TextView mAssignmentGrade;
        public TextView mEarnedMaxPoints;
        public TextView mCategory;
        public TextView mName;
        public Button mEdit;
        public Button mDelete;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tvName);
            mDescription = itemView.findViewById(R.id.tvDescription);
            mAssignedDate = itemView.findViewById(R.id.tvAssignedDate);
            mDueDate = itemView.findViewById(R.id.tvDueDate);
            mAssignmentGrade = itemView.findViewById(R.id.tvGrade);
            mEarnedMaxPoints = itemView.findViewById(R.id.tvEarnedMaxPoints);
            mCategory = itemView.findViewById(R.id.tvCategory);
            mEdit = itemView.findViewById(R.id.btnEdit);
            mDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //pass layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_assignment, parent, false);
        AssignmentViewHolder avh = new AssignmentViewHolder(v);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, final int position) {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Assignment assignmentItem = mAssignmentList.get(position);
        holder.mName.setText(assignmentItem.getName());
        holder.mDescription.setText(assignmentItem.getAssignmentDescription());
        holder.mAssignedDate.setText(assignmentItem.getAssignedDate());
        holder.mDueDate.setText(assignmentItem.getDueDate());
        holder.mAssignmentGrade.setText(decimalFormat.format(assignmentItem.getPercentageGrade()) + "%");
        holder.mEarnedMaxPoints.setText(assignmentItem.getEarnedPoints() + " / " + assignmentItem.getMaxPoints());
        holder.mCategory.setText(assignmentItem.getCategoryName());
        holder.mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = EditAssignmentActivity.getIntent(view.getContext(), mAssignmentList.get(position).getAssignmentID());
                view.getContext().startActivity(intent);
            }
        });
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = AppDatabase.getInstance(view.getContext());
                db.AssignmentDao().deleteAssignment(mAssignmentList.get(position));
                mAssignmentList.remove(position);
                notifyItemRemoved(position);
                notifyItemChanged(position);
                Intent intent = AssignmentActivity.getIntent(view.getContext(), mAssignmentList.get(position).getCourseID());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAssignmentList.size();
    }
}
