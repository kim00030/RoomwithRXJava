package demo.dankim.com.roomwithrxjava.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Dan Kim on 2019-01-29
 */

@Entity(tableName = "employees")
public class Employee {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "firstname")
    private String firstName;
    @ColumnInfo(name = "lastname")
    private String lastName;

    public Employee(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
