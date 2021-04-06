package com.regmi.userdetail.rest.payload.request;

import javax.validation.constraints.Email;

public class UserDetails {
    private String fullName;
    @Email
    private String email;
    private Long number;

    public UserDetails() {
    }

    public UserDetails(String fullName, @Email String email, Long number) {
        this.fullName = fullName;
        this.email = email;
        this.number = number;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
