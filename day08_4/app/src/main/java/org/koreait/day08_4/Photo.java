package org.koreait.day08_4;

public class Photo {
    private Long id;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
