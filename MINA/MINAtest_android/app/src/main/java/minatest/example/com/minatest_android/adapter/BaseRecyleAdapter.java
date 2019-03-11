package minatest.example.com.minatest_android.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseRecyleAdapter extends RecyclerView.Adapter<BaseRecyleAdapter.ViewHolder> {
    public List<?> data;
    private Map<Integer, Integer> hashMap;
    private OnRecycleitemOnClick onRecycleitemOnClick;
    public Activity activity;
    public BaseRecyleAdapter(List<?> data, Map<Integer, Integer> hashMap,Activity activity) {
        this.data = data;
        this.hashMap = hashMap;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        ViewDataBinding inflate = DataBindingUtil.inflate(from, viewType, parent, false);
        return new ViewHolder(inflate);
    }

    /**
     * 默认取值 MAP 第一个Key
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        Set<Integer> integers = hashMap.keySet();
        return integers.iterator().next();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Integer varid = hashMap.get(getItemViewType(position));
        holder.dataBinding.setVariable(varid, data.get(position));
        if (onRecycleitemOnClick != null)
            holder.dataBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecycleitemOnClick.onItemClick(view, position);
                }
            });
    }

    public OnRecycleitemOnClick getOnRecycleitemOnClick() {
        return onRecycleitemOnClick;
    }

    public void setOnRecycleitemOnClick(OnRecycleitemOnClick onRecycleitemOnClick) {
        this.onRecycleitemOnClick = onRecycleitemOnClick;
    }

    public interface OnRecycleitemOnClick {
        void onItemClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding dataBinding;

        public ViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            this.dataBinding = itemView;
        }
    }
}