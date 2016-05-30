package com.itheima.wechatdemo.view;

/**
 * 作者： YouLiang.Ji
 * 描述： GridViewInScroll
 * 创建时间：2015/3/31 14:14.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ListView;

public class ListViewInScroll extends ListView {

    /**
     * @param context
     */
    public ListViewInScroll(Context context) {
        this(context, null);
    }

    public ListViewInScroll(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ListViewInScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}