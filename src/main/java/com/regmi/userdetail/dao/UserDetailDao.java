package com.regmi.userdetail.dao;

import com.regmi.userdetail.model.UserDetailEntity;

public interface UserDetailDao {
    UserDetailEntity getUserDetails(int id);

    void save(UserDetailEntity userDetail);

    void update(UserDetailEntity userDetailEntity);

    void deleteUserDetailsById(int id);

    UserDetailEntity getUserDetailsByUserId(int id);
}
