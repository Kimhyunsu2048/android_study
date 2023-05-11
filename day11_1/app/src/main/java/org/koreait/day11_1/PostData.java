package org.koreait.day11_1;

import android.os.Parcel;
import android.os.Parcelable;

public class PostData implements Parcelable {
    private int imageId;
    private String subject; // 게시글 제목
    private String content; // 게시글 내용

    public PostData() {}

    public PostData(int imageId, String subject, String content) {
        this.imageId = imageId;
        this.subject = subject;
        this.content = content;
    }

    protected PostData(Parcel in) {
        imageId = in.readInt();
        subject = in.readString();
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(subject);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PostData> CREATOR = new Creator<PostData>() {
        @Override
        public PostData createFromParcel(Parcel in) {
            return new PostData(in);
        }

        @Override
        public PostData[] newArray(int size) {
            return new PostData[size];
        }
    };

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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

}
