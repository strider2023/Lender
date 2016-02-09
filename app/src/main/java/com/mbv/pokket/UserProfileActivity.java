package com.mbv.pokket;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;
import com.mbv.pokket.adapters.TimelineListAdapter;
import com.mbv.pokket.dao.TimelineDAO;
import com.mbv.pokket.dao.UserProfileDAO;
import com.mbv.pokket.dao.enums.LoaderID;
import com.mbv.pokket.threads.TimelineLoaderTask;

import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<UserProfileDAO> {

    private TimelineListAdapter timelineListAdapter;
    private PullRefreshLayout pullRefreshLayout;
    private Bundle queryData;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("Arindam Nath");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        timelineListAdapter = new TimelineListAdapter(this);
        pullRefreshLayout = (PullRefreshLayout) findViewById(R.id.user_profile_detials_refresh_container);
        listView = (ListView) findViewById(R.id.user_profile_detials_timeline_list);

        pullRefreshLayout.setRefreshing(false);
        listView.setAdapter(timelineListAdapter);

        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                queryData = new Bundle();
                queryData.putString("query", "");
                getSupportLoaderManager()
                        .initLoader(LoaderID.TIMELINE.getValue(), queryData, UserProfileActivity.this).forceLoad();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryData = new Bundle();
        queryData.putString("query", "");
        getSupportLoaderManager()
                .initLoader(LoaderID.TIMELINE.getValue(), queryData, this).forceLoad();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<UserProfileDAO> onCreateLoader(int id, Bundle args) {
        return new TimelineLoaderTask(this, null);
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
