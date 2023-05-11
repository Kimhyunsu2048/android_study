package org.koreait.day08_4;

import android.os.Parcel;
import android.os.Parcelable;

import org.koreait.day08_4.constants.ApiURL;

import java.time.LocalDateTime;

public class Diary implements Parcelable {

    private long id; // 등록번호
    private String imageUrl = ApiURL.UPLOAD_URL + "/image.jpg"; // 이미지 URL
    private String subject; // 제목
    private String content; // 내용
    private User user; // 사용자
    private LocalDateTime regDt; // 등록일시
    private LocalDateTime modDt; // 수정일시

    public Diary() {}

    protected Diary(Parcel in) {
        id = in.readLong();
        subject = in.readString();
        content = in.readString();
        regDt = LocalDateTime.parse(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(subject);
        dest.writeString(content);
        dest.writeString(regDt.toString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Diary> CREATOR = new Creator<Diary>() {
        @Override
        public Diary createFromParcel(Parcel in) {
            return new Diary(in);
        }

        @Override
        public Diary[] newArray(int size) {
            return new Diary[size];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getRegDt() {
        return regDt;
    }

    public void setRegDt(LocalDateTime regDt) {
        this.regDt = regDt;
    }

    public LocalDateTime getModDt() {
        return modDt;
    }

    public void setModDt(LocalDateTime modDt) {
        this.modDt = modDt;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", regDt=" + regDt +
                ", modDt=" + modDt +
                '}';
    }
}
