package com.soft863.service;

import com.soft863.entity.User;

import java.util.List;

public interface IUserService {
    User getUserByID(int id);
    User getUserByID1(int id);
    User logUser(User user);
    List<User> getAllUsers();
}
