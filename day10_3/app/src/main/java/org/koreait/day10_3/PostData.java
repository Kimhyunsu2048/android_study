package org.koreait.day10_3;

public class PostData {

    private int imageId; // 이미지 Resource Id
    private String subject; // 제목
    private String content; // 내용

    public PostData(int imageId, String subject, String content) {
        this.imageId = imageId;
        this.subject = subject;
        this.content = content;
    }

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
