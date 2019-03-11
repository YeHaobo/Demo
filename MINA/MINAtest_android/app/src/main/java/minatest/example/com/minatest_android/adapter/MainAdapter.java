package minatest.example.com.minatest_android.adapter;

import android.view.View;

import java.util.List;
import java.util.Map;
import minatest.example.com.minatest_android.R;
import minatest.example.com.minatest_android.tool.MyToast;

public class MainAdapter extends BaseRecyleAdapter {
    public MainAdapter(List<?> data, Map<Integer, Integer> hashMap) {
        super(data, hashMap);
    }

    /**
     * 如果有值不是从bean类里面获取的，需要自己去设置的话就重写此方法
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.showImgToast(position+1+"",R.mipmap.ic_launcher_round);
            }
        });
        /*int i = position + 1;
        String text = i >= 10 ? String.valueOf(i) : String.valueOf("0" + i);
        holder.dataBinding.setVariable(BR.itemNum, text);*/
    }
    /**
     * 编写规则,返回相应布局,比如这里是第四个item加载其他布局
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
       /* if (position == 3)
            return R.layout.item_main_diverse;
        else*/
        return R.layout.item_main_chat;
    }
}
