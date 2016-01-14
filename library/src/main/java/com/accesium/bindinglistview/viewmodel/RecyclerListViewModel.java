package com.accesium.bindinglistview.viewmodel;

import android.databinding.BindingAdapter;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.accesium.bindingList.R;
import com.accesium.bindinglistview.interfaces.ItemClickListener;
import com.accesium.bindinglistview.utils.ItemDecoration;
import com.accesium.bindinglistview.utils.Utils;
import com.accesium.bindinglistview.viewmodel.adapters.GenericRecyclerAdapter;
import com.accesium.bindinglistview.viewmodel.adapters.ListViewModelAdapter;
import com.accesium.sample.viewmodel.ListViewModel;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

/**
 * Created by icastell on 17/12/15.
 */
public class RecyclerListViewModel<Model, ViewModel> extends ListViewModel<Model, ViewModel> {

    public RecyclerListViewModel(ListViewModelAdapter viewModel) {
        super(viewModel);
    }

    @BindingAdapter({"itemLayout", "items", "onClick"})
    public static void configureRecyclerView(UltimateRecyclerView recyclerView, @LayoutRes int layoutId, List items, ItemClickListener listener) {

        try {

            if (recyclerView.getLayoutManager() == null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.addItemDecoration(new ItemDecoration(recyclerView.getResources().getDimensionPixelSize(R.dimen.content_padding)));
            }

            GenericRecyclerAdapter adapter = (GenericRecyclerAdapter) recyclerView.getAdapter();

            if (adapter == null) {
                adapter = new GenericRecyclerAdapter(layoutId);
                recyclerView.setAdapter(adapter);
                adapter.setListener(listener);
                recyclerView.setEmptyView(R.layout.placeholder_loading);
            }


            adapter.setItems(items);
            adapter.notifyDataSetChanged();


        } catch (Exception ex) {
            ex.printStackTrace();


        }
    }

    @BindingAdapter("onLoadMore")
    public static void configureRecyclerView(UltimateRecyclerView recyclerView, UltimateRecyclerView.OnLoadMoreListener loadMoreListener) {

        if (recyclerView.getAdapter() == null) {
            return;
        }

        if (loadMoreListener != null) {

            if (!recyclerView.isLoadMoreEnabled()) {
                recyclerView.enableLoadmore();
                ((UltimateViewAdapter) recyclerView.getAdapter()).setCustomLoadMoreView(LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.adapter_load_more, null));
                recyclerView.setOnLoadMoreListener(loadMoreListener);
            }

            ((UltimateViewAdapter) recyclerView.getAdapter()).getCustomLoadMoreView().findViewById(R.id.loading_more).setVisibility(View.VISIBLE);

            Utils.showView(((UltimateViewAdapter) recyclerView.getAdapter()).getCustomLoadMoreView());

        } else {
            //((UltimateViewAdapter) recyclerView.getAdapter()).getCustomLoadMoreView().findViewById(R.id.loading_more).setVisibility(View.GONE);
            Utils.hideView(((UltimateViewAdapter) recyclerView.getAdapter()).getCustomLoadMoreView());
        }


    }

    @BindingAdapter("onRefresh")
    public static void setRefreshListener(UltimateRecyclerView recyclerView, SwipeRefreshLayout.OnRefreshListener swipeListener) {
        recyclerView.setDefaultOnRefreshListener(swipeListener);
    }
}
