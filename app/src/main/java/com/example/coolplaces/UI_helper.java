package com.example.coolplaces;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.EditText;

import java.util.Date;
import java.util.concurrent.TimeUnit;

//used to display some data in right format
public class UI_helper {
    public static String timePass(Date pastDate){
        Date now = new Date();
        long seconds= TimeUnit.MILLISECONDS.toSeconds(now.getTime() - pastDate.getTime());
        long minutes=TimeUnit.MILLISECONDS.toMinutes(now.getTime() - pastDate.getTime());
        long hours=TimeUnit.MILLISECONDS.toHours(now.getTime() - pastDate.getTime());
        long days=TimeUnit.MILLISECONDS.toDays(now.getTime() - pastDate.getTime());

        if(seconds<60)
        {
            return new String(seconds+"sec ago");
        }
        else if(minutes<60)
        {
            return new String(minutes+"min ago");
        }
        else if(hours<24)
        {
            return new String(hours+"hours ago");
        }
        else
        {
            return new String(days+"days ago");
        }
    }

    //get file path from uri
    public static String getPath(Uri uri, Activity activity) {
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = activity
                .managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    //check if empty and set error
    public static boolean emptyEdtTextSetError(EditText editText){
        boolean isEmpty = editText.getText().toString().equals("");
        if(isEmpty){
            editText.setError("Must not be empty!");
        }
        return  isEmpty;
    }

    //check if editText has error
    public static boolean edtTextHasError(EditText editText){
        CharSequence error = editText.getError();
        if(error == null)
        {
            return false;
        }
        return true;
    }
}
