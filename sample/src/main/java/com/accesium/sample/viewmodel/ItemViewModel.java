package com.accesium.sample.viewmodel;

import com.accesium.sample.model.Item;

/**
 * Created by Victor on 14/01/2016.
 */
public class ItemViewModel {

    private Item mItem;

    public ItemViewModel(Item item){
        this.mItem = item;
    }

    public String getText(){
        return mItem.getText();
    }

}
