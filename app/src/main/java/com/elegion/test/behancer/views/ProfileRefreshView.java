package com.elegion.test.behancer.views;

import com.elegion.test.behancer.common.BaseRefreshView;
import com.elegion.test.behancer.data.model.user.User;

public interface ProfileRefreshView extends BaseRefreshView {

    void bind(User user);

    void openUserWorks(String username);
}
