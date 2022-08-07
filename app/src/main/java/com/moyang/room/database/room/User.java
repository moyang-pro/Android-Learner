package com.moyang.room.database.room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Description:
 * @author: moyang
 * @date: 2022/8/7 19:56
 */
@Entity
public class User implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    public User() {
    }

    // 读,读和写的顺序必须一致
    protected User(Parcel in) {
        uid = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
    }

    // 写
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(firstName);
        dest.writeString(lastName);
    }

    // 内部调用,一定要有
    public static final Creator<User> CREATOR = new Creator<User>() {

        // 通过Parcel创建对象
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
