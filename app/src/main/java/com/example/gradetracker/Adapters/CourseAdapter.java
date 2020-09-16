
package com.example.gradetracker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.EditCourseActivity;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.databinding.ItemCourseBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<Course> mCourses;
    private ItemCourseBinding itemCourseBinding;
    private AppDatabase db;

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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        Course currentCourse = mCourses.get(position);
        Log.i(CourseAdapter.class.getName(), currentCourse.toString());
        holder.bind(currentCourse);
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            db = AppDatabase.getInstance(itemView.getContext());
        }

        public void bind(Course currentCourse) {
            itemCourseBinding.tvCourseName.setText(currentCourse.getCourseName());
            itemCourseBinding.tvInstructorName.setText(currentCourse.getInstructor());
            itemCourseBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Edit", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(view.getContext(), EditCourseActivity.class);
                    view.getContext().startActivity(intent);
                }
            });

            itemCourseBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.courseDao().deleteCourse(mCourses.get(getAdapterPosition()));
                    deleteItem(getAdapterPosition());
                }
            });

            itemCourseBinding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Nice", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicking", Toast.LENGTH_LONG).show();
        }
    }

    private void deleteItem(int position) {
        mCourses.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mCourses.size());
    }
}