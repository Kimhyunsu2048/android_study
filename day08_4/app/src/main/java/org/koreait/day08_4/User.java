package org.koreait.day08_4;

public class User {
    private Long userNo;
    private String userId;
    private String userPw;
    private String userNm;

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
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

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userNm='" + userNm + '\'' +
                '}';
    }
}
