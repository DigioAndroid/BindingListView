package com.accesium.sample.data;

import com.accesium.sample.model.Item;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Victor on 14/01/2016.
 */
public class Repository {

    public List<Item> mItemsPage1;
    public List<Item> mItemsPage2;

    public Repository() {

        mItemsPage1 = new ArrayList<>();
        mItemsPage1.add(new Item("Item 1"));
        mItemsPage1.add(new Item("Item 2"));
        mItemsPage1.add(new Item("Item 3"));
        mItemsPage1.add(new Item("Item 4"));
        mItemsPage1.add(new Item("Item 5"));
        mItemsPage1.add(new Item("Item 6"));
        mItemsPage1.add(new Item("Item 7"));
        mItemsPage1.add(new Item("Item 8"));
        mItemsPage1.add(new Item("Item 9"));
        mItemsPage1.add(new Item("Item 10"));

        mItemsPage2 = new ArrayList<>();
        mItemsPage2.add(new Item("Item 11"));
        mItemsPage2.add(new Item("Item 12"));
        mItemsPage2.add(new Item("Item 13"));
        mItemsPage2.add(new Item("Item 14"));
        mItemsPage2.add(new Item("Item 15"));
        mItemsPage2.add(new Item("Item 16"));
        mItemsPage2.add(new Item("Item 17"));
        mItemsPage2.add(new Item("Item 18"));
        mItemsPage2.add(new Item("Item 19"));
        mItemsPage2.add(new Item("Item 20"));

    }

    public Observable<List<Item>> getData(){
        //Simulate first response from cache and second from network
        return Observable.merge(Observable.just(mItemsPage1), Observable.just(mItemsPage1));
    }

    public Observable<List<Item>> getPage(int page) {

        if(page == 0){
            return Observable.just(mItemsPage1);
        }
        else if(page == 1){
            return Observable.just(mItemsPage2);
        }

        return Observable.just(null);
    }
}
