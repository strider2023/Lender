package com.maybrightventures.lender.threads;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.maybrightventures.lender.dao.LoanDetailsDAO;
import com.maybrightventures.lender.dao.RepaymentDAO;
import com.maybrightventures.lender.dao.enums.LoanStatus;
import com.maybrightventures.lender.util.NetworkUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arindamnath on 04/02/16.
 */
public class LoanDetailLoaderTask extends AsyncTaskLoader<LoanDetailsDAO> {

    private NetworkUtils networkUtils;
    private Map<String, Object> params = new HashMap<>();
    private LoanDetailsDAO loanDetailsDAO;
    private List<RepaymentDAO> repaymentDAOList = new ArrayList<>();


    public LoanDetailLoaderTask(Context context, Map<String, Object> params) {
        super(context);
        this.networkUtils = new NetworkUtils(context);
        this.params = params;
    }

    @Override
    public LoanDetailsDAO loadInBackground() {
        if(networkUtils.isNetworkAvailable()) {
            repaymentDAOList.clear();
            loanDetailsDAO = new LoanDetailsDAO(getContext());
            loanDetailsDAO.setBorrowerName("Arindam Nath");
            loanDetailsDAO.setLenderName("Maitri Nath");
            loanDetailsDAO.setBorrowerPendingAmt(700.35f);
            loanDetailsDAO.setLenderPendingAmt(500);
            loanDetailsDAO.setLoanAmt(1200);
            loanDetailsDAO.setStatus("Active");
            loanDetailsDAO.setLoanTenure("3 Months");
            loanDetailsDAO.setIssuedDate("5 Jan 2016");
            loanDetailsDAO.setLoanId("HGHF134GG137GG");

            RepaymentDAO repaymentDAO = new RepaymentDAO(getContext());
            repaymentDAO.setId(1l);
            repaymentDAO.setAmount(150);
            repaymentDAO.setDate(1452081252000l);
            repaymentDAO.setTenure("1 of 2");
            repaymentDAO.setStatus(LoanStatus.RECEIVED);

            RepaymentDAO repaymentDAO1 = new RepaymentDAO(getContext());
            repaymentDAO1.setId(1l);
            repaymentDAO1.setAmount(150);
            repaymentDAO1.setDate(System.currentTimeMillis());
            repaymentDAO1.setTenure("2 of 2");
            repaymentDAO1.setStatus(LoanStatus.PENDING);

            repaymentDAOList.add(repaymentDAO);
            repaymentDAOList.add(repaymentDAO1);
            loanDetailsDAO.setRepaymentDAO(repaymentDAOList);
            return loanDetailsDAO;
        } else {
            return null;
        }
    }
}
