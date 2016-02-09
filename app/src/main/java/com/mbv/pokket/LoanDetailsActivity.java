package com.mbv.pokket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mbv.pokket.adapters.LoanDetailsRecyclerAdapter;
import com.mbv.pokket.dao.LoanDetailsDAO;
import com.mbv.pokket.dao.RepaymentDAO;
import com.mbv.pokket.dao.enums.LoaderID;
import com.mbv.pokket.threads.LoanDetailLoaderTask;

import java.util.ArrayList;

/**
 * Created by arindamnath on 13/01/16.
 */
public class LoanDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<LoanDetailsDAO> {

    private LoanDetailsRecyclerAdapter loanDetailsRecyclerAdapter;
    private RecyclerView listView;
    private ProgressDialog progressDialog;
    private Bundle queryData;

    private TextView borrowerName, lenderName, borrowerPendingAmt, lenderPendingAmt,
            loanAmt, status, loanTenure, issuedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
        setContentView(R.layout.activity_loan_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Loan Id: 132DFJA789F");

        loanDetailsRecyclerAdapter = new LoanDetailsRecyclerAdapter();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Content...");

        listView = (RecyclerView) findViewById(R.id.loan_details_list);
        borrowerName = (TextView) findViewById(R.id.loan_details_borrower);
        lenderName = (TextView) findViewById(R.id.loan_details_lender);
        borrowerPendingAmt = (TextView) findViewById(R.id.loan_details_pending_amount_borrower);
        lenderPendingAmt = (TextView) findViewById(R.id.loan_details_pending_amount_lender);
        loanAmt = (TextView) findViewById(R.id.loan_details_total_amount);
        status = (TextView) findViewById(R.id.loan_details_status);
        loanTenure = (TextView) findViewById(R.id.loan_details_tenure);
        issuedDate = (TextView) findViewById(R.id.loan_details_issue_date);

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

        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(loanDetailsRecyclerAdapter);

        queryData = new Bundle();
        queryData.putString("query", "");
        getSupportLoaderManager().initLoader(LoaderID.TIMELINE.getValue(), queryData, this).forceLoad();
        progressDialog.show();
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

    @Override
    public Loader<LoanDetailsDAO> onCreateLoader(int id, Bundle args) {
        progressDialog.show();
        return new LoanDetailLoaderTask(this, null);
    }

    @Override
    public void onLoadFinished(Loader<LoanDetailsDAO> loader, LoanDetailsDAO data) {
        progressDialog.dismiss();
        if(data != null) {
            getSupportActionBar().setSubtitle(data.getLoanId());
            borrowerName.setText(data.getBorrowerName());
            lenderName.setText(data.getLenderName());
            borrowerPendingAmt.setText(data.getBorrowerPendingAmt());
            lenderPendingAmt.setText(data.getLenderPendingAmt());
            loanAmt.setText(data.getLoanAmt());
            status.setText(data.getStatus());
            loanTenure.setText(data.getLoanTenure());
            issuedDate.setText(data.getIssuedDate());
            loanDetailsRecyclerAdapter.setData(data.getRepaymentDAO());
        } else {
            loanDetailsRecyclerAdapter.setData(new ArrayList<RepaymentDAO>());
        }
    }

    @Override
    public void onLoaderReset(Loader<LoanDetailsDAO> loader) {

    }
}
