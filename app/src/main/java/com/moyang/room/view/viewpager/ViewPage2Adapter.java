package com.moyang.room.view.viewpager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moyang.room.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: view pager2 适配器
 * @author: moyang
 * @date: 2022/7/23 13:25
 */
public class ViewPage2Adapter extends RecyclerView.Adapter<ViewPage2Adapter.MyViewHolder> {


    List<String> textList = new ArrayList<>(3);

    public ViewPage2Adapter() {
        textList.add("1111111");
        textList.add("2222222");
        textList.add("3333333");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item_fragment, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(textList.get(position));
    }



    @Override
    public int getItemCount() {
        return textList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_fragment);

        }
    }
}
