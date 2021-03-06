package com.mbv.pokket.threads;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.mbv.pokket.dao.enums.ServerEvents;
import com.mbv.pokket.dao.interfaces.ServerResponseListener;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by arindamnath on 28/12/15.
 */
public class AuthenticationTask extends AppTask {

    private static String AUTH_URL = "http://lender-env.us-west-2.elasticbeanstalk.com/webapi/authentication";

    private String decodedString;
    private String errorMessage;

    public AuthenticationTask(int id, Context context, ServerResponseListener serverResponseListener){
        super(id, context, serverResponseListener);
    }

    @Override
    protected ServerEvents doInBackground(String... params) {
        if(getNetworkUtils().isNetworkAvailable()) {
            try {
                JSONObject dato = new JSONObject();
                dato.put("email", params[0].trim());
                dato.put("hash", Base64.encodeToString(params[1].trim().getBytes(), Base64.DEFAULT).toUpperCase());
                dato.put("device", new JSONObject(getDeviceInfo(getContext())));
                Log.e("test", dato.toString());
                getLogin(dato);
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

    private void getLogin(JSONObject object) throws Exception{
        //Write the request data
        HttpURLConnection httppost = getNetworkUtils().getHttpURLConInstance(AUTH_URL, false);
        DataOutputStream out = new DataOutputStream(httppost.getOutputStream());
        out.writeBytes(object.toString());
        out.flush();
        out.close();
        //Read the response data
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                httppost.getInputStream()));
        while ((decodedString = in.readLine()) != null)
            sb.append(decodedString);
        in.close();
        //Parse the incoming response
        Log.v("Pokket", sb.toString());
        JSONObject responseObj = (JSONObject) getParser().parse(sb.toString());
        if(responseObj.containsKey("authToken")) {
            getAppPreferences().saveSessionToken((String) responseObj.get("authToken"));
            Log.v("Pokket", (String) responseObj.get("authToken"));
        }
    }

}
