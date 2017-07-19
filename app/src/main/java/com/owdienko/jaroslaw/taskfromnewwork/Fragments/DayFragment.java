package com.owdienko.jaroslaw.taskfromnewwork.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owdienko.jaroslaw.taskfromnewwork.CustomUI.NonSwipeViewPager;
import com.owdienko.jaroslaw.taskfromnewwork.Interfaces.TabPosition;
import com.owdienko.jaroslaw.taskfromnewwork.R;
import com.owdienko.jaroslaw.taskfromnewwork.Adapters.ViewPagerAdapter;

/**
 * Created by Iaroslav Ovdienko on 18.07.17.
 * - jaroslaw - 2017 -
 */

public class DayFragment extends Fragment implements TabPosition {

    private TabLayout tabLayout;
    private NonSwipeViewPager viewPager;
    private Context context;
    private FragmentActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.day_fragment, container, false);
        setupTabLayout(rootView);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        this.activity = (FragmentActivity) context;
        super.onAttach(context);
    }

    private void setupTabLayout(View view){
        viewPager = view.findViewById(R.id.reports_viewpager);
        setupViewPager(viewPager);

        tabLayout = view.findViewById(R.id.reports_tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(NonSwipeViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        adapter.addFragment(new InnerTabFragment(), "Log");
        adapter.addFragment(new InnerTabFragment(), "General");
        adapter.addFragment(new InnerTabFragment(), "Docs");
        adapter.addFragment(new InnerTabFragment(), "DVIR");
        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(adapter);
    }

    public int getTabReportsPosition() {
        return tabLayout.getSelectedTabPosition();
    }

    @Override
    public int getTabPosition() {
        return getTabReportsPosition();
    }
}