package com.example.myapplication18.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication18.R;

public class RecyclerViewTest005Activity_TreeAdapterViewHolder extends RecyclerView.ViewHolder {

    private ImageView bt_arrow_down_gray;
    private TextView tv_company_name;
    private LinearLayout ll_item_monitor_company;

    public ImageView getBt_arrow_down_gray() {
        return bt_arrow_down_gray;
    }

    public TextView getTv_company_name() {
        return tv_company_name;
    }

    public LinearLayout getLl_item_monitor_company() {
        return ll_item_monitor_company;
    }

    public RecyclerViewTest005Activity_TreeAdapterViewHolder(View itemView) {
        super(itemView);
        bt_arrow_down_gray = itemView.findViewById(R.id.iv_spread_level_0);
        tv_company_name =  itemView.findViewById(R.id.tv_company_name);
        ll_item_monitor_company = (LinearLayout) itemView.findViewById(R.id.ll_item_monitor_company);
    }

    /**
     * 此方法实现列表数据的绑定和item的展开/关闭
     */
    void bindView(int pos, RecyclerViewTest004Activity_MyAdapterData bean) {
        /*msgTime.setText(bean.getName());
        msgContent.setText(bean.sex);
        msgContentMore.setText(bean.desc);

        if (pos == opened){
            msgLl.setVisibility(View.VISIBLE);
        } else{
            msgLl.setVisibility(View.GONE);
        }*/
    }

}