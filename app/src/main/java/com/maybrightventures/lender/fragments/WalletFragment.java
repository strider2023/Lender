package com.maybrightventures.lender.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maybrightventures.lender.R;

/**
 * Created by arindamnath on 12/01/16.
 */
public class WalletFragment extends Fragment {

    private View mViewHolder;

    public static WalletFragment newInstance(int sectionNumber) {
        WalletFragment fragment = new WalletFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewHolder = inflater.inflate(R.layout.fragment_wallet, container, false);
        return mViewHolder;
    }
}
