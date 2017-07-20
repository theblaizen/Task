package com.owdienko.jaroslaw.taskfromnewwork.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owdienko.jaroslaw.taskfromnewwork.Adapters.SimpleRVAdapter;
import com.owdienko.jaroslaw.taskfromnewwork.Constants;
import com.owdienko.jaroslaw.taskfromnewwork.Interfaces.DayFragmentPresenter;
import com.owdienko.jaroslaw.taskfromnewwork.Interfaces.PassDataEntity;
import com.owdienko.jaroslaw.taskfromnewwork.Model.DataEntity;
import com.owdienko.jaroslaw.taskfromnewwork.R;

/**
 * Created by Iaroslav Ovdienko on 18.07.17.
 * - jaroslaw - 2017 -
 */

public class DayFragment extends Fragment implements PassDataEntity, DayFragmentPresenter {

    private DataEntity entity;
    private RecyclerView listOfReports;
    private SimpleRVAdapter adapter;
    private String[] mockUpData;

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
        mockUpData = getData();
        setupReportsList(rootView);

        return rootView;
    }

    private String[] getData() {
        if (Constants.TAB_POSITION == 0) {
            return entity.getLog();
        } else if (Constants.TAB_POSITION == 1) {
            return entity.getGeneral();
        } else if (Constants.TAB_POSITION == 2) {
            return entity.getDocs();
        } else {
            return entity.getDvir();
        }
    }

    private void setupReportsList(View view) {
        if (mockUpData != null) {
            adapter = new SimpleRVAdapter(mockUpData);
        } else {
            adapter = new SimpleRVAdapter(new String[]{"null =("});
        }

        listOfReports = view.findViewById(R.id.reports_list);
        listOfReports.setLayoutManager(new LinearLayoutManager(getActivity()));
        listOfReports.setAdapter(adapter);
        listOfReports.setHasFixedSize(true);
    }

    @Override
    public void passEntity(DataEntity entity) {
        this.entity = entity;
    }

    @Override
    public void changeDataInTabs() {
        mockUpData = getData();
        if (mockUpData != null) {
            adapter.swapArray(mockUpData);
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        } else {
            adapter.swapArray(new String[]{"null =("});
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        }
    }
}