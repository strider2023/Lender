package com.maybrightventures.lender;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.maybrightventures.lender.adapters.RepaymentListBaseAdapter;
import com.maybrightventures.lender.dao.LoanDetailsDAO;
import com.maybrightventures.lender.dao.RepaymentDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 13/01/16.
 */
public class LoanDetailsActivity extends AppCompatActivity {

    private View listHead;
    private RepaymentListBaseAdapter repaymentListBaseAdapter;
    private List<RepaymentDAO> repaymentDAOList = new ArrayList<>();
    private ListView listView;
    private LayoutInflater layoutInflater;

    private TextView borrowerName, lenderName, borrowerPendingAmt, lenderPendingAmt,
            loanAmt, status, loanTenure, issuedDate;

    private LoanDetailsDAO loanDetailsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
        setContentView(R.layout.activity_loan_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Loan Id: 132DFJA789F");

        repaymentListBaseAdapter = new RepaymentListBaseAdapter(this);
        repaymentDAOList.add(new RepaymentDAO("14 Jan 2016", "1 of 3", 150, "Received"));
        repaymentDAOList.add(new RepaymentDAO("14 Feb 2016", "2 of 3", 150, "Pending"));
        repaymentDAOList.add(new RepaymentDAO("14 Mar 2016", "3 of 3", 150, "Pending"));

        loanDetailsDAO = new LoanDetailsDAO(this);
        loanDetailsDAO.setBorrowerName("Arindam Nath");
        loanDetailsDAO.setLenderName("Maitri Nath");
        loanDetailsDAO.setBorrowerPendingAmt(700.35f);
        loanDetailsDAO.setLenderPendingAmt(500);
        loanDetailsDAO.setLoanAmt(1200);
        loanDetailsDAO.setStatus("Active");
        loanDetailsDAO.setLoanTenure("3 Months");
        loanDetailsDAO.setIssuedDate("5 Jan 2016");
        loanDetailsDAO.setRepaymentDAO(repaymentDAOList);

        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        listHead = layoutInflater.inflate(R.layout.content_loan_details_header, null);
        listView = (ListView) findViewById(R.id.loan_details_list);
        borrowerName = (TextView) listHead.findViewById(R.id.loan_details_borrower);
        lenderName = (TextView) listHead.findViewById(R.id.loan_details_lender);
        borrowerPendingAmt = (TextView) listHead.findViewById(R.id.loan_details_pending_amount_borrower);
        lenderPendingAmt = (TextView) listHead.findViewById(R.id.loan_details_pending_amount_lender);
        loanAmt = (TextView) listHead.findViewById(R.id.loan_details_total_amount);
        status = (TextView) listHead.findViewById(R.id.loan_details_status);
        loanTenure = (TextView) listHead.findViewById(R.id.loan_details_tenure);
        issuedDate = (TextView) listHead.findViewById(R.id.loan_details_issue_date);

        borrowerName.setText(loanDetailsDAO.getBorrowerName());
        lenderName.setText(loanDetailsDAO.getLenderName());
        borrowerPendingAmt.setText(loanDetailsDAO.getBorrowerPendingAmt());
        lenderPendingAmt.setText(loanDetailsDAO.getLenderPendingAmt());
        loanAmt.setText(loanDetailsDAO.getLoanAmt());
        status.setText(loanDetailsDAO.getStatus());
        loanTenure.setText(loanDetailsDAO.getLoanTenure());
        issuedDate.setText(loanDetailsDAO.getIssuedDate());
        repaymentListBaseAdapter.setData(loanDetailsDAO.getRepaymentDAO());

        borrowerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoanDetailsActivity.this, UserProfileActivity.class));
            }
        });

        lenderName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoanDetailsActivity.this, UserProfileActivity.class));
            }
        });

        listView.setAdapter(repaymentListBaseAdapter);
        listView.addHeaderView(listHead);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
