package com.elegion.test.behancer.common;

import moxy.MvpView;


public interface BaseRefreshView extends MvpView {

    void showRefresh();

    void hideRefresh();

    void showError();
}
