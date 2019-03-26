package com.phonedialer.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


/**
 */
public class SessionManager {

    private static final String PREF_NAME = "Test";
    private static final String KEY_NORMALADMIN = "normaladmin";
    private static final String KEY_LOGIN_PASS = "loginPass";
    private static final String KEY_USERID = "custid";
    private static final String KEY_LOGIN_EMAIL = "email";
    private static final String KEY_VARY_CODE = "custvarycode";
    private static final String KEY_ISLOGIN = "islogin";



    public static void setDeviceToken(Context context, String val) {
        try {
            SharedPreferences preferences = context
                    .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("TOKEN", val);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDeviceToken(Context context) {
        String strregID = "";
        try {

            SharedPreferences preferences = context
                    .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            strregID = preferences.getString(
                    "TOKEN", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strregID;
    }


    public static void setLoginPass(Context context, String pass) {
        try {
            SharedPreferences prefSignupData = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = prefSignupData.edit();
            editor.putString(KEY_LOGIN_PASS, pass);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getLoginPass(Context context) {
        String strPass = "";
        try {
            SharedPreferences pref = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            strPass = pref.getString(KEY_LOGIN_PASS, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strPass;

    }

    public static void setCustVeriCode(Context context, String strCustVarycode) {
        try {
            SharedPreferences prefSignupData = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = prefSignupData.edit();
            editor.putString(KEY_VARY_CODE, strCustVarycode);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCustVeryCode(Context context) {
        String strCustVarycode = "";
        try {
            SharedPreferences pref = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            strCustVarycode = pref.getString(KEY_VARY_CODE, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strCustVarycode;

    }

    public static String getEmailaddress(Context context) {
        String stremailaddress = "";
        try {
            SharedPreferences pref = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            stremailaddress = pref.getString(KEY_LOGIN_EMAIL, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stremailaddress;
    }


    public static void setEmailaddress(Context context, String stremail) {
        try {
            SharedPreferences pref = context
                    .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = pref.edit();
            editor.putString(KEY_LOGIN_EMAIL, stremail);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logout(Context context) {
        try {
            SharedPreferences.Editor editor = context
                    .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
            editor.clear();
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setCustID(Context context, String custId) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = preferences.edit();
        editor.putString(KEY_USERID, custId);
        editor.commit();
    }

    public static String getCustId(Context context) {
        String strCustId = "";
        try {
            SharedPreferences preferences = context
                    .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            strCustId = preferences.getString(KEY_USERID, null);
            Log.d("log", "custId===" + strCustId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strCustId;
    }


    public static void setNormalAdmin(Context context, String custId) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = preferences.edit();
        editor.putString(KEY_NORMALADMIN, custId);
        editor.commit();
    }

    public static String getNormalAdmin(Context context) {
        String strCustId = "";
        try {
            SharedPreferences preferences = context
                    .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            strCustId = preferences.getString(KEY_NORMALADMIN, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strCustId;
    }

    public static void setIsUserLoggedin(Context context, boolean val) {
        try {
            SharedPreferences preferences = context
                    .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(KEY_ISLOGIN, val);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getIsUserLoggedin(Context context) {
        // Integer val = 0;
        SharedPreferences preferences = context
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean val = preferences.getBoolean(KEY_ISLOGIN,
                false);
        return val;
    }


}
