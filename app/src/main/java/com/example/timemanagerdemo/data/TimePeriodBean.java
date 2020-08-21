package com.example.timemanagerdemo.data;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "TimePeriod")
public class TimePeriodBean {
   public static int HIGH_EFFICIENCY = 0;
  public static int MEDIUM_EFFICIENCY = 1;
  public static int LOW_EFFICIENCY = 2;
  public static int NO_OUTPUT = 3;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "event")
    public String event;

    @ColumnInfo(name = "start_time")
    public long startTime;

    @ColumnInfo(name = "end_time")
    public long endTime;

    @ColumnInfo(name = "output")
    public String output;


    @ColumnInfo(name = "output_situation")
    public int outputSituation;

    @ColumnInfo(name = "reason_and_reflection")
    public String reasonAndReflection;

    public int getOutputSituation() {
        return outputSituation;
    }

    public void setOutputSituation(int outputSituation) {
        this.outputSituation = outputSituation;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getReasonAndReflection() {
        return reasonAndReflection;
    }

    public void setReasonAndReflection(String reasonAndReflection) {
        this.reasonAndReflection = reasonAndReflection;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sT=sdf.format(getStartTime());
        String eT=sdf.format(getEndTime());
        return "TimePeriodBean{" +
                "event='" + event + '\'' +
                ", startTime=" + sT +
                ", endTime=" + eT +
                ", output='" + output + '\'' +
                ", outputSituation=" + outputSituation +
                ", reasonAndReflection='" + reasonAndReflection + '\'' +
                '}';
    }


}
