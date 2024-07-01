package com.sajiman.jasonapp.RvAdapters;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by HP on 2/20/2018.
 */

public class RvItemDecoration extends RecyclerView.ItemDecoration {

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = 1;
        } else {
            outRect.top = 2;
        }

        if (parent.getChildLayoutPosition(view) == (parent.getAdapter().getItemCount()) - 1) {
            outRect.bottom = 1;
        } else {
            outRect.bottom = 2;
        }
        outRect.right = 2;
        outRect.left = 2;

    }
}
