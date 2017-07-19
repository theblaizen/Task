package com.owdienko.jaroslaw.taskfromnewwork.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owdienko.jaroslaw.taskfromnewwork.Adapters.ViewPagerAdapter;
import com.owdienko.jaroslaw.taskfromnewwork.Constants;
import com.owdienko.jaroslaw.taskfromnewwork.CustomUI.NonSwipeViewPager;
import com.owdienko.jaroslaw.taskfromnewwork.Interfaces.PassDataEntity;
import com.owdienko.jaroslaw.taskfromnewwork.Interfaces.TabPosition;
import com.owdienko.jaroslaw.taskfromnewwork.Model.DataEntity;
import com.owdienko.jaroslaw.taskfromnewwork.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Iaroslav Ovdienko on 18.07.17.
 * - jaroslaw - 2017 -
 */

public class DayFragment extends Fragment implements TabPosition, PassDataEntity {

    List<InnerTabFragment> fragments;
    private int tabPosition = Constants.TAB_POSITION;
    private TabLayout tabLayout;
    private NonSwipeViewPager viewPager;
    private Context context;
    private FragmentActivity activity;
    private DataEntity entity;

    public static DayFragment newInstance() {
        DayFragment myFragment = new DayFragment();

        Bundle args = new Bundle();
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.day_fragment, container, false);

        setupTabLayout(rootView);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPosition = tab.getPosition();
                Constants.TAB_POSITION = tabPosition;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        this.activity = (FragmentActivity) context;
        super.onAttach(context);
    }

    private void setupTabLayout(View view) {
        viewPager = view.findViewById(R.id.reports_viewpager);
        setupViewPager(viewPager);

        tabLayout = view.findViewById(R.id.reports_tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(NonSwipeViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        fragments = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            fragments.add(InnerTabFragment.newInstance());
        }

        fragments.get(0).passData(entity.getLog());
        fragments.get(1).passData(entity.getGeneral());
        fragments.get(2).passData(entity.getDocs());
        fragments.get(3).passData(entity.getDvir());

        adapter.addFragment(fragments.get(0), "Log");
        adapter.addFragment(fragments.get(1), "General");
        adapter.addFragment(fragments.get(2), "Docs");
        adapter.addFragment(fragments.get(3), "DVIR");

        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(adapter);
    }

    public int getTabReportsPosition() {
        return tabPosition;
    }

    @Override
    public int getTabPosition() {
        return getTabReportsPosition();
    }

    @Override
    public void setTabPosition(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void passEntity(DataEntity entity) {
        this.entity = entity;
    }
}