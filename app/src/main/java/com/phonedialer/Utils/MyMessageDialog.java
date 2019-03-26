package com.phonedialer.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

/**
 * Created by Admin1 on 4/15/2016.
 */
public class MyMessageDialog  {

    public interface MyMessageDialogListener {
        public void onClosed(String ship, String scientist, String email, String volume, String color);
    }

    @SuppressLint("NewApi")
    public static AlertDialog displayMessage(Context context, String title, String message, final MyMessageDialogListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        builder.setTitle(title);
        builder.setMessage(message);
//        final View layoutView = inflater.inflate(R.layout.custom_view, null);
//        builder.setView(layoutView);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // get the edit text values here and pass them back via the listener
                if(listener != null)
                {

//                    listener.onClosed(text1.getText().toString(),
//                            text2.getText().toString(),
//                            text3.getText().toString(),
//                            text4.getText().toString(),
//                            text5.getText().toString());
                }

                dialog.cancel();
            }
        });
        builder.show();
        return builder.create();
    }

}
