package com.butterknife.example.highfargment.view;

import com.butterknife.example.highfargment.R;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

/**
 * 自定义加载布局
 */
public class CustomLoadMoreView extends LoadMoreView {

    /**
     * 加载的布局
     */
    @Override public int getLayoutId() {
        return R.layout.view_load_more;
    }

    /**
     * 如果返回true，数据全部加载完毕后会隐藏加载更多
     * 如果返回false，数据全部加载完毕后会显示getLoadEndViewId()布局
     */
    @Override public boolean isLoadEndGone() {
        return true;
    }


    /**
     *     加载中的布局
     */
    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    /**
     *     加载失败的布局
     */
    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    /**
     * isLoadEndGone()为true，可以返回0
     * isLoadEndGone()为false，不能返回0
     */
    @Override protected int getLoadEndViewId() {
        return 0;
    }
}
