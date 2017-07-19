package com.owdienko.jaroslaw.taskfromnewwork;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.owdienko.jaroslaw.taskfromnewwork.Adapters.ViewPagerAdapter;
import com.owdienko.jaroslaw.taskfromnewwork.CustomUI.NonSwipeViewPager;
import com.owdienko.jaroslaw.taskfromnewwork.Fragments.DayFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvToolBarTitle;
    private TabLayout tabLayout;
    private NonSwipeViewPager viewPager;
    private ViewPagerAdapter adapter;
    private int positionOfTheDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolBar();
        setupOuterTabLayout();

        viewPager.setCurrentItem(positionOfTheDay);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabLayout.Tab tab = tabLayout.getTabAt(viewPager.getCurrentItem());

                if (tab != null) {
                    tvToolBarTitle.setText(tab.getText());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupOuterTabLayout() {
        viewPager = (NonSwipeViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.reports_tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        List<String> days = setupTabsCount();

        for (String day : days) {
            adapter.addFragment(DayFragment.newInstance(), day);
        }

        viewPager.setAdapter(adapter);
    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        tvToolBarTitle = (TextView) findViewById(R.id.toolbar_title);
        tvToolBarTitle.setText(getCurrentWeek());
    }

    public void previousPosition(View view) {
        TabLayout.Tab tab = tabLayout.getTabAt(viewPager.getCurrentItem() - 1);
        if (tab != null) {
            tab.select();
            tvToolBarTitle.setText(tab.getText());
        }
    }

    public void nextPosition(View view) {
        TabLayout.Tab tab = tabLayout.getTabAt(viewPager.getCurrentItem() + 1);
        if (tab != null) {
            tab.select();
            tvToolBarTitle.setText(tab.getText());
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        DayFragment fragment = (DayFragment) adapter.getItem(viewPager.getCurrentItem());
        int currentTab = fragment.getTabPosition();
        fragment = null;

        menu.clear();
        inflater.inflate(R.menu.menu, menu);

        if (currentTab == 0) {
            menu.removeItem(R.id.menu_add);
            menu.removeItem(R.id.menu_load);
            menu.removeItem(R.id.menu_dvir);
        } else if (currentTab == 1) {
            menu.removeItem(R.id.menu_load);
            menu.removeItem(R.id.menu_send);
            menu.removeItem(R.id.menu_dvir);
        } else if (currentTab == 2) {
            menu.removeItem(R.id.menu_add);
            menu.removeItem(R.id.menu_send);
            menu.removeItem(R.id.menu_dvir);
        } else {
            menu.removeItem(R.id.menu_add);
            menu.removeItem(R.id.menu_send);
            menu.removeItem(R.id.menu_load);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public String getCurrentWeek() {

        StringBuilder builder = new StringBuilder();
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat dayNumber = new SimpleDateFormat("dd");
        SimpleDateFormat dayWeek = new SimpleDateFormat("EEE");
        SimpleDateFormat month = new SimpleDateFormat("MMM");

        builder.append(dayWeek.format(cal.getTime()));
        builder.append(" | ");
        builder.append(month.format(cal.getTime()));
        builder.append(". ");
        builder.append(dayNumber.format(cal.getTime()));

        return builder.toString();
    }

    private List<String> setupTabsCount() {

        List<String> days = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());

        SimpleDateFormat dayNumber = new SimpleDateFormat("dd");
        SimpleDateFormat dayWeek = new SimpleDateFormat("EEE");
        SimpleDateFormat month = new SimpleDateFormat("MMM");

        for (int i = 0; i < 6; i++) {
            StringBuilder builder = new StringBuilder();

            if (dayNumber.format(cal.getTime()).equals(getCurrentWeek().replaceAll("[^0-9]", ""))) {
                positionOfTheDay = i;
            }

            builder.append(dayWeek.format(cal.getTime()));
            builder.append(" | ");
            builder.append(month.format(cal.getTime()));
            builder.append(". ");
            builder.append(dayNumber.format(cal.getTime()));

            days.add(builder.toString());
            cal.add(Calendar.DAY_OF_WEEK, 1);
        }

        return days;
    }
}
