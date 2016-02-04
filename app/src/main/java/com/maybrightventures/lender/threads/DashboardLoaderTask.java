package com.maybrightventures.lender.threads;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.maybrightventures.lender.dao.RepaymentDAO;
import com.maybrightventures.lender.util.NetworkUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arindamnath on 18/01/16.
 */
public class DashboardLoaderTask extends AsyncTaskLoader<List<RepaymentDAO>> {

    private NetworkUtils networkUtils;
    private Map<String, Object> params = new HashMap<>();
    private List<RepaymentDAO> repaymentDAOList = new ArrayList<>();

    public DashboardLoaderTask(Context context, Map<String, Object> params) {
        super(context);
        this.networkUtils = new NetworkUtils(context);
        this.params = params;
    }

    @Override
    public List<RepaymentDAO> loadInBackground() {
        if(networkUtils.isNetworkAvailable()) {
            repaymentDAOList.add(new RepaymentDAO("Today", "3 of 4", 500, null));
            repaymentDAOList.add(new RepaymentDAO("Today", "3 of 4", 500, null));
            repaymentDAOList.add(new RepaymentDAO("Today", "3 of 4", 500, null));
            repaymentDAOList.add(new RepaymentDAO("Tomorrow", "1 of 2", 300.34f, null));
            repaymentDAOList.add(new RepaymentDAO("16 Jan 2016", "5 of 5", 457.40f, null));
            repaymentDAOList.add(new RepaymentDAO("20 Jan 2016", "5 of 5", 457.40f, null));
            repaymentDAOList.add(new RepaymentDAO("23 Jan 2016", "5 of 5", 457.40f, null));
            return repaymentDAOList;
        } else {
            return null;
        }
    }
}
