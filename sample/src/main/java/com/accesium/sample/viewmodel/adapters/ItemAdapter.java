package com.accesium.sample.viewmodel.adapters;


import com.accesium.bindinglistview.viewmodel.adapters.ListViewModelAdapter;
import com.accesium.sample.data.Repository;
import com.accesium.sample.model.Item;
import com.accesium.sample.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


/**
 * Created by Victor Pineda on 03/12/2015.
 */
public class ItemAdapter implements ListViewModelAdapter<Item, ItemViewModel> {

    private Repository mRepository;

    public ItemAdapter(Repository repository) {
        mRepository = repository;
    }

    @Override public boolean hasLoadMore() {
        return true;
    }

    @Override public Observable<List<Item>> getData() {
        return mRepository.getData();
    }

    @Override public Observable<List<Item>> getPage(int page) {
        return mRepository.getPage(page);
    }

    @Override public Observable<List<Item>> refreshData() {
        return mRepository.getData();
    }

    @Override public Observable<Boolean> saveData(List<Item> data) {
        throw new UnsupportedOperationException();
    }

    @Override public List<ItemViewModel> buildDetailViewModel(List<Item> items) {
        List<ItemViewModel> list = new ArrayList<>();
        for(Item item : items){
            list.add(new ItemViewModel(item));
        }

        return  list;
    }



}
