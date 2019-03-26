package com.phonedialer.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.WindowManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GlobalMethods {
    public static boolean isSuccess = false;

    public static String bitmapToBase64(Bitmap bmp) {
        String strBase64 = "";
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            strBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strBase64;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public static Uri getUriFromBitMap(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static String getDeviceID(Context context) {
        String android_id = "";
        try {
            android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return android_id;
    }

    public static int secondsToFrames(double seconds, int mSampleRate, int mSamplesPerFrame) {
        return (int) (1.0 * seconds * mSampleRate / mSamplesPerFrame + 0.5);
    }

    public static void hideKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public static String getStringResource(int id, Context context) {
        return context.getResources().getString(id);
    }

    static public String getDateFormattedDateFromDayMonthYear(int day, int month, int year) {
        String newDate = "";
        try {
            return GlobalMethods.getMonthMMM(month + "") + " " + getTwoDigits(day) + ", " + year;
        } catch (Exception e) {
            newDate = year + "/" + month + "/" + day;
        }
        return newDate;
    }

    public static String getMonthMMM(String date) {
        if (date != null) {
            if (date.equals("1") || date.equals("01")) {
                date = "Jan";
            } else if (date.equals("2") || date.equals("02")) {
                date = "Feb";
            } else if (date.equals("3") || date.equals("03")) {
                date = "Mar";
            } else if (date.equals("4") || date.equals("04")) {
                date = "Apr";
            } else if (date.equals("5") || date.equals("05")) {
                date = "May";
            } else if (date.equals("6") || date.equals("06")) {
                date = "Jun";
            } else if (date.equals("7") || date.equals("07")) {
                date = "Jul";
            } else if (date.equals("8") || date.equals("08")) {
                date = "Aug";
            } else if (date.equals("9") || date.equals("09")) {
                date = "Sep";
            } else if (date.equals("10") || date.equals("10")) {
                date = "Oct";
            } else if (date.equals("11") || date.equals("11")) {
                date = "Nov";
            } else if (date.equals("12") || date.equals("12")) {
                date = "Dec";
            }
        }
        return date;
    }

    static public String getTwoDigits(int i) {
        if (i < 10) {
            return "0" + i;
        } else {
            return i + "";
        }
    }

    static public String getDateFormattedDateTimeFromDateTime(String oldDate) {
        String newDate = "";
        try {
            String[] date = oldDate.split(" ")[0].split("-");
            String[] time = oldDate.split(" ")[1].split(":");
            return date[2] + " " + GlobalMethods.getMonthMMM(date[1]) + " " + date[0] + " " + (Integer.parseInt(time[0]) > 12 ? (Integer.parseInt(time[0]) - 12) : time[0]) + ":" + time[1] + " " + (Integer.parseInt(time[0]) > 12 ? "PM" : "AM");
        } catch (Exception e) {
            newDate = oldDate;
        }
        return newDate;
    }
}
