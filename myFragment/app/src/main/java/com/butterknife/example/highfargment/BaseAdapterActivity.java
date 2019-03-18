package com.butterknife.example.highfargment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.butterknife.example.highfargment.adapter.QuickAdapter;
import com.butterknife.example.highfargment.entity.User;
import com.butterknife.example.highfargment.view.CustomLoadMoreView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * BaseRecyclerViewAdapterHelper万能适配器包括以下功能
 * Adapter的最基本使用方法
 * 点击事件
 * 添加列表加载动画
 * 添加头部、尾部
 * 上拉加载
 * 下拉加载（符合聊天软件下拉历史数据需求）
 * 分组布局
 * 多布局
 * 设置空布局
 * 添加拖拽、滑动删除
 * 树形列表
 * 自定义ViewHolder
 */
public class BaseAdapterActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private QuickAdapter quickAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseadapter);
        ButterKnife.bind(this);
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User("yhb", i, "mo", getResources().getDrawable(R.mipmap.ic_launcher_round));
            userList.add(user);
        }
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        quickAdapter = new QuickAdapter(R.layout.item_base, userList);
        recyclerView.setAdapter(quickAdapter);
        //条目点击事件
        setClickListener();
        /**
         * 开启动画（默认为渐显效果）
         * ALPHAIN 渐显
         * SCALEIN 缩放
         * SLIDEIN_BOTTOM 从下到上
         * SLIDEIN_LEFT 从左到右
         * SLIDEIN_RIGHT 从右到左
         */
        quickAdapter.openLoadAnimation();
        //设置重复执行动画（默认只执行一次动画）
        quickAdapter.isFirstOnly(false);
        //添加头布局
        addHread();
        //添加上拉加载功能
        onLoadMore();
        //自定义加载布局
        quickAdapter.setLoadMoreView(new CustomLoadMoreView());//没有原装布局好看
        /**
         *         设置加载更多的启用状态，由于刷新不可跟加载同时操作，
         *         // 所以在刷新时必须调用该方法
         *         quickAdapter.setEnableLoadMore(false);
         */
        /**
         *         下拉加载功能
         *         事实现方式与上拉加载相同
         *         开启下拉加载开关mAdapter.setUpFetchEnable(true);
         *         mAdapter.setUpFetching(true);设置开始加载，在网络请求开始时设置
         *         quickAdapter.addData(0, genData());添加数据至适配器，特定位置添加
         *          mAdapter.setUpFetching(false);设置是否开启加载，防止多次加载网络数据
         *          mAdapter.setStartUpFetchPosition(2);指定到某行开始预加载
         * */


    }

    //设置条目点击监听
    private void setClickListener() {
        //条目点击事件
        quickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //获取某项的相应控件
                TextView tv_title = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.base_tv);
                Toast.makeText(BaseAdapterActivity.this, tv_title.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //条目长按事件
        quickAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

                Toast.makeText(BaseAdapterActivity.this, "长按了第" + position + "条条目", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //条目子控件点击事件
        quickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override//改时间需要在适配器绑定指定控件监听
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.base_img) {
                    Toast.makeText(BaseAdapterActivity.this, "点击了第" + (position + 1) + "条条目的图片", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //头布局的内部类
    public class HreadView {
        @BindView(R.id.base_img)
        ImageView baseImg;
        @BindView(R.id.base_tv)
        TextView baseTv;
        public View view;
        public HreadView() {
            this.view = LayoutInflater.from(BaseAdapterActivity.this).inflate(R.layout.item_base, null, false);
            ButterKnife.bind(this,view);
        }

        public View getView() {
            return view;
        }
    }

    //添加头布局
    private void addHread(){
        HreadView hreadView = new HreadView();
        hreadView.baseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseAdapterActivity.this,BaseAdapter2Activity.class));
            }
        });
        hreadView.baseTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(BaseAdapterActivity.this,BaseAdapter3Activity.class));
                return false;
            }
        });
        quickAdapter.addHeaderView(hreadView.getView());//(可以添加多个头部或尾部)
        /**
         * quickAdapter.removeHeaderView(hreadView.getView());删除指定view
         * quickAdapter.removeAllHeaderView();删除所有
         * quickAdapter.setHeaderAndEmpty();默认出现了头部就不会显示Empty，和尾部，配置该方法也支持同时显示：
         * quickAdapter.setHeaderViewAsFlow();默认头部尾部都是占满一行，如果需要不占满可以配置：
         * 尾部操作和头部同理
         */
    }

    //上拉加载
    private boolean isLoading = false;//是否正在加载
    private void onLoadMore(){
        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
        quickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if(!isLoading){
                    isLoading = true;
                    recyclerView.postDelayed(upLoading,2000);
                }
            }
        });
    }
    private Runnable upLoading = new Runnable() {//数据加载事件
        @Override
        public void run() {
            //数据全部加载完毕//数据全部加载完毕后会隐藏加载更多
            //quickAdapter.loadMoreEnd();
            List<User> userList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                User user = new User("yhb", i, "mo", getResources().getDrawable(R.mipmap.ic_launcher_round));
                userList.add(user);
            }
            quickAdapter.addData(userList);//添加数据至适配器（在尾部添加）
            int mCurrentCounter = quickAdapter.getData().size();//获取数据条数
            //加载数据完毕（注意不是加载结束，而是本次数据加载结束并且还有下页数据）
            quickAdapter.loadMoreComplete();
            //获取更多数据失败
            //quickAdapter.loadMoreFail();
            isLoading = false;
        }
    };


}
