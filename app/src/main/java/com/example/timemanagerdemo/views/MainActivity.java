package com.example.timemanagerdemo.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timemanagerdemo.R;
import com.example.timemanagerdemo.ViewModel.MainViewModel;
import com.example.timemanagerdemo.data.TimePeriodBean;
import com.example.timemanagerdemo.data.TimePeriodBeanDatabase;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = "TimeManagerActivity";
    TimePeriodBeanDatabase database;
    Button getStartTime;
    TextView tvStartTime;
    Button getEndTime;
    TextView tvEndTime;
    Button commit;
    Button lookUp;
    EditText etEvent;
    EditText etOutput;
    RadioGroup outputSituationGroup;
    RadioButton rbtnHightOutput;
    RadioButton rbtnMediumOutput;
    RadioButton rbtnLowOutput;
    RadioButton rbtnNoOutput;
    EditText reasonAndReflection;

    MainViewModel mainViewModel;
    Context mContext;

    long startTime;
    long endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindWidget();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getTimePeriodBean().observe(this,
                new Observer<TimePeriodBean>() {
                    @Override
                    public void onChanged(TimePeriodBean timePeriodBean) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String startTime = sdf.format(timePeriodBean.getStartTime());
                        String endTime = sdf.format(timePeriodBean.getEndTime());
                        tvStartTime.setText("开始时间：" + startTime.split(" ")[1]);
                        tvEndTime.setText("结束时间：" + endTime.split(" ")[1]);
                        Log.d(TAG, "onChanged: getStartTime" + sdf.format(timePeriodBean.getStartTime()));
                        Log.d(TAG, "onChanged: getEndTime" + sdf.format(timePeriodBean.getEndTime()));

                    }
                });
    }

    private void bindWidget() {
        getStartTime = findViewById(R.id.btn_start_time);
        tvStartTime = findViewById(R.id.tv_start_time);
        getEndTime = findViewById(R.id.btn_end_time);
        tvEndTime = findViewById(R.id.tv_end_time);
        commit = findViewById(R.id.btn_commit);
        lookUp = findViewById(R.id.btn_look_up);
        etEvent = findViewById(R.id.et_event);
        etEvent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mainViewModel.setEvent(etEvent.getText().toString().trim());
            }
        });
        etOutput = findViewById(R.id.et_output);

        outputSituationGroup = findViewById(R.id.rg_output_situation);
        outputSituationGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbtn_high_output:
                        mainViewModel.setOutputSituation(TimePeriodBean.HIGH_EFFICIENCY);
                        break;
                    case R.id.rbtn_medium_output:
                        mainViewModel.setOutputSituation(TimePeriodBean.MEDIUM_EFFICIENCY);
                        break;
                    case R.id.rbtn_low_output:
                        mainViewModel.setOutputSituation(TimePeriodBean.LOW_EFFICIENCY);
                        break;
                    case R.id.rbtn_no_output:
                        mainViewModel.setOutputSituation(TimePeriodBean.NO_OUTPUT);
                        break;
                }
            }
        });
        rbtnHightOutput = findViewById(R.id.rbtn_high_output);
        rbtnMediumOutput = findViewById(R.id.rbtn_medium_output);
        rbtnLowOutput = findViewById(R.id.rbtn_low_output);
        rbtnNoOutput = findViewById(R.id.rbtn_no_output);
        reasonAndReflection = findViewById(R.id.et_reason_and_reflection);

        getStartTime.setOnClickListener(this);
        tvStartTime.setOnClickListener(this);
        getEndTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        commit.setOnClickListener(this);
        lookUp.setOnClickListener(this);
        etEvent.setOnClickListener(this);
        etOutput.setOnClickListener(this);
        outputSituationGroup.setOnClickListener(this);
        rbtnHightOutput.setOnClickListener(this);
        rbtnMediumOutput.setOnClickListener(this);
        rbtnLowOutput.setOnClickListener(this);
        rbtnNoOutput.setOnClickListener(this);
        reasonAndReflection.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_time:
                startTime = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case R.id.btn_end_time:
                endTime = System.currentTimeMillis();
                break;
            case R.id.btn_commit:
                commitData();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TimePeriodBean timePeriodBean = TimePeriodBeanDatabase.getInstance(getApplicationContext()).getTimePeriodDao().queryTimePeriodByEvent(etEvent.getText().toString().trim());
                        if (timePeriodBean == null) {
                            TimePeriodBeanDatabase.getInstance(getApplicationContext()).getTimePeriodDao().insert(mainViewModel.getTimePeriodBean().getValue());
                        }
                        TimePeriodBean time = TimePeriodBeanDatabase.getInstance(getApplicationContext()).getTimePeriodDao().getTimePeriodList().get(0);

                        Log.d(TAG, "onClick: " + time.toString());
                    }
                }).start();
                break;
            case R.id.btn_look_up:
                Intent intent = new Intent(this, ShowInformationActivity.class);
                startActivity(intent);
                break;

        }
    }

    void commitData() {
        mainViewModel.setReasonAndReflection(reasonAndReflection.getText().toString().trim());
        mainViewModel.setOutput(etOutput.getText().toString().trim());
        mainViewModel.setStartTime(startTime);
        mainViewModel.setEndTime(endTime);

    }
}