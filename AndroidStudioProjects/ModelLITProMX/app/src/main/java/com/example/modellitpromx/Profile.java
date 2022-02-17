package com.example.modellitpromx;

public class Profile {
    private String email;
    private String profileImgUrl;
    private String name;

    public Profile(String email, String profileImgUrl, String name) {
        this.email = email;
        this.profileImgUrl = profileImgUrl;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getprofileImgUrl() {
        return profileImgUrl;
    }

    public void setprofileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "email='" + email + '\'' +
                ", profileImgUrl='" + profileImgUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
