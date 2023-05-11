package org.koreait.day06_6;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class User implements Parcelable {
    private String userId;
    private String userNm;
    private String email;
    private LocalDateTime regDt;

    public User() {

    }


    protected User(Parcel in) {
        userId = in.readString();
        userNm = in.readString();
        email = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            regDt = LocalDateTime.parse(in.readString());
        }
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeString(userNm);
        parcel.writeString(email);
        parcel.writeString(regDt, toString());
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegDt() {
        return regDt;
    }

    public void setRegDt(LocalDateTime regDt) {
        this.regDt = regDt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userNm='" + userNm + '\'' +
                ", email='" + email + '\'' +
                ", regDt=" + regDt +
                '}';
    }
}
