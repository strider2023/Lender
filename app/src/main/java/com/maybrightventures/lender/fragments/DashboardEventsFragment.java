package com.maybrightventures.lender.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;
import com.maybrightventures.lender.LoanDetailsActivity;
import com.maybrightventures.lender.R;
import com.maybrightventures.lender.adapters.EventsListBaseAdapter;
import com.maybrightventures.lender.dao.enums.LoaderID;
import com.maybrightventures.lender.threads.EventsLoaderTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 12/01/16.
 */
public class DashboardEventsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Object>> {

    private View mViewHolder;
    private PullRefreshLayout pullRefreshLayout;
    private ListView listView;
    private Bundle queryData;
    private EventsListBaseAdapter eventsListBaseAdapter;

    public static DashboardEventsFragment newInstance() {
        DashboardEventsFragment fragment = new DashboardEventsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        eventsListBaseAdapter = new EventsListBaseAdapter(getActivity());

        mViewHolder = inflater.inflate(R.layout.fragment_events, container, false);

        pullRefreshLayout = (PullRefreshLayout) mViewHolder.findViewById(R.id.events_refresh_container);
        listView = (ListView) mViewHolder.findViewById(R.id.dashboard_events_list);
        listView.setAdapter(eventsListBaseAdapter);
        pullRefreshLayout.setRefreshing(false);

        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                queryData = new Bundle();
                queryData.putString("query", "");
                getActivity().getSupportLoaderManager()
                        .initLoader(LoaderID.EVENTS.getValue(), queryData, DashboardEventsFragment.this).forceLoad();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), LoanDetailsActivity.class);
                startActivity(intent);
            }
        });

        queryData = new Bundle();
        queryData.putString("query", "");
        getActivity().getSupportLoaderManager().initLoader(LoaderID.EVENTS.getValue(), queryData, this).forceLoad();

        return mViewHolder;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public Loader<List<Object>> onCreateLoader(int id, Bundle args) {
        return new EventsLoaderTask(getActivity(), null);
    }

    @Override
    public void onLoadFinished(Loader<List<Object>> loader, List<Object> data) {
        pullRefreshLayout.setRefreshing(false);
        if(data != null) {
            eventsListBaseAdapter.setData(data);
        } else {
            eventsListBaseAdapter.setData(new ArrayList<Object>());
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Object>> loader) {
        pullRefreshLayout.setRefreshing(false);
    }
}
