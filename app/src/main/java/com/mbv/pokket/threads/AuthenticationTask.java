package com.mbv.pokket.threads;

import android.content.Context;
import android.os.AsyncTask;

import com.mbv.pokket.dao.enums.ServerEvents;
import com.mbv.pokket.dao.interfaces.ServerResponseListener;
import com.mbv.pokket.util.AppPreferences;
import com.mbv.pokket.util.NetworkUtils;

/**
 * Created by arindamnath on 28/12/15.
 */
public class AuthenticationTask extends AsyncTask<String, Void, ServerEvents> {

    private ServerResponseListener serverResponseListener;
    private NetworkUtils networkUtils;
    private AppPreferences appPreferences;
    private int id;

    public AuthenticationTask(int id, Context context, ServerResponseListener serverResponseListener){
        this.serverResponseListener = serverResponseListener;
        this.networkUtils = new NetworkUtils(context);
        this.appPreferences = new AppPreferences(context);
        this.id = id;
    }

    @Override
    protected ServerEvents doInBackground(String... params) {
        if(networkUtils.isNetworkAvailable()) {
            try {
                appPreferences.saveUserInfo("", params[0], "", params[1]);
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
