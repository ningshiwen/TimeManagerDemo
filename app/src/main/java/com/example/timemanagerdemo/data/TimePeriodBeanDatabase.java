package com.example.timemanagerdemo.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TimePeriodBean.class}, version = 1, exportSchema = false)
public abstract class TimePeriodBeanDatabase extends RoomDatabase {
    public abstract TimePeriodBeanDao getTimePeriodDao();

    private static final String DB_NAME = "TimePeriodBean.db";
    private static volatile TimePeriodBeanDatabase instance;

   public static synchronized TimePeriodBeanDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static TimePeriodBeanDatabase create(Context context) {
        return Room.databaseBuilder(context, TimePeriodBeanDatabase.class, DB_NAME).build();
    }

}
