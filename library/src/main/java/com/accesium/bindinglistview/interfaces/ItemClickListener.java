package com.accesium.bindinglistview.interfaces;

import android.view.View;

/**
 * Created by Victor on 01/12/2015.
 */
public interface ItemClickListener<T> {

    void onClick(View v, T request);
}
