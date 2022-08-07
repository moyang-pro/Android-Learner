package com.moyang.room.database.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Description:
 * @author: moyang
 * @date: 2022/8/7 19:56
 */
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
