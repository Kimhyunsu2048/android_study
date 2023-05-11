package org.koreait.android;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.Diary;
import org.koreait.entities.Users;
import org.koreait.repositories.DiaryRepository;
import org.koreait.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryController {
    @Value("${file.upload.path}")
    private String fileUploadPath;

    private final DiaryRepository repository;
    private final UsersRepository usersRepository;

    @GetMapping("/{userId}")
    public List<Diary> search(@PathVariable String userId) {

        List<Diary> diaries = repository.getListByUser(userId);

        return diaries;
    }

    @PostMapping
    public ResponseEntity<JSONResult<Object>> write(DiaryForm diaryForm, HttpServletRequest request) {

        String userId = diaryForm.getUserId();
        String subject = diaryForm.getSubject();
        String content = diaryForm.getContent();
        String imageData = diaryForm.getImageData();
        System.out.println(imageData);

        Users user = usersRepository.findByUserId(userId);

        Diary diary = Diary.builder()
                .subject(subject)
                .content(content)
                .user(user)
                .build();

        if (imageData != null) {
            String fileName = UUID.randomUUID().toString() + ".jpg";
            String filePath = fileUploadPath + fileName;

            byte[] bytes = Base64.getDecoder().decode(imageData);

            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
                while (bis.available() > 0) {
                    bos.write(bis.read());
                }

                bos.flush();

                String fileURL = request.getContextPath() + "/uploads/" + fileName;
                diary.setImageUrl(fileURL);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        diary = repository.saveAndFlush(diary);

        JSONResult<Object> jsonResult = JSONResult.builder()
                .success(true)
                .status(HttpStatus.OK)
                .data(diary.getId()) // 등록번호
                .build();

        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping("/view/{id}")
    public Diary view(@PathVariable Long id) {
        Diary diary = repository.findById(id).orElse(null);

        return diary;
    }
}
