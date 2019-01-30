package demo.dankim.com.roomwithrxjava.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Dan Kim on 2019-01-29
 */
@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employees")
    Flowable<List<Employee>> getAllEmployees();

    @Insert
    void insertEmployee(Employee employee);


}
