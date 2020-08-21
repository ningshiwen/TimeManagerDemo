package com.example.timemanagerdemo.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timemanagerdemo.R;
import com.example.timemanagerdemo.ViewModel.ShowInformationViewModel;
import com.example.timemanagerdemo.adapter.ShowInformationAdapter;
import com.example.timemanagerdemo.data.TimePeriodBean;

import java.util.List;

public class ShowInformationActivity extends AppCompatActivity {
    ShowInformationViewModel mViewModel;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information);
        recyclerView = findViewById(R.id.rv_show_information);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new ShowInformationAdapter(this, mViewModel.getTimePeriodList().getValue()));
        mViewModel = new ViewModelProvider(this).get(ShowInformationViewModel.class);
        mViewModel.setContext(getApplicationContext());
        mViewModel.getTimePeriodList().observe(this, new Observer<List<TimePeriodBean>>() {
            @Override
            public void onChanged(List<TimePeriodBean> timePeriodBeans) {
                recyclerView.notifyAll();
            }
        });
    }


}
