package com.accesium.bindinglistview.viewmodel.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.accesium.bindinglistview.interfaces.ItemClickListener;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Victor on 30/11/2015.
 */
public class GenericRecyclerAdapter<T> extends UltimateViewAdapter<GenericRecyclerAdapter.BindingHolder> {

    private List<T> mItems;
    private int mLayout;
    private ItemClickListener<T> mClickListener;

    public GenericRecyclerAdapter(int layout){
        this.mLayout = layout;
    }

    public void setListener(ItemClickListener listener) {
        this.mClickListener = listener;
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View rowView) {
            super(rowView);
            try {
                binding = DataBindingUtil.bind(rowView);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }


    @Override public BindingHolder getViewHolder(View view) {
        return new BindingHolder(view);
    }

    @Override public BindingHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mLayout, parent, false);
        BindingHolder holder = new BindingHolder(v);
        return holder;
    }

    @Override public int getAdapterItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override public long generateHeaderId(int position) {
        return -1;
    }

    @Override public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {

        try {
            if (position < getAdapterItemCount()) {
                final T item = mItems.get(position);

                Class mClass = Class.forName("com.accesium.bindingList.BR");
                Object brInstance = mClass.newInstance();
                Field field = mClass.getDeclaredField("item");
                field.setAccessible(true);
                Integer value = (Integer) field.get(brInstance);

                holder.getBinding().setVariable(value, item);
                holder.getBinding().executePendingBindings();

                if (mClickListener != null) {
                    holder.getBinding().getRoot().setOnClickListener(view -> mClickListener.onClick(view, item));
                }
            }

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }



    public void setItems(List<T> items){
        mItems = items;
    }
}