package com.butterknife.example.butterknifetest;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    private Activity activity;
    public List<String> stringList;

    public MessageAdapter(Activity activity, List<String> stringList) {
        this.activity = activity;
        this.stringList = stringList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(activity
        ).inflate(R.layout.item_text, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final String str = stringList.get(position);
        holder.textView.setText(str);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"点击了："+str,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView)TextView textView;
        View view1;
        private MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view1 = view;
        }
    }
}
