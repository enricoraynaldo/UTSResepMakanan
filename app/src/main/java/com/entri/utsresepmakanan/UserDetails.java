package com.entri.utsresepmakanan;

public class UserDetails {
    private String name;
    private String imageUrl;

    public UserDetails() {
    }

    public UserDetails(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
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
