package com.example.timemanagerdemo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TimePeriodBeanDao {

    @Insert
    public void insert(TimePeriodBean timePeriodBean);

    @Delete
    public int delete(TimePeriodBean timePeriodBean);

    @Query("SELECT * FROM TimePeriod")
    public List<TimePeriodBean> getTimePeriodList();

    @Query("SELECT * FROM TimePeriod WHERE event = :event")
    public TimePeriodBean queryTimePeriodByEvent(String event);
}
