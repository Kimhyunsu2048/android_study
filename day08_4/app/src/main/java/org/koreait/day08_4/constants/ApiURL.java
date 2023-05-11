package org.koreait.day08_4.constants;

public class ApiURL {
    public static final String HOST = "http://192.168.3.101:3000";
    public static final String API_HOST = HOST + "/api";
    public static final String JOIN_URL = API_HOST + "/user"; // 회원가입
    public static final String LOGIN_URL = API_HOST + "/user/login"; // 로그인
    public static final String USER_INFO_URL = API_HOST + "/user/view/"; // 회원정보 조회
    public static final String DIARY_URL = API_HOST + "/diary"; // 일기 쓰기, 조회, 목록
    public static final String UPLOAD_URL = HOST + "/uploads";
    public static final String PHOTO_URL = API_HOST + "/photos";
}
