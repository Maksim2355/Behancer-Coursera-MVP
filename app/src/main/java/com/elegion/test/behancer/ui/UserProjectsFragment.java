package com.elegion.test.behancer.ui;

import android.content.Context;
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
import com.elegion.test.behancer.common.PresenterRefreshFragment;
import com.elegion.test.behancer.common.RefreshOwner;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.project.Project;
import com.elegion.test.behancer.presenters.ProjectsPresenter;
import com.elegion.test.behancer.views.ProjectsRefreshView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class UserProjectsFragment extends PresenterRefreshFragment
        implements ProjectsRefreshView {

    public static final String USERNAME = "USERNAME";

    private String mUsername;

    private RecyclerView mRecyclerView;
    private View mErrorView;

    private ProjectsAdapter mProjectsAdapter;

    @Inject
    RoutingFragment routing;

    @InjectPresenter
    ProjectsPresenter mUserProjectsPresenter;

    @Inject
    Provider<ProjectsPresenter> mProvideUserProjectsPresenter;

    @ProvidePresenter
    ProjectsPresenter providePresenterUserProjects(){
        return mProvideUserProjectsPresenter.get();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) mUsername = getArguments().getString(USERNAME);
        else mUsername = null;

        AppDelegate.getInstance().startFragmentComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_projects, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        mRecyclerView = view.findViewById(R.id.recycler);
        mErrorView = view.findViewById(R.id.errorView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProjectsAdapter = new ProjectsAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mProjectsAdapter);

        onRefresh();
    }


    @Override
    protected BasePresenter getPresenter() {
        return mUserProjectsPresenter;
    }

    @Override
    protected SwipeRefreshLayout getSwipeRefreshLayout(View v) {
        return v.findViewById(R.id.refresh_userProjects);
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
    public void showProjects(@NonNull List<Project> projects) {
        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mProjectsAdapter.addData(projects, true);
    }

    @Override
    public void openProfileFragment(@NonNull String username) {
        //TODO возможное добавление вторго экрана
    }

    @Override
    public void onRefresh() {
        mUserProjectsPresenter.getProjects(mUsername);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        AppDelegate.getInstance().stopFragmentComponent();
    }
}