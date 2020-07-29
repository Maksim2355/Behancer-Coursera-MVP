package com.elegion.test.behancer.common;


import io.reactivex.disposables.CompositeDisposable;
import moxy.MvpPresenter;



public abstract class BasePresenter<View extends BaseRefreshView> extends MvpPresenter<View> {

    protected final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void disposeAll() { mCompositeDisposable.dispose(); }
}
