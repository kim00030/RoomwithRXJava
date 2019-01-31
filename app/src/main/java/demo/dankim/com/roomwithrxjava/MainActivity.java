package demo.dankim.com.roomwithrxjava;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import demo.dankim.com.roomwithrxjava.adapter.RecyclerViewAdapter;
import demo.dankim.com.roomwithrxjava.database.Employee;
import demo.dankim.com.roomwithrxjava.databinding.ActivityMainBinding;
import demo.dankim.com.roomwithrxjava.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    // Binding object
    private ActivityMainBinding activityMainBinding;
    //ViewModel object
    private MainActivityViewModel viewModel;
    //RecyclerView Adapter
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize DataBind
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setIMainActivity(this);
        //Initialize RecyclerView
        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.recyclerView.setHasFixedSize(true);
        recyclerViewAdapter = new RecyclerViewAdapter();
        activityMainBinding.recyclerView.setAdapter(recyclerViewAdapter);
        //Initialize ViewModel
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getEmployeeMutableLiveData().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                Log.d(TAG, "onChanged: ");
                recyclerViewAdapter.setEmployeeList(employees);
            }
        });
    }

    @Override
    public void onClickGetData() {
        Log.d(TAG, "onClickGetData: ");
        viewModel.getALlData();
    }

    @Override
    public void onClickAddData() {
        viewModel.addData("Dan", "Kim");
    }
}
