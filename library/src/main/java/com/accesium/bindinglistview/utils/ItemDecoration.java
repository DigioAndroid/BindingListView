package com.accesium.bindinglistview.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Victor on 01/12/2015.
 */
public class ItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public ItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        // Add top margin only for the first item to avoid double space between items
        if(parent.getChildPosition(view) == 0){
            outRect.top = space;
        }

        outRect.bottom = space;
        outRect.right = space / 2;
        outRect.left = space / 2;

    }
}