package com.fridayapp.attendancetracker.Services;

public interface MyInterface {

    // for login
    void register();
    void login(String name, String email,String image, String created_at);
    void logout();
}
