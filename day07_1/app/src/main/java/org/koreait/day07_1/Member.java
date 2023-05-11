package org.koreait.day07_1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class Member implements Parcelable {
    private String userId;
    private String userPw;
    private String userNm;
    private LocalDateTime regDt;

    public Member() {}


    protected Member(Parcel in) {
        userId = in.readString();
        userPw = in.readString();
        userNm = in.readString();
        regDt = LocalDateTime.parse(in.readString());
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel in) {
            return new Member(in);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeString(userPw);
        parcel.writeString(userNm);
        parcel.writeString(regDt.toString());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public LocalDateTime getRegDt() {
        return regDt;
    }

    public void setRegDt(LocalDateTime regDt) {
        this.regDt = regDt;
    }

    @Override
    public String toString() {
        return "Member{" +
                "userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userNm='" + userNm + '\'' +
                ", regDt=" + regDt +
                '}';
    }
}
