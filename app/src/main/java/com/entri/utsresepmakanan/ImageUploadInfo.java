package com.entri.utsresepmakanan;

public class ImageUploadInfo {

    public String name;

    public String imageUrl;

    public ImageUploadInfo() {

    }

    public ImageUploadInfo(String name, String imageUrl) {

        this.name = name;
        this.imageUrl= imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}