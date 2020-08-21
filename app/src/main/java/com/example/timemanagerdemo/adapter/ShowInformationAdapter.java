package com.example.timemanagerdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timemanagerdemo.R;
import com.example.timemanagerdemo.data.TimePeriodBean;

import java.util.List;

public class ShowInformationAdapter extends RecyclerView.Adapter<ShowInformationAdapter.MyViewHolder> {
    private Context mContext;
    private List<TimePeriodBean> list;
    private LayoutInflater inflater;

    public ShowInformationAdapter(Context context, List<TimePeriodBean> datas) {
        this.list = datas;
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_information, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_information_item);
        }
    }
}
