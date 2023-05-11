package org.koreait.android;

import lombok.Data;

@Data
public class DiaryForm {
    private String subject;
    private String content;

    private String imageData;

    private String userId;
}
