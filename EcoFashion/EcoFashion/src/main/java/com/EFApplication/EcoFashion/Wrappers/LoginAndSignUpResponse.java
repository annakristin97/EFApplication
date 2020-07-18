package com.EFApplication.EcoFashion.Wrappers;

import com.EFApplication.EcoFashion.Entities.User;

import java.util.List;

public class LoginAndSignUpResponse {

    private User user;

    public LoginAndSignUpResponse(User user){
        this.user = user;
    }

    public LoginAndSignUpResponse(User user, String message, List<?> errors) {

        //super(message, errors);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
