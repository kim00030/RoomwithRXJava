package demo.dankim.com.roomwithrxjava.repository;

import android.app.Application;
import android.content.Context;

import java.util.List;

import demo.dankim.com.roomwithrxjava.database.Employee;
import demo.dankim.com.roomwithrxjava.database.EmployeeDao;
import demo.dankim.com.roomwithrxjava.database.EmployeeDatabase;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dan Kim on 2019-01-29
 */
public class Repository {

    private EmployeeDatabase employeeDatabase;
    private EmployeeDao employeeDao;
    private Disposable disposable = null;

    public Repository(Application application) {

        employeeDatabase = EmployeeDatabase.getInstance(application.getApplicationContext());
        employeeDao = employeeDatabase.getEmployeeDao();

    }

    public void addData(final DataCallBack dataCallBack, final String firstName, final String lastName) {


        Completable completable = Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {

                Employee employee = new Employee(firstName, lastName);
                employeeDao.insertEmployee(employee);

            }
        });

        completable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        disposable = d;
                    }

                    @Override
                    public void onComplete() {

                        dataCallBack.dataAdded();
                    }

                    @Override
                    public void onError(Throwable e) {

                        dataCallBack.errorAdded();
                    }
                });

    }

    public void getAllData(final DataCallBack dataCallBack) {

        employeeDao.getAllEmployees().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Employee>>() {
                    @Override
                    public void accept(List<Employee> employees) throws Exception {

                        dataCallBack.setAllDataWith(employees);
                    }
                });
    }

    public void dispose() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
