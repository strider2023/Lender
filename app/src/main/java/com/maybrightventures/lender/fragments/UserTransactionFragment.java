package com.maybrightventures.lender.fragments;

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

import com.maybrightventures.lender.LoanDetailsActivity;
import com.maybrightventures.lender.R;
import com.maybrightventures.lender.adapters.TransactionHistoryAdapter;
import com.maybrightventures.lender.dao.LendDAO;
import com.maybrightventures.lender.dao.enums.LoaderID;
import com.maybrightventures.lender.threads.LendLoaderTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 13/01/16.
 */
public class UserTransactionFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<LendDAO>> {

    public static final int OPEN = 1;
    public static final int CLOSE = 2;
    public static final int PENDING = 3;
    private static final String TYPE = "type";

    private View mViewHolder;
    private ListView listView;
    private TransactionHistoryAdapter transactionHistoryAdapter;

    public static UserTransactionFragment newInstance(int type) {
        UserTransactionFragment fragment = new UserTransactionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        transactionHistoryAdapter = new TransactionHistoryAdapter(getActivity());

        mViewHolder = inflater.inflate(R.layout.fragment_user_transaction, container, false);
        listView = (ListView) mViewHolder.findViewById(R.id.user_transaction_list);
        listView.setAdapter(transactionHistoryAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), LoanDetailsActivity.class));
            }
        });

        Bundle data = new Bundle();
        data.putString("query", "");
        switch (getArguments().getInt(TYPE)) {
            case OPEN:
                getActivity().getSupportLoaderManager().initLoader(
                        LoaderID.OPEN_TRANSACTIONS.getValue(), data, this).forceLoad();
                break;
            case CLOSE:
                getActivity().getSupportLoaderManager().initLoader(
                        LoaderID.CLOSE_TRANSACTION.getValue(), data, this).forceLoad();
                break;
            case PENDING:
                getActivity().getSupportLoaderManager().initLoader(
                        LoaderID.PENDING_TRANSACTIONS.getValue(), data, this).forceLoad();
                break;
        }
        return mViewHolder;
    }

    @Override
    public Loader<List<LendDAO>> onCreateLoader(int id, Bundle args) {
        return new LendLoaderTask(getActivity(), null);
    }

    @Override
    public void onLoadFinished(Loader<List<LendDAO>> loader, List<LendDAO> data) {
        if(data != null) {
            transactionHistoryAdapter.setData(data);
        } else {
            transactionHistoryAdapter.setData(new ArrayList<LendDAO>());
        }
    }

    @Override
    public void onLoaderReset(Loader<List<LendDAO>> loader) {

    }
}

