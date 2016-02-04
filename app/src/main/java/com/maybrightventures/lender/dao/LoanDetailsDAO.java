package com.maybrightventures.lender.dao;

import android.content.Context;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 18/01/16.
 */
public class LoanDetailsDAO extends BaseDAO {

    private Context context;
    private String borrowerName;
    private String lenderName;
    private float borrowerPendingAmt;
    private float lenderPendingAmt;
    private float loanAmt;
    private String status;
    private String loanTenure;
    private String issuedDate;
    private List<RepaymentDAO> repaymentDAO = new ArrayList<>();

    public LoanDetailsDAO(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void parse(JSONParser jsonParser, JSONObject jsonObject) {

    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public String getBorrowerPendingAmt() {
        return getAmountFormatter().format(borrowerPendingAmt);
    }

    public void setBorrowerPendingAmt(float borrowerPendingAmt) {
        this.borrowerPendingAmt = borrowerPendingAmt;
    }

    public String getLenderPendingAmt() {
        return getAmountFormatter().format(lenderPendingAmt);
    }

    public void setLenderPendingAmt(float lenderPendingAmt) {
        this.lenderPendingAmt = lenderPendingAmt;
    }

    public String getLoanAmt() {
        return getAmountFormatter().format(loanAmt);
    }

    public void setLoanAmt(float loanAmt) {
        this.loanAmt = loanAmt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoanTenure() {
        return loanTenure;
    }

    public void setLoanTenure(String loanTenure) {
        this.loanTenure = loanTenure;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public List<RepaymentDAO> getRepaymentDAO() {
        return repaymentDAO;
    }

    public void setRepaymentDAO(List<RepaymentDAO> repaymentDAO) {
        this.repaymentDAO = repaymentDAO;
    }
}
