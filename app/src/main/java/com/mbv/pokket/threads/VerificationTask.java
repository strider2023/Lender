package com.mbv.pokket.threads;

import android.content.Context;
import android.util.Log;

import com.mbv.pokket.dao.enums.ServerEvents;
import com.mbv.pokket.dao.interfaces.ServerResponseListener;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by arindamnath on 12/02/16.
 */
public class VerificationTask extends AppTask {

    private static final String MSG_URL = "";
    
    private String decodedString;
    private String errorMessage;

    public VerificationTask(int id, Context context, ServerResponseListener serverResponseListener){
        super(id, context, serverResponseListener);
    }

    @Override
    protected ServerEvents doInBackground(String... params) {
        if(getNetworkUtils().isNetworkAvailable()) {
            try {
                /*JSONObject dato = new JSONObject();
                dato.put("email", params[0].trim());
                dato.put("device", new JSONObject(getDeviceInfo(getContext())));
                Log.e("test", dato.toString());
                getLogin(dato);*/
                getOTP(null);
                return ServerEvents.SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
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
                getServerResponseListener().onSuccess(getId(), null);
                break;
            case FALIURE:
                getServerResponseListener().onFaliure(ServerEvents.FALIURE, null);
                break;
            case NO_NETWORK:
                getServerResponseListener().onFaliure(ServerEvents.NO_NETWORK, null);
                break;
        }
    }

    private void getOTP(JSONObject object) throws Exception{
        //Write the request data
        HttpURLConnection httppost = getNetworkUtils().getHttpURLConInstance(MSG_URL, true);
        /*DataOutputStream out = new DataOutputStream(httppost.getOutputStream());
        out.writeBytes(object.toString());
        out.flush();
        out.close();*/
        //Read the response data
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(httppost.getInputStream()));
        while ((decodedString = in.readLine()) != null)
            sb.append(decodedString);
        in.close();
        //Parse the incoming response
        Log.v("Pokket", sb.toString());
        /*JSONObject responseObj = (JSONObject) getParser().parse(sb.toString());
        if(responseObj.containsKey("authToken")) {
            getAppPreferences().saveSessionToken((String) responseObj.get("authToken"));
            Log.v("Pokket", (String) responseObj.get("authToken"));
        }*/
    }
}
