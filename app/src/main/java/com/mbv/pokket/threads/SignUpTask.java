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
public class SignUpTask extends AppTask {

    private static String SIGN_UP_URL = "http://lender-env.us-west-2.elasticbeanstalk.com/webapi/authentication/signup";

    private String decodedString;
    private String errorMessage;

    public SignUpTask(int id, Context context, ServerResponseListener serverResponseListener){
        super(id, context, serverResponseListener);
    }

    @Override
    protected ServerEvents doInBackground(String... params) {
        if(getNetworkUtils().isNetworkAvailable()) {
            try {
                JSONObject dato = new JSONObject();
                String[] userName = params[0].trim().split("\\s+");
                if(userName.length == 1) {
                    dato.put("firstName", userName[0]);
                } else if(userName.length == 2) {
                    dato.put("firstName", userName[0]);
                    dato.put("lastName", userName[1]);
                } else if(userName.length > 2) {
                    dato.put("firstName", userName[0]);
                    dato.put("middleName", userName[1]);
                    dato.put("lastName", userName[2]);
                }
                dato.put("email", params[1].trim());
                dato.put("phone", Long.parseLong(params[2]));
                dato.put("hash", Base64.encodeToString(params[3].trim().getBytes(), Base64.DEFAULT).toUpperCase());
                dato.put("device", new JSONObject(getDeviceInfo(getContext())));
                Log.v("test", dato.toString());
                //getSignUp(dato);
                getAppPreferences().saveUserInfo(params[0], params[1], params[2], params[3]);
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

    private void getSignUp(JSONObject object) throws Exception{
        //Write the request data
        HttpURLConnection httppost = getNetworkUtils().getHttpURLConInstance(SIGN_UP_URL, false);
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
