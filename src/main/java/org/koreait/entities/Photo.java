package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Photo {
    @Id @GeneratedValue
    private Long id;
    private String imageUrl;
}
