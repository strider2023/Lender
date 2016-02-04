package com.maybrightventures.lender.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.maybrightventures.lender.R;
import com.maybrightventures.lender.adapters.RepaymentListBaseAdapter;
import com.maybrightventures.lender.dao.RepaymentDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 12/01/16.
 */
public class BorrowFragment extends Fragment {

    private View listHead;
    private View mViewHolder;
    private RepaymentListBaseAdapter repaymentListBaseAdapter;
    private List<RepaymentDAO> repaymentDAOList = new ArrayList<>();
    private ListView borrowDetails;

    public static BorrowFragment newInstance(int sectionNumber) {
        BorrowFragment fragment = new BorrowFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        repaymentListBaseAdapter = new RepaymentListBaseAdapter(getActivity());
        repaymentDAOList.add(new RepaymentDAO("14 Jan 2016", "1 of 3", 150, null));
        repaymentDAOList.add(new RepaymentDAO("14 Feb 2016", "2 of 3", 150, null));
        repaymentDAOList.add(new RepaymentDAO("14 Mar 2016", "3 of 3", 150, null));
        repaymentListBaseAdapter.setData(repaymentDAOList);

        mViewHolder = inflater.inflate(R.layout.fragment_borrow, container, false);
        borrowDetails = (ListView) mViewHolder.findViewById(R.id.content_borrow_repayment_list);
        listHead = inflater.inflate(R.layout.content_borrow_header, null);

        borrowDetails.setAdapter(repaymentListBaseAdapter);
        borrowDetails.addHeaderView(listHead);
        return mViewHolder;
    }
}
