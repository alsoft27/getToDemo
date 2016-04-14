package com.getto.service;

import com.getto.domain.User;

public interface UserService {

    Iterable<User> listAllUsers();

    User getUserById(Long id);

    User getUserByName(String name);

    User saveUser(User user);

    void deleteUser(Long id);

}
