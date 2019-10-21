package com.example.coolplaces;

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
}
