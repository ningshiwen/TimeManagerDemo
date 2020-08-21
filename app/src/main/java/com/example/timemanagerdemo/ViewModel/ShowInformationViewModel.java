package com.example.timemanagerdemo.ViewModel;


import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timemanagerdemo.data.TimePeriodBean;
import com.example.timemanagerdemo.data.TimePeriodBeanDatabase;

import java.util.List;

public class ShowInformationViewModel extends ViewModel {
    private MutableLiveData<List<TimePeriodBean>> timePeriodList;
    Context mContext;
    List<TimePeriodBean> list;


    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public MutableLiveData<List<TimePeriodBean>> getTimePeriodList() {
        list = TimePeriodBeanDatabase.getInstance(mContext).getTimePeriodDao().getTimePeriodList();
        if (timePeriodList == null) {
            timePeriodList = new MutableLiveData<List<TimePeriodBean>>();
            timePeriodList.setValue(list);
        }
        return timePeriodList;
    }


    public void setTimePeriodList(MutableLiveData<List<TimePeriodBean>> timePeriodList) {
        this.timePeriodList = timePeriodList;
    }
}
