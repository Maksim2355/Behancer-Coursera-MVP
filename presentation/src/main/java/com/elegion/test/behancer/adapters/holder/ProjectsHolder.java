package com.elegion.test.behancer.adapters.holder;

import androidx.recyclerview.widget.RecyclerView;
import com.elegion.test.behancer.adapters.OnItemClickListener;

import com.elegion.test.behancer.databinding.ProjectItemBinding;
import com.elegion.test.behancer.view_model.ProjectsItemViewModel;
import com.lumi.domain.model.project.Project;

public class ProjectsHolder extends RecyclerView.ViewHolder {

    private ProjectItemBinding mBinding;

    public ProjectsHolder(ProjectItemBinding itemBinding) {
        super(itemBinding.getRoot());
        mBinding = itemBinding;
    }

    public void bind(Project item, OnItemClickListener onItemClickListener) {
        mBinding.setProjectsItem(new ProjectsItemViewModel(item));
        if (onItemClickListener != null) mBinding.setOnItemClickListener(onItemClickListener);
        mBinding.executePendingBindings();
    }
}
