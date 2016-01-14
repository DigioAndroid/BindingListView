package com.accesium.bindinglistview.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.accesium.bindingList.R;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.Collection;

/**
 * Created by Victor on 14/01/2016.
 */
public class Utils {

    public static boolean isEmpty(final Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static void hideView(View view) {

        view.setTag(R.id.origin_height, view.getHeight());
        ValueAnimator anim = ValueAnimator.ofInt(view.getHeight(), 0);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();

            if (val < 0) {
                return; //bug android
            }

            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = val;

            view.setLayoutParams(layoutParams);

        });

        anim.setInterpolator(new DecelerateInterpolator(1));
        anim.setDuration(400);
        anim.start();
    }

    public static void showView(View view) {

        int height = view.getHeight();
        if (height == 0 && view.getTag(R.id.origin_height) != null) {
            view.getLayoutParams().height = (int) view.getTag(R.id.origin_height);
        }
    }

}
