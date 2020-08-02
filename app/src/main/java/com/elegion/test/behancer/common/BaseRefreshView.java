package com.elegion.test.behancer.common;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;


public interface BaseRefreshView extends MvpView {

    @AddToEndSingle
    void showRefresh();

    @AddToEndSingle
    void hideRefresh();

    @AddToEndSingle
    void showError();
}
