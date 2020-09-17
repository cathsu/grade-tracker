
package com.example.gradetracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.databinding.ItemCourseBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<Course> mCourses;
    private ItemCourseBinding itemCourseBinding;
    private AppDatabase db;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onEditClick(int p);
        void onDeleteClick(int p);
        void onViewClick(int p);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public CourseAdapter(List<Course> courses) {
        mCourses = courses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        itemCourseBinding = ItemCourseBinding.inflate(inflater);
        View view = itemCourseBinding.getRoot();
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        Course currentCourse = mCourses.get(position);
        itemCourseBinding.tvCourseName.setText(currentCourse.getCourseName());
        itemCourseBinding.tvInstructorName.setText(currentCourse.getInstructor());
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            itemCourseBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onEditClick(getAdapterPosition());
                    }
                }
            });

            itemCourseBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onDeleteClick(getAdapterPosition());
                    }
                }
            });

            itemCourseBinding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onViewClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}