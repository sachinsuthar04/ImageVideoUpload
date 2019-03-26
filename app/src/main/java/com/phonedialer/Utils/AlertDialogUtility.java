package com.phonedialer.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.phonedialer.R;

public class AlertDialogUtility {

    public static void ShowToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showAlert(Context context, String msg) {
        new AlertDialog.Builder(context).setIcon(0).setTitle(context.getString(R.string.app_name)).setMessage(msg).setCancelable(false).setNeutralButton("OK", null).show();
    }


    public static void CustomAlert(Context context, String title, String message, String Positive_text, String Negative_text, DialogInterface.OnClickListener PositiveListener, DialogInterface.OnClickListener NegativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(title).setMessage(message).setNeutralButton(Positive_text, PositiveListener).setNeutralButton(Negative_text, NegativeListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showConfirmAlert(Context context, String msg, DialogInterface.OnClickListener onYesClick) {
        new AlertDialog.Builder(context).setIcon(0).setTitle(context.getString(R.string.app_name)).setMessage(msg).setCancelable(false).setNegativeButton("NO", null)
                .setPositiveButton("YES", onYesClick).show();
    }

    public static void showLogoutAlert(Context context, String msg, DialogInterface.OnClickListener onYesClick) {
        new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).setIcon(0).setTitle(context.getString(R.string.app_name)).setMessage(msg).setCancelable(false).setNegativeButton("Cancel", null)
                .setPositiveButton("Logout", onYesClick).show();
    }

    public static void showDeleteAlert(Context context, String msg, DialogInterface.OnClickListener onYesClick, DialogInterface.OnClickListener onNoClick) {
        new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).setIcon(0).setTitle(context.getString(R.string.app_name)).setMessage(msg).setCancelable(false).setNegativeButton("No", onNoClick)
                .setPositiveButton("Yes", onYesClick).show();
    }
    public static void showExitAlert(Context context, String msg, DialogInterface.OnClickListener onYesClick, DialogInterface.OnClickListener onNoClick) {
        new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).setIcon(0).setTitle(context.getString(R.string.app_name)).setMessage(msg).setCancelable(false).setNegativeButton("No", onNoClick)
                .setPositiveButton("Yes", onYesClick).show();
    }
}
