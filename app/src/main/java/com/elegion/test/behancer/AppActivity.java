package com.elegion.test.behancer;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.elegion.test.behancer.Navigation.RoutingFragment;
import com.elegion.test.behancer.common.RefreshOwner;
import com.elegion.test.behancer.common.Refreshable;

import moxy.MvpAppCompatActivity;


public class AppActivity extends MvpAppCompatActivity
        implements RoutingFragment {

    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        navController = Navigation.findNavController(this, R.id.nav_host_fr_container);
    }


    @Override
    public void startScreen(int destination, Bundle bundle) {
        navController.navigate(destination, bundle);
    }

    @Override
    public void popBackStack() {
        navController.popBackStack();
    }
}
