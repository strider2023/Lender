package com.mbv.pokket.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mbv.pokket.R;

/**
 * Created by arindamnath on 12/01/16.
 */
public class WalletFragment extends Fragment {

    private View mViewHolder;

    public static WalletFragment newInstance() {
        WalletFragment fragment = new WalletFragment();
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
        mViewHolder = inflater.inflate(R.layout.fragment_wallet, container, false);
        return mViewHolder;
    }
}
