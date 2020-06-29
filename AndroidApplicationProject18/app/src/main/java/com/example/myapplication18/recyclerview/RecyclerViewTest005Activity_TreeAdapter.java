package com.example.myapplication18.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication18.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTest005Activity_TreeAdapter extends RecyclerView.Adapter<RecyclerViewTest005Activity_TreeAdapterViewHolder> {
    private Context ctx;
    /**
     * 所有客户的列表
     */
    private List<RecyclerViewTest005Activity_TreeData> companyList;
    /**
     * 当前展示的客户列表（未处于收起状态）
     */
    private List<RecyclerViewTest005Activity_TreeData> customerShownList;
    /**
     * 当前选中的客户
     */
    private RecyclerViewTest005Activity_TreeData currentChosenCustomer;

    /**
     * 处于收缩状态的客户id
     * （由用户点击后触发）
     */
    private List<String> shrinkCustomerIdList;


    public RecyclerViewTest005Activity_TreeAdapter(Context ctx, List<RecyclerViewTest005Activity_TreeData> companyList)
    {
        this.ctx = ctx;
        this.companyList = companyList;
        customerShownList = new ArrayList<>(companyList.size());
        shrinkCustomerIdList = new ArrayList<>();
        //默认选中第一项
        if(companyList != null && companyList.size() > 0)
        {
            currentChosenCustomer = companyList.get(0);
        }
        initCustomerShownList();
    }

    @NonNull
    @Override
    public RecyclerViewTest005Activity_TreeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecyclerViewTest005Activity_TreeAdapterViewHolder(LayoutInflater.from(ctx).inflate(R.layout.activity_recycler_view_test005_rcitem,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewTest005Activity_TreeAdapterViewHolder baseViewHolder, int position)
    {
        final RecyclerViewTest005Activity_TreeData company = customerShownList.get(position);

        LinearLayout llItemMonitorCompany = baseViewHolder.getLl_item_monitor_company();
        TextView tvCompanyName = baseViewHolder.getTv_company_name();
        tvCompanyName.setText(company.getCompanyName());
        ImageView ivSpreadLevel0 = baseViewHolder.getBt_arrow_down_gray();
        //选中项修改底色
        if(currentChosenCustomer != null && company.equals(currentChosenCustomer))
        {
            tvCompanyName.setTextColor(Color.rgb(0x22,0x88,0xff));
        } else
        {
            tvCompanyName.setTextColor(Color.BLACK);
        }

        //公司层级：0,1,2,3
        int level = company.getCompanyLevel();
        boolean hasChildCompany = company.isHasChildCompany();

        if(hasChildCompany)
        {
            //有子客户，显示展开/收缩 的图标
            ivSpreadLevel0.setVisibility(View.VISIBLE);
            if(shrinkCustomerIdList.contains(company.getCompanyID()))
            {
                //处于收缩状态，显示收缩对应的图标
                ivSpreadLevel0.setImageResource(R.drawable.bt_arrow_down_gray);
            } else
            {
                //处于展开状态，显示展开对应的图标
                ivSpreadLevel0.setImageResource(R.drawable.bt_arrow_up_gray);
            }
        } else
        {
            //没有子客户，不显示展开/收缩 的图标
            ivSpreadLevel0.setVisibility(View.INVISIBLE);
        }

        //根据客户的层级数值，设置margin。层级越高左边margin数值越大，
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivSpreadLevel0.getLayoutParams();
        params.setMargins(dip2px(ctx,12*(level + 1) - 8),0,0,0);
        ivSpreadLevel0.setLayoutParams(params);

        //展开/收缩图标的点击事件，点击后收缩变为展开，展开变为收缩
        ivSpreadLevel0.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(shrinkCustomerIdList.contains(company.getCompanyID()))
                {
                    shrinkCustomerIdList.remove(company.getCompanyID());
                } else
                {
                    shrinkCustomerIdList.add(company.getCompanyID());
                }
                initCustomerShownList();
                notifyDataSetChanged();
            }
        });


        //项点击事件，点击后该项即为选中项（需变色），并且触发回调
        llItemMonitorCompany.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //设置当前点击项为选中项
                currentChosenCustomer = company;

                //回调
                if(listener != null)
                {
                    listener.onItemClick(company);
                }
                //更新界面
                notifyDataSetChanged();
            }
        });

    }

    /**
     * 初始化待显示的客户列表
     */
    public void initCustomerShownList()
    {
        //处于收缩状态的项的层级(0,1,2,3)，暂默认层级不超过1000层。
        //相信也不会碰到超过1000层的情况
        int shrinkLevel = 1000;
        customerShownList.clear();
        int i = 0;
        for(;i<companyList.size();i++)
        {
            RecyclerViewTest005Activity_TreeData customer = companyList.get(i);

            if(shrinkCustomerIdList.contains(customer.getCompanyID()))
            {
                //处于收缩状态的项，该项显示，
                customerShownList.add(customer);
                shrinkLevel = customer.getCompanyLevel();
                //从下一个开始循环
                i++;
                for(;i<companyList.size();i++)
                {
                    if(companyList.get(i).getCompanyLevel() > shrinkLevel)
                    {
                        //下级菜单，全部隐藏
                        continue;
                    } else
                    {
                        //同级或上级,跳出循环，并将下标前移一位，让该对象进入上层的for循环判断，并初始化隐藏层级
                        i--;
                        shrinkLevel = 1000;
                        break;
                    }
                }
            } else
            {
                customerShownList.add(customer);
            }
        }
    }



    @Override
    public int getItemCount()
    {
        return customerShownList.size();
    }


    /**
     * 项点击的回调监听
     */
    private OnItemClickListener listener;
    public void setOnItemClickListener (OnItemClickListener listener)
    {
        this.listener = listener;
    }
    public static interface OnItemClickListener
    {
        public void onItemClick(RecyclerViewTest005Activity_TreeData company);
    }



    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}