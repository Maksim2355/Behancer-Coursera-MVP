package com.elegion.test.behancer.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.Navigation.RoutingFragment;
import com.elegion.test.behancer.R;
import com.elegion.test.behancer.adapters.ProjectsAdapter;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.model.project.Project;
import com.elegion.test.behancer.common.PresenterRefreshFragment;
import com.elegion.test.behancer.common.RefreshOwner;
import com.elegion.test.behancer.views.ProjectsRefreshView;

import java.util.List;



public class ProjectsFragment extends PresenterRefreshFragment
        implements ProjectsRefreshView, ProjectsAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private View mErrorView;
    private ProjectsAdapter mProjectsAdapter;

    private RoutingFragment routing;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_projects, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
        AppDelegate.getAppComponent().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mProjectsAdapter = new ProjectsAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mProjectsAdapter);
    }


    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected SwipeRefreshLayout getSwipeRefreshLayout(View v) {
        return v.findViewById(R.id.refresh_projects);
    }


    @Override
    public void showRefresh() {
        setRefreshState(true);
    }

    @Override
    public void hideRefresh() {
        setRefreshState(false);
    }

    @Override
    public void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void openProfileFragment(@NonNull String username) {
        Bundle bundle = new Bundle();
        bundle.putString("USERNAME", username);
        routing.startScreen(R.id.action_projectsFragment_to_profileFragment, bundle);
    }

    @Override
    public void showProjects(@NonNull List<Project> projects) {
        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mProjectsAdapter.addData(projects, true);
    }

    @Override
    public void onItemClick(String username) {
        mPresenter.openProfileFragment(username);
    }

    @Override
    public void onRefresh() {

    }

    private void init(View v){
        mRecyclerView = v.findViewById(R.id.recycler);
        mErrorView = v.findViewById(R.id.errorView);
    }
}
