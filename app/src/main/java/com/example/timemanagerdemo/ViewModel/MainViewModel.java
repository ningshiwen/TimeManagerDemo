package com.example.timemanagerdemo.ViewModel;

import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timemanagerdemo.data.TimePeriodBean;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class MainViewModel extends ViewModel {
    static String TAG = "MainViewModel";
    public MutableLiveData<TimePeriodBean> timePeriodBean = new MutableLiveData<TimePeriodBean>();
    private TimePeriodBean bean = new TimePeriodBean();

    public MutableLiveData<TimePeriodBean> getTimePeriodBean() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            timePeriodBean.setValue(bean);
        } else {
            timePeriodBean.postValue(bean);
        }
        return timePeriodBean;
    }


    public void setStartTime(long startTime) {
        bean.setStartTime(startTime);
        timePeriodBean.setValue(bean);
    }

    public void setEndTime(long endTime) {
        bean.setEndTime(endTime);
        timePeriodBean.setValue(bean);

    }

    public void setEvent(String event) {
        bean.setEvent(event);
        timePeriodBean.setValue(bean);

        Log.d(TAG, "setEvent: " + event);
    }

    public void setOutput(String output) {
        bean.setOutput(output);
        timePeriodBean.setValue(bean);

    }

    public void setOutputSituation(int outputSituation) {
        bean.setOutputSituation(outputSituation);
        timePeriodBean.setValue(bean);

    }

    public void setReasonAndReflection(String reasonAndReflection) {
        bean.setReasonAndReflection(reasonAndReflection);
        timePeriodBean.setValue(bean);

    }

}
