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
public class EventsLoaderTask extends AsyncTaskLoader<DashboardDAO> {

    private NetworkUtils networkUtils;
    private Map<String, Object> params = new HashMap<>();
    private DashboardDAO dashboardDAO;
    private List<CalendarEventDAO> calendarEventDAOs = new ArrayList<>();

    public EventsLoaderTask(Context context, Map<String, Object> params) {
        super(context);
        this.networkUtils = new NetworkUtils(context);
        this.params = params;
    }

    @Override
    public DashboardDAO loadInBackground() {
        if(networkUtils.isNetworkAvailable()) {
            this.dashboardDAO = new DashboardDAO(getContext());
            dashboardDAO.setTotalAmount(1200.0f);
            dashboardDAO.setBorrowed(700.0f);
            dashboardDAO.setLent(500.0f);

            CalendarEventDAO calendarEventDAO = new CalendarEventDAO(getContext());
            calendarEventDAO.setAmount(100.0f);
            calendarEventDAO.setDate(System.currentTimeMillis());
            calendarEventDAO.setLoanID("AJBVA13D123DF");
            calendarEventDAO.setId(1l);

            calendarEventDAOs.add(calendarEventDAO);
            calendarEventDAOs.add(calendarEventDAO);
            calendarEventDAOs.add(calendarEventDAO);
            calendarEventDAOs.add(calendarEventDAO);
            calendarEventDAOs.add(calendarEventDAO);
            dashboardDAO.setCalendarEventDAOArrayList(calendarEventDAOs);

            return this.dashboardDAO;
        } else {
            return null;
        }
    }
}
