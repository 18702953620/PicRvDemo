package com.ch.picrvdemo.repeat;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ch.picrvdemo.R;

import java.util.List;

/**
 * 作者： ch
 * 时间： 2019/2/27 0027-下午 2:02
 * 描述：
 * 来源：
 */

public class RepeatAdapter extends RecyclerView.Adapter<RepeatAdapter.RepeatViewHolder> {

    private List<String> list;
    private Context context;

    public RepeatAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RepeatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_repeat, viewGroup, false);

        Log.e("cheng", "onCreateViewHolder  viewType=" + i);
        return new RepeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepeatViewHolder viewHolder, int i) {
        viewHolder.tv_repeat.setText(list.get(i));
        Log.e("cheng", "onBindViewHolder  position=" + i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class RepeatViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_repeat;

        public RepeatViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_repeat = (TextView) itemView.findViewById(R.id.tv_repeat);
        }
    }
}
