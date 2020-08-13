package com.elegion.test.behancer;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.elegion.test.behancer.Navigation.RoutingFragment;

import moxy.MvpAppCompatActivity;


public class AppActivity extends MvpAppCompatActivity
        implements RoutingFragment {

    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AppDelegate.getInstance().startActivityComponent(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        navController = Navigation.findNavController(this, R.id.nav_host_fr_container);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDelegate.getInstance().stopActivityComponent();
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
