package com.elegion.test.behancer.views;

import com.elegion.test.behancer.common.BaseRefreshView;
import com.elegion.test.behancer.data.model.user.User;

import moxy.viewstate.strategy.alias.AddToEndSingle;

public interface ProfileRefreshView extends BaseRefreshView {

    @AddToEndSingle
    void bind(User user);

    @AddToEndSingle
    void openUserWorks(String username);
}
