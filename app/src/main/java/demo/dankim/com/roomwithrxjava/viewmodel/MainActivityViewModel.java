package demo.dankim.com.roomwithrxjava.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.List;

import demo.dankim.com.roomwithrxjava.database.Employee;
import demo.dankim.com.roomwithrxjava.repository.DataCallBack;
import demo.dankim.com.roomwithrxjava.repository.Repository;

/**
 * Created by Dan Kim on 2019-01-29
 */
public class MainActivityViewModel extends AndroidViewModel implements DataCallBack {

    private MutableLiveData<List<Employee>> employeeMutableLiveData = new MutableLiveData<>();
    private Repository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        repository.getAllData(this);
    }

    public void addData(String firstName, String lastName){
        repository.addData(this, firstName,lastName);
    }

    public void getALlData(){

        repository.getAllData(this);
    }

    @Override
    public void dataAdded() {

        Toast.makeText(getApplication().getApplicationContext(), "DataAdded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorAdded() {

        Toast.makeText(getApplication().getApplicationContext(), "Failed to add data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAllDataWith(List<Employee> employeeList) {
        employeeMutableLiveData.setValue(employeeList);
    }

    public MutableLiveData<List<Employee>> getEmployeeMutableLiveData(){
        return employeeMutableLiveData;
    }
}
