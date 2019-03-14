package data.mysql.com.greendaotest;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import data.mysql.com.greendaotest.entity.User;

/**
 * 数据库信息列表适配器
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>
{
    private Activity activity;
    public List<User> userList;
    public MessageAdapter(Activity activity,List<User> userList){
        this.activity=activity;
        this.userList=userList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(activity
        ).inflate(R.layout.item_message, parent,
                false));
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position)
    {
        User user = userList.get(position);
        holder.tv.setText(user.user2string());
    }

    @Override
    public int getItemCount()
    {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        View view1;
        TextView tv;
        private MyViewHolder(View view)
        {
            super(view);
            tv = view.findViewById(R.id.item_msg);
            view1=view;
        }
    }
}
