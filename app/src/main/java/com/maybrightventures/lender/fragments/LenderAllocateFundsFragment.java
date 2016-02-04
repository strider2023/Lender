package com.maybrightventures.lender.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maybrightventures.lender.R;

/**
 * Created by arindamnath on 03/02/16.
 */
public class LenderAllocateFundsFragment extends Fragment {

    private View mViewHolder;

    public static LenderAllocateFundsFragment newInstance() {
        LenderAllocateFundsFragment fragment = new LenderAllocateFundsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewHolder = inflater.inflate(R.layout.fragment_allocate_funds, container, false);
        return mViewHolder;
    }
}
