package com.maybrightventures.lender.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maybrightventures.lender.R;

/**
 * Created by arindamnath on 04/02/16.
 */
public class UserKYCFragment extends Fragment {

    private View mViewHolder;

    public static UserKYCFragment newInstance(int sectionNumber) {
        UserKYCFragment fragment = new UserKYCFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewHolder = inflater.inflate(R.layout.fragment_work_details, container, false);
        return mViewHolder;
    }
}
