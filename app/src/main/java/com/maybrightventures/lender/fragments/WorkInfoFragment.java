package com.maybrightventures.lender.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.maybrightventures.lender.EducationInfoActivity;
import com.maybrightventures.lender.R;
import com.maybrightventures.lender.WorkInfoActivity;

/**
 * Created by arindamnath on 27/12/15.
 */
public class WorkInfoFragment extends Fragment {

    private View mViewHolder;
    private FloatingActionButton floatingActionButton;
    private ListView workDetails;

    public static WorkInfoFragment newInstance(int sectionNumber) {
        WorkInfoFragment fragment = new WorkInfoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewHolder = inflater.inflate(R.layout.fragment_work_details, container, false);
        floatingActionButton = (FloatingActionButton) mViewHolder.findViewById(R.id.add_work_details);
        workDetails = (ListView) mViewHolder.findViewById(R.id.work_details_list);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivityForResult(new Intent(getActivity(), WorkInfoActivity.class), 1);
            }
        });
        return mViewHolder;
    }
}
