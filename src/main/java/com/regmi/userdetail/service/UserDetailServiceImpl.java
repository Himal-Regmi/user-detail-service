package com.regmi.userdetail.service;

import com.regmi.userdetail.dao.UserDetailDao;
import com.regmi.userdetail.exception.UserDetailNotFoundException;
import com.regmi.userdetail.model.UserDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    private UserDetailDao userDetailDao;

    @Override
    @Transactional
    public UserDetailEntity getUserDetails(int id){
        UserDetailEntity userDetail= userDetailDao.getUserDetails(id);
        if(userDetail == null){
            throw new UserDetailNotFoundException("User details not available");
        }
        return userDetail;
    }

    @Override
    @Transactional
    public void saveUserDetails(UserDetailEntity userDetail) {
        userDetailDao.save(userDetail);
    }

    @Override
    @Transactional
    public void deleteUserDetailsById(int id) {
        userDetailDao.deleteUserDetailsById(id);
    }

    @Override
    @Transactional
    public void updateUserDetails(UserDetailEntity userDetail) {
        userDetailDao.update(userDetail);
    }

    @Override
    @Transactional
    public UserDetailEntity getUserDetailsByUserId(int id) {
        return userDetailDao.getUserDetailsByUserId(id);
    }
}
