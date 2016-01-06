package com.maybrightventures.lender.threads;

import android.content.Context;
import android.os.AsyncTask;

import com.maybrightventures.lender.dao.enums.ServerEvents;
import com.maybrightventures.lender.dao.interfaces.ServerResponseListener;
import com.maybrightventures.lender.util.AppPreferences;
import com.maybrightventures.lender.util.NetworkUtils;

/**
 * Created by arindamnath on 28/12/15.
 */
public class SignUpTask extends AsyncTask<String, Void, ServerEvents> {

    private ServerResponseListener serverResponseListener;
    private NetworkUtils networkUtils;
    private AppPreferences appPreferences;
    private int id;

    public SignUpTask(int id, Context context, ServerResponseListener serverResponseListener){
        this.serverResponseListener = serverResponseListener;
        this.networkUtils = new NetworkUtils(context);
        this.appPreferences = new AppPreferences(context);
        this.id = id;
    }

    @Override
    protected ServerEvents doInBackground(String... params) {
        if(networkUtils.isNetworkAvailable()) {
            try {
                appPreferences.saveUserInfo(params[0], params[1], params[2], params[3]);
                return ServerEvents.SUCCESS;
            } catch (Exception e) {
                return ServerEvents.FALIURE;
            }
        } else {
            return ServerEvents.NO_NETWORK;
        }
    }

    @Override
    protected void onPostExecute(ServerEvents serverEvents) {
        super.onPostExecute(serverEvents);
        switch (serverEvents) {
            case SUCCESS:
                serverResponseListener.onSuccess(id);
                break;
            case FALIURE:
                serverResponseListener.onFaliure();
                break;
            case NO_NETWORK:
                break;
        }
    }
}
