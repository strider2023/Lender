package com.mbv.pokket.threads;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.mbv.pokket.dao.LendDAO;
import com.mbv.pokket.util.NetworkUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arindamnath on 18/01/16.
 */
public class LendLoaderTask extends AsyncTaskLoader<List<LendDAO>> {

    private NetworkUtils networkUtils;
    private Map<String, Object> params = new HashMap<>();
    private List<LendDAO> lendDAOList = new ArrayList<>();

    public LendLoaderTask(Context context, Map<String, Object> params) {
        super(context);
        this.networkUtils = new NetworkUtils(context);
        this.params = params;
    }

    @Override
    public List<LendDAO> loadInBackground() {
        if(networkUtils.isNetworkAvailable()) {
            lendDAOList.clear();
            lendDAOList.add(new LendDAO("Arindam Nath", 1500, "3 Months", "3 mins ago"));
            lendDAOList.add(new LendDAO("Samarjit Choudhury", 2000, "2 Months", "30 mins ago"));
            lendDAOList.add(new LendDAO("Gaurav Jalan", 500, "1 Months", "31 Dec 2015"));
            return lendDAOList;
        } else {
            return null;
        }
    }
}
