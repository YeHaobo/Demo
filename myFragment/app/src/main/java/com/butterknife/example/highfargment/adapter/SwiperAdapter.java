package com.butterknife.example.highfargment.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.butterknife.example.highfargment.R;
import com.daimajia.swipe.SwipeLayout;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SwiperAdapter extends RecyclerView.Adapter<SwiperAdapter.MyViewHolder> {

    private Activity activity;
    public List<String> stringList;
    public SwipeLayout swipeLayout = null;//用来判断现在打开删除的是哪个

    public SwiperAdapter(Activity activity, List<String> stringList) {
        this.activity = activity;
        this.stringList = stringList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(activity
        ).inflate(R.layout.item_swipelayout, parent,
                false));
        return holder;
    }

    private DelectItem delectItem;
    public interface DelectItem {
     void delete(int position);
    }
    public void setDelectItem(DelectItem delectItem){
        this.delectItem = delectItem;
    }

    @Override
    public void onBindViewHolder(@NonNull final  MyViewHolder holder,int position) {
        holder.itemSwiperTv.setText(stringList.get(position));
        holder.delectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delectItem.delete(holder.getAdapterPosition());
                //holder.getAdapterPosition()//获取当前的position
            }
        });
        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {
                if(swipeLayout != layout && swipeLayout != null){
                    swipeLayout.close();
                }
                swipeLayout = layout;
            }

            @Override
            public void onOpen(SwipeLayout layout) {
            }

            @Override
            public void onStartClose(SwipeLayout layout) {
            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.delect_img)
        ImageView delectImg;
        @BindView(R.id.item_swiper_tv)
        TextView itemSwiperTv;
        @BindView(R.id.item_swiper)
        SwipeLayout swipeLayout;
        public View view;
        private MyViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }

}