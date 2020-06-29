package com.example.myapplication18.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication18.R;

import java.util.List;

public class RecyclerViewTest002Activity_RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //条目类型
    public static final int TYPE_0 = 0;
    public static final int TYPE_1 = 1;

    //数据源
    private List<RecyclerViewTest002Activity_RvAdapterData> mList;

    //点击监听
    private AdapterView.OnItemClickListener mOnItemClickListener;

    public RecyclerViewTest002Activity_RvAdapter(List<RecyclerViewTest002Activity_RvAdapterData> list) {
        mList = list;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View item;
        RecyclerView.ViewHolder holder = null;
        if (viewType == TYPE_0) {
            item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycler_view_test002_type1_rcitem, viewGroup, false);
            holder = new Type0ViewHolder(item);
        }
        if (viewType == TYPE_1) {
            item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycler_view_test002_type2_rcitem, viewGroup, false);
            holder = new Type1ViewHolder(item);
        }
        return holder;
    }

    /**
     * 根据数据源的某一项，返回相应的布局类别
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        RecyclerViewTest002Activity_RvAdapterData data = mList.get(i);
        if (type == TYPE_0) {
            Type0ViewHolder holder = (Type0ViewHolder) viewHolder;
            holder.iv.setImageResource(R.drawable.ic_launcher_background);
            holder.tv.setText(data.getText());
            if (mOnItemClickListener != null) {
                holder.ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(null,v,i,holder.ll.getId());
                    }
                });
            }
        }
        if (type == TYPE_1) {
            Type1ViewHolder holder = (Type1ViewHolder) viewHolder;
            holder.iv.setImageResource(R.drawable.ic_launcher_background);
            holder.tv.setText(data.getText());
            if (mOnItemClickListener != null) {
                holder.ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(null,v,i,holder.ll.getId());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class Type0ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        LinearLayout ll;

        public Type0ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll=itemView.findViewById(R.id.ll);;
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    class Type1ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll;
        ImageView iv;
        TextView tv;

        public Type1ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll=itemView.findViewById(R.id.ll);;
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
