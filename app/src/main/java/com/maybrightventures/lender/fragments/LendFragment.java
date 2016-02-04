package com.maybrightventures.lender.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.maybrightventures.lender.LoanDetailsActivity;
import com.maybrightventures.lender.R;
import com.maybrightventures.lender.adapters.LendListBaseAdapter;
import com.maybrightventures.lender.dao.LendDAO;
import com.maybrightventures.lender.dao.enums.LoaderID;
import com.maybrightventures.lender.threads.LendLoaderTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 12/01/16.
 */
public class LendFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<LendDAO>> {

    private View mViewHolder;
    private LendListBaseAdapter lendListBaseAdapter;
    private ListView listView;
    private LinearLayout filterOptions;

    public static LendFragment newInstance() {
        LendFragment fragment = new LendFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        lendListBaseAdapter = new LendListBaseAdapter(getActivity());

        mViewHolder = inflater.inflate(R.layout.fragment_lend, container, false);
        listView = (ListView) mViewHolder.findViewById(R.id.lend_request_list);
        filterOptions = (LinearLayout) mViewHolder.findViewById(R.id.lend_filter_option_container);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), LoanDetailsActivity.class));
            }
        });

        mViewHolder.findViewById(R.id.lend_filter_options_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleDrawer(true);
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
        filterOptions.animate()
                .setDuration(0)
                .translationY(filterOptions.getHeight())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        filterOptions.setVisibility(View.GONE);
                    }
                });

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
}