package com.maybrightventures.lender.dao;

/**
 * Created by arindamnath on 13/01/16.
 */
public class RepaymentDAO {

    public String date;
    public String loanId;
    public float amount;
    private String status;

    public RepaymentDAO() {

    }

    public RepaymentDAO(String date, String loanId, float amount, String status) {
        this.date = date;
        this.loanId = loanId;
        this.amount = amount;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
