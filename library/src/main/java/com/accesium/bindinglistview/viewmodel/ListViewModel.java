package com.accesium.sample.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.accesium.bindingList.BR;
import com.accesium.bindinglistview.interfaces.ItemClickListener;
import com.accesium.bindinglistview.utils.Utils;
import com.accesium.bindinglistview.viewmodel.adapters.ListViewModelAdapter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.List;

/**
 * Created by Victor on 09/12/2015.
 */
public class ListViewModel<Model, ViewModel extends Object> extends BaseObservable {

    List<ViewModel> mItems;
    ItemClickListener mClickListener;
    int mCurrentPage;
    boolean mRefresing;
    UltimateRecyclerView.OnLoadMoreListener mLoadMoreListener;
    int mNumCalls;
    boolean mShowPlaceholder;
    ListViewModelAdapter<Model, ViewModel> mViewModel;

    public ListViewModel(ListViewModelAdapter viewModel){
        this.mCurrentPage = 0;
        this.mShowPlaceholder = false;
        this.mViewModel = viewModel;

        initData();

    }

    //****************************************************
    //Listener methods
    //****************************************************

    public void setClickListener(ItemClickListener listener) {
        this.mClickListener = listener;
    }

    public ItemClickListener getClickListener() {
        return this.mClickListener;
    }

    @Bindable public boolean isRefresing() {
        return mRefresing;
    }

    public SwipeRefreshLayout.OnRefreshListener getRefreshListener() {
        return () -> onRefresh();
    }

    @Bindable
    public UltimateRecyclerView.OnLoadMoreListener getLoadMoreListener() {
        return mLoadMoreListener;
    }

    public void setupLoadMoreListener() {

        if(mViewModel.hasLoadMore()) {
            mLoadMoreListener = (itemsCount, maxLastVisiblePosition) -> {
                mCurrentPage++;
                loadPage(mCurrentPage);

            };

            notifyPropertyChanged(BR.loadMoreListener);
        }
    }


    //****************************************************
    //Data load methods
    //****************************************************

    @Bindable
    public List<ViewModel> getItems() {
        return mItems;
    }


    public void clearAndRefresh(){
        this.mItems = null;
        notifyPropertyChanged(BR.items);
        onRefresh();
    }


    public void onRefresh() {

        mRefresing = true;
        mCurrentPage = 0;
        notifyPropertyChanged(BR.refresing);


        mViewModel.refreshData().subscribe(items -> {
            mItems = mViewModel.buildDetailViewModel(items);
            mRefresing = false;
            notifyPropertyChanged(BR.items);
            notifyPropertyChanged(BR.refresing);
            setupLoadMoreListener();
        }, error -> error.printStackTrace());
    }



    public void loadPage(int page) {

        mViewModel.getPage(page).subscribe(requests -> {

            if (Utils.isEmpty(requests)) {
                mLoadMoreListener = null;
                notifyPropertyChanged(BR.loadMoreListener);

            } else {
                mItems.addAll(mViewModel.buildDetailViewModel(requests));
                notifyPropertyChanged(BR.items);

            }

        }, error -> error.printStackTrace());
    }

    public void initData() {


        mViewModel.getData().subscribe(requests -> {
            mNumCalls++;
            if (mNumCalls > 1 && Utils.isEmpty(requests)) {
                //Show placeholder, server has no data
                showPlaceholder();
            } else if(!Utils.isEmpty(requests)){
                mItems = mViewModel.buildDetailViewModel(requests);
                notifyPropertyChanged(BR.items);
                setupLoadMoreListener();
            }

            notifyPropertyChanged(BR.placeholderVisibility);


        }, error -> {
            error.printStackTrace();
            if(Utils.isEmpty(getItems())){
                //Show placeholder, there is no data
                showPlaceholder();
            }
        });
    }

    public int getCurrentPage(){
        return mCurrentPage;
    }


    //****************************************************
    //Placeholder methods
    //****************************************************

    public void showPlaceholder() {
        mShowPlaceholder = true;
        notifyPropertyChanged(BR.placeholderVisibility);
    }

    @Bindable
    public int getPlaceholderVisibility() {
        return mShowPlaceholder ? View.VISIBLE : View.GONE;
    }



}
