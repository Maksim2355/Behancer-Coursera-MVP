package com.elegion.test.behancer.Navigation;

import android.os.Bundle;

public interface RoutingFragment {

    void startScreen(int destination, Bundle bundle);

    void popBackStack();

}
