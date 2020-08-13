package com.elegion.test.behancer.views;

import com.elegion.test.behancer.common.BaseRefreshView;
import com.lumi.domain.model.user.User;

import moxy.viewstate.strategy.alias.AddToEndSingle;
import moxy.viewstate.strategy.alias.Skip;

public interface ProfileRefreshView extends BaseRefreshView {

    @AddToEndSingle
    void bind(User user);

    @Skip
    void openUserWorks(String username);
}
