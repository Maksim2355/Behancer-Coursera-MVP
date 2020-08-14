package com.elegion.test.behancer.common;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import io.reactivex.disposables.Disposable;

public abstract class BaseRefreshViewModel extends ViewModel {

    protected Disposable mDisposable;

    protected MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>(false);
    protected MutableLiveData<Boolean> mIsListVisible = new MutableLiveData<>(true);

    protected SwipeRefreshLayout.OnRefreshListener onRefreshListener = this::update;


    public abstract void update();

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsListVisible() {
        return mIsListVisible;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return onRefreshListener;
    }

    @Override
    protected void onCleared() {
        if (mDisposable != null) mDisposable.dispose();
        super.onCleared();
    }
}
