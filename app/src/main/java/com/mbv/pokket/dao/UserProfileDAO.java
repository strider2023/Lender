package com.mbv.pokket.dao;

import android.content.Context;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 07/02/16.
 */
public class UserProfileDAO extends BaseDAO {

    private List<TimelineDAO> timelineDAOList = new ArrayList<>();

    public UserProfileDAO(Context context) {
        super(context);
    }

    @Override
    protected void parse(JSONParser jsonParser, JSONObject jsonObject) {

    }

    public List<TimelineDAO> getTimelineDAOList() {
        return timelineDAOList;
    }

    public void setTimelineDAOList(List<TimelineDAO> timelineDAOList) {
        this.timelineDAOList = timelineDAOList;
    }
}
