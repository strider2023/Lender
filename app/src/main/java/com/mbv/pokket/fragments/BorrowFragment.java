package com.mbv.pokket.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mbv.pokket.R;
import com.mbv.pokket.adapters.RepaymentListBaseAdapter;
import com.mbv.pokket.dao.RepaymentDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 12/01/16.
 */
public class BorrowFragment extends Fragment {

    private View listHead, listFooter;
    private View mViewHolder, selectorContainer, quoteContainer;
    private RepaymentListBaseAdapter repaymentListBaseAdapter;
    private List<RepaymentDAO> repaymentDAOList = new ArrayList<>();
    private Spinner amountSpinner, timeSpinner;
    private EditText noteEditText, codeEditText;
    private TextView amount, tenure, note, referralCode;
    private ListView borrowDetails;

    public static BorrowFragment newInstance() {
        BorrowFragment fragment = new BorrowFragment();
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
        repaymentListBaseAdapter = new RepaymentListBaseAdapter(getActivity());
        RepaymentDAO repaymentDAO = new RepaymentDAO(getActivity());
        repaymentDAO.setId(1l);
        repaymentDAO.setAmount(150);
        repaymentDAO.setDate(System.currentTimeMillis());
        repaymentDAO.setTenure("1 of 1");
        repaymentDAOList.add(repaymentDAO);
        repaymentDAOList.add(repaymentDAO);
        repaymentDAOList.add(repaymentDAO);
        repaymentListBaseAdapter.setData(repaymentDAOList);

        mViewHolder = inflater.inflate(R.layout.fragment_borrow, container, false);
        selectorContainer = mViewHolder.findViewById(R.id.borrow_selector_container);
        quoteContainer = mViewHolder.findViewById(R.id.borrow_quote_container);
        listHead = inflater.inflate(R.layout.content_borrow_header, null);
        listFooter = inflater.inflate(R.layout.content_payment_footer, null);

        borrowDetails = (ListView) mViewHolder.findViewById(R.id.borrow_repayment_list);
        amountSpinner = (Spinner) mViewHolder.findViewById(R.id.borrow_amount);
        timeSpinner = (Spinner) mViewHolder.findViewById(R.id.borrow_teneure);
        noteEditText = (EditText) mViewHolder.findViewById(R.id.borrow_user_note);
        codeEditText = (EditText) mViewHolder.findViewById(R.id.borrow_user_referral_code);
        amount = (TextView) listHead.findViewById(R.id.content_borrow_requested_amount);
        tenure = (TextView) listHead.findViewById(R.id.content_borrow_requested_tenure);
        note = (TextView) listHead.findViewById(R.id.content_borrow_requested_note);
        referralCode = (TextView) listHead.findViewById(R.id.content_borrow_requested_referral_code);

        mViewHolder.findViewById(R.id.borrow_get_quote_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutToggle(false);
                amount.setText(amountSpinner.getSelectedItem().toString() + " @ 14%");
                tenure.setText(timeSpinner.getSelectedItem().toString());
                if (noteEditText.getText().toString().trim().length() > 0) {
                    note.setText(noteEditText.getText().toString().trim());
                } else {
                    note.setText(R.string.na);
                }
                if (codeEditText.getText().toString().trim().length() > 0) {
                    referralCode.setText(codeEditText.getText().toString().trim());
                } else {
                    referralCode.setText(R.string.na);
                }
            }
        });

        listHead.findViewById(R.id.content_borrow_edit_quote_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layoutToggle(true);
                        noteEditText.getText().clear();
                    }
                });

        mViewHolder.findViewById(R.id.borrow_submit_loan_request)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layoutToggle(true);
                        noteEditText.getText().clear();
                        Snackbar.make(v, "Your loan request is now active.", Snackbar.LENGTH_LONG)
                                .setAction("UNDO", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Snackbar.make(v, "Your loan loan request has been cancelled.", Snackbar.LENGTH_LONG).show();
                                    }
                                })
                                .show();
                    }
                });

        borrowDetails.setAdapter(repaymentListBaseAdapter);
        borrowDetails.addHeaderView(listHead);
        borrowDetails.addFooterView(listFooter);
        return mViewHolder;
    }

    @Override
    public void onResume() {
        super.onResume();
        layoutToggle(true);
    }

    private void layoutToggle(boolean isSelectorLayout) {
        if(isSelectorLayout) {
            selectorContainer.animate()
                    .setDuration(500)
                    .translationY(0);
            quoteContainer.animate()
                    .setDuration(750)
                    .translationY(quoteContainer.getHeight())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            quoteContainer.setVisibility(View.GONE);
                        }
                    });
        } else {
            selectorContainer.animate()
                    .setDuration(500)
                    .translationY(-selectorContainer.getHeight());
            quoteContainer.animate()
                    .setDuration(750)
                    .translationY(0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            quoteContainer.setVisibility(View.VISIBLE);
                        }
                    });
        }
    }
}
