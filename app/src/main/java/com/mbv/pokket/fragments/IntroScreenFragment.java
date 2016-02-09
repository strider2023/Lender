package com.mbv.pokket.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mbv.pokket.R;

/**
 * Created by arindamnath on 04/01/16.
 */
public class IntroScreenFragment extends Fragment {

    public final static String LAYOUT_ID = "layoutId";
    private View mViewHolder;
    private LinearLayout base;
    private ImageView image;
    private TextView primaryText, secondaryText;

    public static IntroScreenFragment newInstance(int layoutId) {
        IntroScreenFragment pane = new IntroScreenFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LAYOUT_ID, layoutId);
        pane.setArguments(bundle);
        return pane;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewHolder = inflater.inflate(R.layout.fragment_app_info, container, false);
        base = (LinearLayout) mViewHolder.findViewById(R.id.app_info_holder);
        image = (ImageView) mViewHolder.findViewById(R.id.app_info_image);
        primaryText = (TextView) mViewHolder.findViewById(R.id.app_info_heading);
        secondaryText = (TextView) mViewHolder.findViewById(R.id.app_info_desc);
        return mViewHolder;
    }
}
