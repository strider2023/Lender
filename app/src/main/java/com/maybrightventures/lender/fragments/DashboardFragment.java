package com.maybrightventures.lender.fragments;

import android.app.ProgressDialog;
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
import android.widget.TextView;

import com.maybrightventures.lender.LoanDetailsActivity;
import com.maybrightventures.lender.R;
import com.maybrightventures.lender.UserTransactionActivity;
import com.maybrightventures.lender.adapters.EventsListBaseAdapter;
import com.maybrightventures.lender.dao.CalendarEventDAO;
import com.maybrightventures.lender.dao.DashboardDAO;
import com.maybrightventures.lender.dao.enums.LoaderID;
import com.maybrightventures.lender.threads.EventsLoaderTask;

import java.util.ArrayList;

/**
 * Created by arindamnath on 12/01/16.
 */
public class DashboardFragment extends Fragment implements LoaderManager.LoaderCallbacks<DashboardDAO> {

    public static final int LEND = 1;
    public static final int BORROW = 2;
    private static final String TYPE = "type";

    private View mViewHolder, summaryContainer;
    private TextView listHeaderText, transactionAmount, debitAmount, creditAmount;
    private ListView listView;
    private ProgressDialog progressDialog;
    private Bundle queryData;
    private EventsListBaseAdapter eventsListBaseAdapter;

    public static DashboardFragment newInstance(int type) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        eventsListBaseAdapter = new EventsListBaseAdapter(getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading Content...");

        mViewHolder = inflater.inflate(R.layout.fragment_dashboard, container, false);
        summaryContainer = inflater.inflate(R.layout.content_dashboard_header, null, false);

        listView = (ListView) mViewHolder.findViewById(R.id.dashboard_details_list);
        listHeaderText = (TextView) summaryContainer.findViewById(R.id.dashboard_header_text);
        transactionAmount = (TextView) summaryContainer.findViewById(R.id.dashboard_transaction_amount);
        debitAmount = (TextView) summaryContainer.findViewById(R.id.dashboard_debit);
        creditAmount = (TextView) summaryContainer.findViewById(R.id.dashboard_credit);

        listView.addHeaderView(summaryContainer);
        listView.setAdapter(eventsListBaseAdapter);

        switch (getArguments().getInt(TYPE)) {
            case LEND:
                listHeaderText.setText(R.string.payment_due);
                creditAmount.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                debitAmount.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                queryData = new Bundle();
                queryData.putString("query", "");
                getActivity().getSupportLoaderManager().initLoader(LoaderID.PAYMENT_DUE.getValue(), queryData, this).forceLoad();
                progressDialog.show();
                break;
            case BORROW:
                listHeaderText.setText(R.string.repayments_due);
                creditAmount.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                debitAmount.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                queryData = new Bundle();
                queryData.putString("query", "");
                getActivity().getSupportLoaderManager().initLoader(LoaderID.REPAYMENT_DUE.getValue(), queryData, this).forceLoad();
                progressDialog.show();
                break;
        }

        summaryContainer.findViewById(R.id.dashboard_loan_details)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), UserTransactionActivity.class);
                        intent.putExtra(TYPE, getArguments().getInt(TYPE));
                        startActivity(intent);
                    }
                });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), LoanDetailsActivity.class);
                startActivity(intent);
            }
        });

        return mViewHolder;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public Loader<DashboardDAO> onCreateLoader(int id, Bundle args) {
        progressDialog.show();
        return new EventsLoaderTask(getActivity(), null);
    }

    @Override
    public void onLoadFinished(Loader<DashboardDAO> loader, DashboardDAO data) {
        progressDialog.dismiss();
        if(data != null) {
            creditAmount.setText(data.getLent());
            debitAmount.setText(data.getBorrowed());
            eventsListBaseAdapter.setData(data.getCalendarEventDAOArrayList());
        } else {
            eventsListBaseAdapter.setData(new ArrayList<CalendarEventDAO>());
        }
    }

    @Override
    public void onLoaderReset(Loader<DashboardDAO> loader) {

    }
}
