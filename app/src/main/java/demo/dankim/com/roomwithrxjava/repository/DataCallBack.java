package demo.dankim.com.roomwithrxjava.repository;

import java.util.List;

import demo.dankim.com.roomwithrxjava.database.Employee;

/**
 * Created by Dan Kim on 2019-01-29
 */
public interface DataCallBack {

    void dataAdded();
    void errorAdded();
    void setAllDataWith(List<Employee> employeeList);
}
