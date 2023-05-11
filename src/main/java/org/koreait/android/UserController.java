package org.koreait.android;

import org.koreait.entities.Users;
import org.koreait.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UsersRepository repository;

    // 회원 가입
    @PostMapping
    public ResponseEntity<JSONResult<Object>> join(Users user) {

        String userId = user.getUserId();
        String userPw = user.getUserPw();
        String userNm = user.getUserNm();
        if (userId == null || userId.isBlank()) {
            throw new CommonException("아이디를 입력하세요.", HttpStatus.BAD_REQUEST);
        }

        if (userPw == null || userPw.isBlank()) {
            throw new CommonException("비밀번호를 입력하세요.", HttpStatus.BAD_REQUEST);
        }

        if (userNm == null || userNm.isBlank()) {
            throw new CommonException("회원명을 입력하세요.", HttpStatus.BAD_REQUEST);
        }

        Users user2 = repository.findByUserId(userId);
        if (user2 != null) { // 이미 등록된 아이디
            throw new CommonException("이미 가입된 아이디 입니다.", HttpStatus.BAD_REQUEST);
        }

        user = repository.saveAndFlush(user);

        JSONResult<Object> jsonResult = JSONResult.builder()
                .status(HttpStatus.OK)
                .data(user)
                .success(true)
                .build();

        return ResponseEntity.ok(jsonResult);
    }

    // 회원정보 조회
    @GetMapping("/view/{userId}")
    public Users getUser(@PathVariable String userId) {
        Users user = repository.findByUserId(userId);

        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<JSONResult<Object>> login(String userId, String userPw) {
        Users user = repository.findByUserId(userId);
        if (user == null) {
            throw new CommonException("아이디를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        if (userPw == null || !userPw.equals(user.getUserPw())) {
            throw new CommonException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        JSONResult<Object> jsonResult = JSONResult.builder()
                .status(HttpStatus.OK)
                .success(true)
                .data(user.getUserId())
                .build();

        return ResponseEntity.ok(jsonResult);
    }
}
