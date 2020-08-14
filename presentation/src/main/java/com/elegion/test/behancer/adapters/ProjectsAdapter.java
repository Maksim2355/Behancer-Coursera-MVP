package com.elegion.test.behancer.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elegion.test.behancer.adapters.holder.ProjectsHolder;
import com.elegion.test.behancer.databinding.ProjectItemBinding;
import com.lumi.domain.model.project.Project;

import java.util.List;


public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsHolder> {

    @NonNull
    private List<Project> mProjects;

    private OnItemClickListener mOnItemClickListener;

    public ProjectsAdapter(List<Project> projects, OnItemClickListener onItemClickListener) {
        mProjects = projects;
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ProjectsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ProjectsHolder(ProjectItemBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsHolder holder, int position) {
        Project project = mProjects.get(position);
        holder.bind(project, mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return (mProjects == null) ? 0 : mProjects.size();
    }

}
