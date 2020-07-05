package com.elegion.test.behancer.views;

import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.data.model.user.User;

public interface ProfileView extends BaseView {

    void bind(User user);

    void openUserWorks(User user);
}
