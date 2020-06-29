package com.example.myapplication18.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewTest003Activity_DemoItemDecoration extends RecyclerView.ItemDecoration {
    private int mDividerHeight = 40;
    private Paint mPaint;

    public RecyclerViewTest003Activity_DemoItemDecoration() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.YELLOW);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = 40;
        outRect.right = 40;
        //第一个条目，我们不给它设置上边距
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = mDividerHeight;
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount= parent.getChildCount();
        //遍历RecyclerView的所有子view
        for (int i = 0; i < childCount; i++) {
            //看看子view是否是列表中的条目，如果是的话，我们获取它的位置
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            //因为第一个条目我们没有设置上边距，所以我们在画图的时候也要排除掉它
            if (position != 0) {
                float dividerTop = view.getTop() - mDividerHeight;
                float dividerLeft = parent.getLeft();
                float dividerBottom = view.getTop();
                float dividerRight = parent.getWidth();
                c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, mPaint);
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
