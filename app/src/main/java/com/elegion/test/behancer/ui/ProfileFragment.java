package com.elegion.test.behancer.ui;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.Navigation.RoutingFragment;
import com.elegion.test.behancer.R;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.common.PresenterRefreshFragment;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.presenters.ProfilePresenter;
import com.elegion.test.behancer.utils.DateUtils;
import com.elegion.test.behancer.views.ProfileRefreshView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import javax.inject.Provider;

import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class ProfileFragment extends PresenterRefreshFragment
        implements ProfileRefreshView {

    public static final String USERNAME = "USERNAME";

    private View mErrorView;
    private View mProfileView;

    private String mUsername;

    private ImageView mProfileImage;
    private TextView mProfileName;
    private TextView mProfileCreatedOn;
    private TextView mProfileLocation;
    private Button mViewWorksBtn;

    @Inject
    RoutingFragment mRouting;

    @InjectPresenter
    ProfilePresenter mProfilePresenter;

    @Inject
    Provider<ProfilePresenter> mProviderProfilePresenter;

    @ProvidePresenter
    ProfilePresenter provideProfilePresenter(){
        return mProviderProfilePresenter.get();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getArguments() != null) mUsername = getArguments().getString(USERNAME);
        else mUsername = null;
        AppDelegate.getInstance().startFragmentComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mProfilePresenter;
    }

    @Override
    protected SwipeRefreshLayout getSwipeRefreshLayout(View v) {
        return v.findViewById(R.id.refresh_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            mUsername = getArguments().getString(USERNAME);
        }

        onRefresh();
    }

    @Override
    public void bind(User user) {
        mErrorView.setVisibility(View.GONE);
        mProfileView.setVisibility(View.VISIBLE);
        Picasso.with(getContext())
                .load(user.getImage().getPhotoUrl())
                .fit()
                .into(mProfileImage);
        mProfileName.setText(user.getDisplayName());
        mProfileCreatedOn.setText(DateUtils.format(user.getCreatedOn()));
        mProfileLocation.setText(user.getLocation());
    }

    @Override
    public void openUserWorks(String username) {
        Bundle bundle = new Bundle();
        bundle.putString(USERNAME, username);
        mRouting.startScreen(R.id.action_profileFragment_to_userProjectsFragment, bundle);
    }

    @Override
    public void showRefresh() {
        setRefreshState(true);
    }

    @Override
    public void hideRefresh() {
        setRefreshState(false);
    }

    @Override
    public void showError() {
        mProfileView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        mProfilePresenter.loadProfile(mUsername);
    }

    private void init(View view) {
        mErrorView = view.findViewById(R.id.errorView);
        mProfileView = view.findViewById(R.id.view_profile);
        mViewWorksBtn = view.findViewById(R.id.goUserProjects_btn);
        mProfileImage = view.findViewById(R.id.iv_profile);
        mProfileName = view.findViewById(R.id.tv_display_name_details);
        mProfileCreatedOn = view.findViewById(R.id.tv_created_on_details);
        mProfileLocation = view.findViewById(R.id.tv_location_details);

        mViewWorksBtn.setOnClickListener(v -> mProfilePresenter.openUserProjects(mUsername));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        AppDelegate.getInstance().stopFragmentComponent();
    }
}
