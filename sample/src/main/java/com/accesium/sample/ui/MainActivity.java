package com.accesium.sample.ui;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.accesium.bindinglistview.viewmodel.RecyclerListViewModel;
import com.accesium.sample.R;
import com.accesium.sample.data.Repository;
import com.accesium.sample.databinding.ActivityMainBinding;
import com.accesium.sample.model.Item;
import com.accesium.sample.viewmodel.ItemViewModel;
import com.accesium.sample.viewmodel.adapters.ItemAdapter;

/**
 * Created by Victor Pineda on 03/12/2015.
 */
public class MainActivity extends AppCompatActivity {

    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Repository repository = new Repository();
        ItemAdapter adapter = new ItemAdapter(repository);
        RecyclerListViewModel<Item, ItemViewModel> viewModel = new RecyclerListViewModel(adapter);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setViewmodel(viewModel);

    }


}
