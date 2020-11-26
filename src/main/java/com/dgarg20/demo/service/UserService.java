package com.dgarg20.demo.service;

import com.dgarg20.demo.requests.AddUserRequest;

/**
 * Created by Deepanshu Garg on 26/11/20.
 */
public interface UserService {
     AddUserRequest getUserById(String emailId) throws Exception;

    void AddUser(AddUserRequest addUserRequest, String currentUser)throws Exception;
}
