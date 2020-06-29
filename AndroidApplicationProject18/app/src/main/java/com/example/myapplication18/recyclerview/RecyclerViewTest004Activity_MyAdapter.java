package com.example.myapplication18.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication18.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTest004Activity_MyAdapter extends RecyclerView.Adapter<RecyclerViewTest004Activity_MyAdapter.MyViewHolder> {
    private Context context;
    /**
     * 消息列表数据
     */
    private List<RecyclerViewTest004Activity_MyAdapterData> lists;

    /**
     * 标记展开的item
     */
    private int opened = -1;

    public RecyclerViewTest004Activity_MyAdapter(Context context) {
        this.context = context;
        lists = new ArrayList<>();
    }

    /**
     * 设置列表数据
     * @param lists
     */
    public void setLists(List<RecyclerViewTest004Activity_MyAdapterData> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recycler_view_test004_rcitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.bindView(position,lists.get(position));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView msgTime;
        private TextView msgContent;
        private TextView msgContentMore;
        private RelativeLayout msgRl;
        private LinearLayout msgLl;

        public MyViewHolder(View itemView) {
            super(itemView);
            msgTime = (TextView) itemView.findViewById(R.id.msg_time);
            msgContent = (TextView) itemView.findViewById(R.id.msg_content);
            msgContentMore = (TextView) itemView.findViewById(R.id.msg_contentMore);
            msgRl = (RelativeLayout) itemView.findViewById(R.id.msg_rl);
            msgLl = (LinearLayout) itemView.findViewById(R.id.msg_ll);
            msgRl.setOnClickListener(this);
        }

        /**
         * 此方法实现列表数据的绑定和item的展开/关闭
         */
        void bindView(int pos, RecyclerViewTest004Activity_MyAdapterData bean) {
            msgTime.setText(bean.getName());
            msgContent.setText(bean.sex);
            msgContentMore.setText(bean.desc);

            if (pos == opened){
                msgLl.setVisibility(View.VISIBLE);
            } else{
                msgLl.setVisibility(View.GONE);
            }

        }
        /**
         * item的点击事件
         * @param v
         */
        @Override
        public void onClick(View v) {
            if (opened == getAdapterPosition()) {
                //当点击的item已经被展开了, 就关闭.
                opened = -1;
                notifyItemChanged(getAdapterPosition());
            } else {
                int oldOpened = opened;
                opened = getAdapterPosition();
                notifyItemChanged(oldOpened);
                notifyItemChanged(opened);
            }
        }
    }
}