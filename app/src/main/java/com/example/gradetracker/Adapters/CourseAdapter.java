package com.example.gradetracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gradetracker.Model.Course;
import com.example.gradetracker.R;
import com.example.gradetracker.databinding.ItemCourseBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<Course> mCourses;
    private ItemCourseBinding itemCourseBinding;

    public CourseAdapter(List<Course> courses) {
        mCourses = courses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View courseView = inflater.inflate(R.layout.item_course, parent, false);
        itemCourseBinding = ItemCourseBinding.inflate(inflater);
        return new ViewHolder(courseView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        Course currentCourse = mCourses.get(position);
        holder.bind(currentCourse);
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // TODO add relevant view texts here
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Working", Toast.LENGTH_LONG).show();
        }

        public void bind(Course currentCourse) {
            itemCourseBinding.tvCourseName.setText(currentCourse.getCourseName());
            itemCourseBinding.tvInstructorName.setText(currentCourse.getInstructor());
            itemCourseBinding.tvStartDate.setText(currentCourse.getStartDate());
            itemCourseBinding.tvEndDate.setText(currentCourse.getEndDate());
//            itemCourseBinding.tvDescription.setText(currentCourse.getDescription());
        }
    }
}
