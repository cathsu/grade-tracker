package com.example.gradetracker.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gradetracker.Model.Assignment;
import com.example.gradetracker.R;

import java.util.ArrayList;

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
        public TextView mCategoryGrade;


        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            mDescription = itemView.findViewById(R.id.tvDescription);
            mAssignedDate = itemView.findViewById(R.id.tvAssignedDate);
            mDueDate = itemView.findViewById(R.id.tvDueDate);
            mAssignmentGrade = itemView.findViewById(R.id.tvGrade);
            mEarnedMaxPoints = itemView.findViewById(R.id.tvEarnedMaxPoints);
            mCategory = itemView.findViewById(R.id.tvCategory);
            mCategoryGrade = itemView.findViewById(R.id.tvCategoryGrade);
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
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        Assignment assignmentItem = mAssignmentList.get(position);
        holder.mDescription.setText(assignmentItem.getAssignmentDescription());
        holder.mAssignedDate.setText(assignmentItem.getAssignedDate());
        holder.mDueDate.setText(assignmentItem.getDueDate());
        holder.mAssignmentGrade.setText(assignmentItem.getLetterGrade());
        holder.mEarnedMaxPoints.setText(assignmentItem.getEarnedPoints() + " / " + assignmentItem.getMaxPoints());
        holder.mCategory.setText(assignmentItem.getCategoryName());
        holder.mCategoryGrade.setText("HOW????");
    }

    @Override
    public int getItemCount() {
        return mAssignmentList.size();
    }
}
