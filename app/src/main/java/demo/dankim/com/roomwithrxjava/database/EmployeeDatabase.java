package demo.dankim.com.roomwithrxjava.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Dan Kim on 2019-01-29
 */
@Database(entities = Employee.class, version = 1)
public abstract class EmployeeDatabase extends RoomDatabase {

    private static EmployeeDatabase instance;

    public abstract EmployeeDao getEmployeeDao();

    public static synchronized EmployeeDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EmployeeDatabase.class, "employee.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
