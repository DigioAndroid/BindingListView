package com.accesium.bindinglistview.viewmodel.adapters;

import java.util.List;

import rx.Observable;

/**
 * Created by Victor on 22/12/2015.
 */
public interface ListViewModelAdapter<Model, CellViewModel> {

    Observable<List<Model>> getData();

    Observable<List<Model>> getPage(int page);

    Observable<List<Model>> refreshData();

    List<CellViewModel> buildDetailViewModel(List<Model> items);

    Observable<Boolean> saveData(List<Model> data);

    boolean hasLoadMore();

}
