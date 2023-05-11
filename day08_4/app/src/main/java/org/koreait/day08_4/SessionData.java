package org.koreait.day08_4;

public class SessionData {
    public static User user;

    /**
     * User user가 null이면 미로그인 상태
     * null이 아니면 로그인 상태
     *
     * @return
     */
    public static boolean isLogin() {
        return user != null;
    }
}
