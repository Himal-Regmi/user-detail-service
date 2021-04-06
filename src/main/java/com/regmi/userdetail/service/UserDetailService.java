package com.regmi.userdetail.service;

import com.regmi.userdetail.model.UserDetailEntity;

public interface UserDetailService {
    UserDetailEntity getUserDetails(int id);

    void saveUserDetails(UserDetailEntity userDetail);

    void deleteUserDetailsById(int id);

    void updateUserDetails(UserDetailEntity userDetail);

    UserDetailEntity getUserDetailsByUserId(int id);
}
