package com.owdienko.jaroslaw.taskfromnewwork.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owdienko.jaroslaw.taskfromnewwork.Adapters.SimpleRVAdapter;
import com.owdienko.jaroslaw.taskfromnewwork.Interfaces.PassDataIntoInnerTabs;
import com.owdienko.jaroslaw.taskfromnewwork.R;

/**
 * Created by Iaroslav Ovdienko on 19.07.17.
 * - jaroslaw - 2017 -
 */

public class InnerTabFragment extends Fragment implements PassDataIntoInnerTabs {

    private RecyclerView listOfReports;
    private SimpleRVAdapter adapter;
    private String[] mockUpData;

    public static InnerTabFragment newInstance() {
        InnerTabFragment myFragment = new InnerTabFragment();

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
        View rootView = inflater.inflate(R.layout.reports_fragment, container, false);
        setupReportsList(rootView);
        return rootView;
    }

    private void setupReportsList(View view) {
        if (mockUpData != null){
            adapter = new SimpleRVAdapter(mockUpData);
        }
        else {
            adapter = new SimpleRVAdapter(new String[]{"null =("});
        }

        listOfReports = view.findViewById(R.id.reports_list);
        listOfReports.setLayoutManager(new LinearLayoutManager(getActivity()));
        listOfReports.setAdapter(adapter);
        listOfReports.setHasFixedSize(true);
    }

    @Override
    public void passData(String[] dataArgs) {
        if (dataArgs != null) {
            mockUpData = dataArgs;
        }
    }

    @Override
    public String[] getDataForLogs() {
        return mockUpData;
    }
}
