package com.fridayapp.attendancetracker.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int id;
    @SerializedName("response")
    private String response;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;


    @SerializedName("image")
    private String image;

    @SerializedName("created_at")
    private String created_at;

    public int getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "User{" +
                "response='" + response + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", email='" + email + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
