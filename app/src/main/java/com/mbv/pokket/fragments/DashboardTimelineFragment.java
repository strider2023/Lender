package com.mbv.pokket.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;
import com.mbv.pokket.LoanDetailsActivity;
import com.mbv.pokket.R;
import com.mbv.pokket.UserProfileActivity;
import com.mbv.pokket.adapters.TimelineListAdapter;
import com.mbv.pokket.dao.TimelineDAO;
import com.mbv.pokket.dao.UserProfileDAO;
import com.mbv.pokket.dao.enums.LoaderID;
import com.mbv.pokket.dao.enums.TimelineEvents;
import com.mbv.pokket.threads.TimelineLoaderTask;

import java.util.ArrayList;

/**
 * Created by arindamnath on 06/02/16.
 */
public class DashboardTimelineFragment extends Fragment implements LoaderManager.LoaderCallbacks<UserProfileDAO>{

    private View mViewHolder;
    private TimelineListAdapter timelineListAdapter;
    private PullRefreshLayout pullRefreshLayout;
    private Bundle queryData;
    private ListView listView;

    public static DashboardTimelineFragment newInstance() {
        DashboardTimelineFragment fragment = new DashboardTimelineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        timelineListAdapter = new TimelineListAdapter(getActivity());
        mViewHolder = inflater.inflate(R.layout.fragment_timeline, container, false);
        pullRefreshLayout = (PullRefreshLayout) mViewHolder.findViewById(R.id.timeline_refresh_container);
        listView = (ListView) mViewHolder.findViewById(R.id.dashboard_timeline_list);

        pullRefreshLayout.setRefreshing(false);
        listView.setAdapter(timelineListAdapter);

        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                queryData = new Bundle();
                queryData.putString("query", "");
                getActivity().getSupportLoaderManager()
                        .initLoader(LoaderID.TIMELINE.getValue(), queryData, DashboardTimelineFragment.this).forceLoad();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TimelineDAO timelineDAO = timelineListAdapter.getItem(position);
                if (timelineDAO.getTimelineEvents() == TimelineEvents.PROFILE_UPDATE
                        || timelineDAO.getTimelineEvents() == TimelineEvents.RATING_GIVEN
                        || timelineDAO.getTimelineEvents() == TimelineEvents.RATING_RECEIVED) {
                    startActivity(new Intent(getActivity(), UserProfileActivity.class));
                } else if (timelineDAO.getTimelineEvents() == TimelineEvents.DEFAULTED
                        || timelineDAO.getTimelineEvents() == TimelineEvents.APPROVED_LOAN
                        || timelineDAO.getTimelineEvents() == TimelineEvents.LOAN_REQUESTED
                        || timelineDAO.getTimelineEvents() == TimelineEvents.LOAN_APPROVED) {
                    startActivity(new Intent(getActivity(), LoanDetailsActivity.class));
                }
            }
        });

        queryData = new Bundle();
        queryData.putString("query", "");
        getActivity().getSupportLoaderManager()
                .initLoader(LoaderID.TIMELINE.getValue(), queryData, DashboardTimelineFragment.this).forceLoad();

        return mViewHolder;
    }

    @Override
    public Loader<UserProfileDAO> onCreateLoader(int id, Bundle args) {
        return new TimelineLoaderTask(getActivity(), null);
    }

    @Override
    public void onLoadFinished(Loader<UserProfileDAO> loader, UserProfileDAO data) {
        pullRefreshLayout.setRefreshing(false);
        if(data != null) {
            timelineListAdapter.setData(data.getTimelineDAOList());
        } else {
            timelineListAdapter.setData(new ArrayList<TimelineDAO>());
        }
    }

    @Override
    public void onLoaderReset(Loader<UserProfileDAO> loader) {
        pullRefreshLayout.setRefreshing(false);
    }
}
