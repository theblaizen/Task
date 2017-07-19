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
import com.owdienko.jaroslaw.taskfromnewwork.CustomUI.NonSwipeViewPager;
import com.owdienko.jaroslaw.taskfromnewwork.Interfaces.TabPosition;
import com.owdienko.jaroslaw.taskfromnewwork.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Iaroslav Ovdienko on 18.07.17.
 * - jaroslaw - 2017 -
 */

public class DayFragment extends Fragment implements TabPosition {

    private TabLayout tabLayout;
    private NonSwipeViewPager viewPager;
    private Context context;
    private FragmentActivity activity;
    List<InnerTabFragment> fragments;

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

        fragments.get(0).passData(new String[]{"Log","Log","Log","Log","Log","Log"});
        fragments.get(1).passData(new String[]{"General","General","General","General","General","General"});
        fragments.get(2).passData(new String[]{"Docs","Docs","Docs","Docs","Docs","Docs"});
        fragments.get(3).passData(new String[]{"DVIR","DVIR","DVIR","DVIR","DVIR","DVIR"});

        adapter.addFragment(fragments.get(0), "Log");
        adapter.addFragment(fragments.get(1), "General");
        adapter.addFragment(fragments.get(2), "Docs");
        adapter.addFragment(fragments.get(3), "DVIR");

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