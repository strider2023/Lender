package com.mbv.pokket.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import com.mbv.pokket.R;

import java.util.Calendar;

/**
 *
 */
public class ProfileFragment extends Fragment {

    private View mViewHolder;
    private Button dobSeletion;
    private CheckBox isAddressSame;
    private EditText currentAddress;

    public static ProfileFragment newInstance(int sectionNumber) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewHolder = inflater.inflate(R.layout.fragment_profile, container, false);
        dobSeletion = (Button) mViewHolder.findViewById(R.id.profile_dob);
        isAddressSame = (CheckBox) mViewHolder.findViewById(R.id.profile_address_checkbox);
        currentAddress = (EditText) mViewHolder.findViewById(R.id.profile_current_address);

        isAddressSame.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    currentAddress.setVisibility(View.GONE);
                } else {
                    currentAddress.setVisibility(View.VISIBLE);
                }
            }
        });

        dobSeletion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDateFragment selectDateFragment = new SelectDateFragment();
                selectDateFragment.show(getActivity().getSupportFragmentManager(), "dialog");
            }
        });

        return mViewHolder;
    }

    public static class SelectDateFragment  extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return   new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        @Override
        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            //populateSetDate(yy, mm + 1, dd);
        }
    }
}
