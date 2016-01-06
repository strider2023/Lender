package com.maybrightventures.lender.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.maybrightventures.lender.EducationInfoActivity;
import com.maybrightventures.lender.R;

/**
 * Created by arindamnath on 27/12/15.
 */
public class EducationInfoFragment extends Fragment {

    private View mViewHolder;
    private FloatingActionButton floatingActionButton;
    private ListView educationalDetails;

    public static EducationInfoFragment newInstance(int sectionNumber) {
        EducationInfoFragment fragment = new EducationInfoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewHolder = inflater.inflate(R.layout.fragment_education_details, container, false);
        floatingActionButton = (FloatingActionButton) mViewHolder.findViewById(R.id.add_educationl_details);
        educationalDetails = (ListView) mViewHolder.findViewById(R.id.education_details_qualifications_list);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivityForResult(new Intent(getActivity(), EducationInfoActivity.class), 1);
            }
        });

        return mViewHolder;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
