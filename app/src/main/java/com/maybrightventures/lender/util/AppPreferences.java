package com.maybrightventures.lender.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by arindamnath on 27/12/15.
 */
public class AppPreferences {

    private SharedPreferences mAppPrefs;

    public AppPreferences(Context context) {
        mAppPrefs = context.getSharedPreferences("AppPreferences", 0);
    }

    public void saveUserInfo(String name, String email, String phone, String password) {
        SharedPreferences.Editor edit = mAppPrefs.edit();
        String[] userName = name.split("\\s+");
        if(userName.length == 1) {
            edit.putString("userFirstName", userName[0]);
        } else if(userName.length == 2) {
            edit.putString("userFirstName", userName[0]);
            edit.putString("userLastName", userName[1]);
        } else if(userName.length >= 3) {
            edit.putString("userFirstName", userName[0]);
            edit.putString("userMiddleName", userName[1]);
            edit.putString("userLastName", userName[2]);
        }
        edit.putString("userEmail", email);
        edit.putString("userPhone", phone);
        edit.putString("userPassword", password);
        edit.commit();
    }

    public String getUserFirstName() {
        return mAppPrefs.getString("userFirstName", null);
    }

    public String getUserMiddleName() {
        return mAppPrefs.getString("userMiddleName", null);
    }

    public String getUserLastName() {
        return mAppPrefs.getString("userLastName", null);
    }

    public String getUserEmail() {
        return mAppPrefs.getString("userEmail", null);
    }

    public String getUserPhone() {
        return mAppPrefs.getString("userPhone", null);
    }

    public String getUserPassword() {
        return mAppPrefs.getString("userPassword", null);
    }

    public void setLoggedIn() {
        SharedPreferences.Editor edit = mAppPrefs.edit();
        edit.putBoolean("appLoggedIn", true);
        edit.commit();
    }

    public void setLoggedOut() {
        SharedPreferences.Editor edit = mAppPrefs.edit();
        edit.putBoolean("appLoggedIn", false);
        edit.commit();
    }

    public boolean isUserLoggedIn() {
        return mAppPrefs.getBoolean("appLoggedIn", false);
    }
}
