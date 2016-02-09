package com.mbv.pokket.threads;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.mbv.pokket.dao.TimelineDAO;
import com.mbv.pokket.dao.UserProfileDAO;
import com.mbv.pokket.dao.enums.TimelineEvents;
import com.mbv.pokket.util.NetworkUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arindamnath on 07/02/16.
 */
public class TimelineLoaderTask extends AsyncTaskLoader<UserProfileDAO> {

    private NetworkUtils networkUtils;
    private Map<String, Object> params = new HashMap<>();
    private UserProfileDAO userProfileDAO;
    private List<TimelineDAO> timelineDAOs = new ArrayList<>();

    public TimelineLoaderTask(Context context, Map<String, Object> params) {
        super(context);
        this.networkUtils = new NetworkUtils(context);
        this.params = params;
    }

    @Override
    public UserProfileDAO loadInBackground() {
        if(networkUtils.isNetworkAvailable()) {
            this.timelineDAOs.clear();
            userProfileDAO = new UserProfileDAO(getContext());

            TimelineDAO timelineDAO = new TimelineDAO(getContext());
            timelineDAO.setTimelineEvents(TimelineEvents.PROFILE_UPDATE);
            timelineDAO.setId(1l);
            timelineDAO.setContent("You updated your profile.");
            timelineDAO.setLoanID(1l);
            timelineDAO.setProfileID(23l);
            timelineDAO.setTimestamp(System.currentTimeMillis());
            timelineDAOs.add(timelineDAO);

            timelineDAO = new TimelineDAO(getContext());
            timelineDAO.setTimelineEvents(TimelineEvents.LOAN_REQUESTED);
            timelineDAO.setId(1l);
            timelineDAO.setContent("You requested a loan of 500 for period of 1 month. Loan ref. id. 132ADFA13S.");
            timelineDAO.setLoanID(1l);
            timelineDAO.setProfileID(23l);
            timelineDAO.setTimestamp(1452081251000l);
            timelineDAOs.add(timelineDAO);

            timelineDAO = new TimelineDAO(getContext());
            timelineDAO.setTimelineEvents(TimelineEvents.LOAN_APPROVED);
            timelineDAO.setId(1l);
            timelineDAO.setContent("Your request for a loan of 500 for period of 1 month has been approved. Loan ref. id. 132ADFA13S.");
            timelineDAO.setLoanID(1l);
            timelineDAO.setProfileID(23l);
            timelineDAO.setTimestamp(1452041251000l);
            timelineDAOs.add(timelineDAO);

            timelineDAO = new TimelineDAO(getContext());
            timelineDAO.setTimelineEvents(TimelineEvents.APPROVED_LOAN);
            timelineDAO.setId(1l);
            timelineDAO.setContent("Your approved a loan request by Arindam Nath of 500 for 3 Months. Loan ref. id. 132ADFA13S.");
            timelineDAO.setLoanID(1l);
            timelineDAO.setProfileID(23l);
            timelineDAO.setTimestamp(1452081251000l);
            timelineDAOs.add(timelineDAO);

            timelineDAO = new TimelineDAO(getContext());
            timelineDAO.setTimelineEvents(TimelineEvents.RATING_GIVEN);
            timelineDAO.setId(1l);
            timelineDAO.setContent("You gave Arindam Nath a 5 star rating");
            timelineDAO.setLoanID(1l);
            timelineDAO.setProfileID(23l);
            timelineDAO.setTimestamp(1452081252000l);
            timelineDAOs.add(timelineDAO);

            timelineDAO = new TimelineDAO(getContext());
            timelineDAO.setTimelineEvents(TimelineEvents.RATING_RECEIVED);
            timelineDAO.setId(1l);
            timelineDAO.setContent("You received 4 star rating from Arindam Nath");
            timelineDAO.setLoanID(1l);
            timelineDAO.setProfileID(23l);
            timelineDAO.setTimestamp(1453081251000l);
            timelineDAOs.add(timelineDAO);

            timelineDAO = new TimelineDAO(getContext());
            timelineDAO.setTimelineEvents(TimelineEvents.DEFAULTED);
            timelineDAO.setId(1l);
            timelineDAO.setContent("You defaulted on a loan of 500 for period of 1 month. Loan ref. id. 132ADFA13S.");
            timelineDAO.setLoanID(1l);
            timelineDAO.setProfileID(23l);
            timelineDAO.setTimestamp(1452081351000l);
            timelineDAOs.add(timelineDAO);

            userProfileDAO.setTimelineDAOList(timelineDAOs);
            return userProfileDAO;
        } else {
            return null;
        }
    }
}
