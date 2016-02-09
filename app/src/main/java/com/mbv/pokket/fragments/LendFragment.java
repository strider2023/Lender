package com.mbv.pokket.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mbv.pokket.LoanDetailsActivity;
import com.mbv.pokket.R;
import com.mbv.pokket.adapters.LendListBaseAdapter;
import com.mbv.pokket.dao.LendDAO;
import com.mbv.pokket.dao.enums.LoaderID;
import com.mbv.pokket.threads.LendLoaderTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 12/01/16.
 */
public class LendFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<LendDAO>> {

    private View mViewHolder;
    private LendListBaseAdapter lendListBaseAdapter;
    private ListView listView;
    private CardView filterOptions;

    public static LendFragment newInstance() {
        LendFragment fragment = new LendFragment();
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
        lendListBaseAdapter = new LendListBaseAdapter(getActivity());
        mViewHolder = inflater.inflate(R.layout.fragment_lend, container, false);
        listView = (ListView) mViewHolder.findViewById(R.id.lend_request_list);
        filterOptions = (CardView) mViewHolder.findViewById(R.id.lend_filter_option_container);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), LoanDetailsActivity.class));
            }
        });

        mViewHolder.findViewById(R.id.lend_close_filter_options)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleDrawer(false);
                    }
                });

        listView.setAdapter(lendListBaseAdapter);
        toggleDrawer(false);

        Bundle data = new Bundle();
        data.putString("query", "");
        getActivity().getSupportLoaderManager().initLoader(
                LoaderID.AVAILABLE_BORROWER.getValue(), data, this).forceLoad();
        return mViewHolder;
    }

    @Override
    public Loader<List<LendDAO>> onCreateLoader(int id, Bundle args) {
        return new LendLoaderTask(getActivity(), null);
    }

    @Override
    public void onLoadFinished(Loader<List<LendDAO>> loader, List<LendDAO> data) {
        if(data != null) {
            lendListBaseAdapter.setData(data);
        } else {
            lendListBaseAdapter.setData(new ArrayList<LendDAO>());
        }
    }

    @Override
    public void onLoaderReset(Loader<List<LendDAO>> loader) {

    }

    private void toggleDrawer(boolean open) {
        if(open) {
            filterOptions.animate()
                    .setDuration(500)
                    .translationY(0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            filterOptions.setVisibility(View.VISIBLE);
                        }
                    });
        } else {
            filterOptions.animate()
                    .setDuration(500)
                    .translationY(filterOptions.getHeight())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            filterOptions.setVisibility(View.GONE);
                        }
                    });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_lend_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_lend_filter:
                toggleDrawer(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
