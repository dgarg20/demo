package com.dgarg20.demo.service;

import com.dgarg20.demo.entities.User;
import com.dgarg20.demo.repository.UserDao;
import com.dgarg20.demo.requests.AddUserRequest;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Created by Deepanshu Garg on 26/11/20.
 */
@Slf4j
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Inject
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public AddUserRequest getUserById(String emailId)throws Exception {
        Optional<User> user = userDao.getUserById(emailId);
        if(user.isPresent()){
            return new AddUserRequest(user.get().getName(), user.get().getEmailId(), user.get().getPhoneNumber());
        }
        else {
            log.info("User Not Found {}" , emailId);
            throw new Exception("User Not found");
        }
    }

    @Override
    public void AddUser(AddUserRequest addUserRequest, String currentUser) {
        User user = new User(addUserRequest.getName(), addUserRequest.getEmailId(),  addUserRequest.getPhoneNumber());
        userDao.create(user);
    }
}
