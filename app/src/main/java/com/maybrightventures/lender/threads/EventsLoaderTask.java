package com.maybrightventures.lender.threads;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.maybrightventures.lender.dao.CalendarEventDAO;
import com.maybrightventures.lender.dao.DashboardDAO;
import com.maybrightventures.lender.util.NetworkUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arindamnath on 03/02/16.
 */
public class EventsLoaderTask extends AsyncTaskLoader<List<Object>> {

    private NetworkUtils networkUtils;
    private Map<String, Object> params = new HashMap<>();
    private List<Object> calendarEventDAOs = new ArrayList<>();

    public EventsLoaderTask(Context context, Map<String, Object> params) {
        super(context);
        this.networkUtils = new NetworkUtils(context);
        this.params = params;
    }

    @Override
    public List<Object> loadInBackground() {
        if(networkUtils.isNetworkAvailable()) {
            this.calendarEventDAOs.clear();

            CalendarEventDAO calendarEventDAO = new CalendarEventDAO(getContext());
            calendarEventDAO.setAmount(100.0f);
            calendarEventDAO.setDate(1452081252000l);
            calendarEventDAO.setLoanID("AJBVA13D123DF");
            calendarEventDAO.setId(1l);

            calendarEventDAOs.add("Upcoming Payments");
            calendarEventDAOs.add(calendarEventDAO);
            calendarEventDAOs.add(calendarEventDAO);
            calendarEventDAOs.add(calendarEventDAO);
            calendarEventDAOs.add("Upcoming Dues");
            calendarEventDAOs.add(calendarEventDAO);
            calendarEventDAOs.add(calendarEventDAO);

            return calendarEventDAOs;
        } else {
            return null;
        }
    }
}
