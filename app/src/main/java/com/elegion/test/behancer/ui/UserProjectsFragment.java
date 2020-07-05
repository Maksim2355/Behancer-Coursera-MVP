package com.elegion.test.behancer.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.common.PresenterFragment;
import com.elegion.test.behancer.common.Refreshable;
import com.elegion.test.behancer.data.model.user.User;


public class UserProjectsFragment extends PresenterFragment
        implements Refreshable, BaseView {

    private static final String USER_TAG = "USER";
    private User mUser;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_projects, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUser = (User) getArguments().getSerializable(USER_TAG);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void hideRefresh() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void onRefreshData() {

    }
}