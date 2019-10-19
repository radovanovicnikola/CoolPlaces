package com.example.coolplaces;

import android.app.Activity;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class ErrorDialog {
    private AlertDialog dialog;

    ErrorDialog(Activity activityContext){
        dialog=new AlertDialog.Builder(activityContext).create();
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    void show(String title,String msg){
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.show();
    }
}
