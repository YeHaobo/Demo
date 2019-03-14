package com.butterknife.example.butterknifetest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TestFargment extends Fragment {

    @BindView(R.id.title_text) TextView titleText;
    @BindView(R.id.radioButton1) RadioButton radioButton1;
    @BindView(R.id.radioButton2) RadioButton radioButton2;
    @BindView(R.id.radioButton3) RadioButton radioButton3;
    @BindView(R.id.checkbox) CheckBox checkbox;
    @BindView(R.id.buttom) Button buttom;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    private Unbinder unbinder;
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_one, container, false);
        //返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unbinder = ButterKnife.bind(this, view);
        //绑定监听事件
        bindListener();
        //RecyclerView数据控件绑定
        bindRecyclerView();
        return view;
    }

    /**
     * onDestroyView中进行解绑操作
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 绑定监听事件
     */
    private void bindListener() {
        radioButton1.setOnCheckedChangeListener(onCheckedChangeListener);
        radioButton2.setOnCheckedChangeListener(onCheckedChangeListener);
        radioButton3.setOnCheckedChangeListener(onCheckedChangeListener);
        checkbox.setOnCheckedChangeListener(onCheckedChangeListener);
        buttom.setOnClickListener(clickListener);
    }

    /**
     * 以下为点击事件
     */
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Toast.makeText(getContext(), "点击了RadioButtom", Toast.LENGTH_SHORT).show();
        }
    };
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), "点击了Buttom", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     *绑定列表
     */
    private void bindRecyclerView(){
        List<String> stringList = new ArrayList<>();
        for(int i = 0;i <= 10;i ++ ){
            stringList.add("条目："+ i);
        }
        MessageAdapter messageAdapter = new MessageAdapter(getActivity(),stringList);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager( new GridLayoutManager(getActivity(),1));
        recyclerView.setAdapter(messageAdapter);
    }
}
