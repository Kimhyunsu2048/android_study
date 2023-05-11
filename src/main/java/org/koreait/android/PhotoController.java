package org.koreait.android;

import jakarta.servlet.http.HttpServletRequest;
import org.koreait.entities.Photo;
import org.koreait.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.domain.Sort.Order.desc;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    @Value("${@file.upload.path}")
    private String fileUploadPath;

    @Autowired
    private PhotoRepository repository;

    @GetMapping
    public List<Photo> gets() {
        List<Photo> photos = repository.findAll(Sort.by(desc("id")));

        return photos;
    }

    @PostMapping
    public void save(String imageData, HttpServletRequest request) {
        String fileName = UUID.randomUUID().toString() + ".jpg";
        String filePath = fileUploadPath + fileName;

        byte[] bytes = imageData.getBytes();
        bytes = Base64.getDecoder().decode(bytes);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            while (bis.available() > 0) {
                bos.write(bis.read());
            }

            bos.flush();

            Photo photo = new Photo();
            photo.setImageUrl(request.getContextPath() + " /uploads/" + fileName);
            repository.saveAndFlush(photo);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
