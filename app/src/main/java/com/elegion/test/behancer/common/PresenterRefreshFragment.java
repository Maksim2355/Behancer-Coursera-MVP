package com.elegion.test.behancer.common;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import moxy.MvpAppCompatFragment;

public abstract class PresenterRefreshFragment extends MvpAppCompatFragment
        implements RefreshOwner, SwipeRefreshLayout.OnRefreshListener {

    protected abstract BasePresenter getPresenter();
    protected abstract SwipeRefreshLayout getSwipeRefreshLayout(View v);

    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = getSwipeRefreshLayout(view);
    }

    @Override
    public void onDetach() {
        if (getPresenter() != null) { getPresenter().disposeAll(); }
        super.onDetach();
    }

    @Override
    public void setRefreshState(boolean refreshing) {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(refreshing));
    }

}
