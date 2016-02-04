package com.maybrightventures.lender.dao;

import android.content.Context;

import com.maybrightventures.lender.R;
import com.maybrightventures.lender.dao.enums.LoanStatus;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Date;

/**
 * Created by arindamnath on 13/01/16.
 */
public class RepaymentDAO extends BaseDAO {

    public long date;
    public String tenure;
    public float amount;
    private LoanStatus status;

    public RepaymentDAO(Context context) {
        super(context);
    }

    @Override
    protected void parse(JSONParser jsonParser, JSONObject jsonObject) {

    }

    public String getDate() {
        return getDateFormat().format(new Date(date));
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getAmount() {
        return getContext().getString(R.string.rupee) + getAmountFormatter().format(amount);
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
