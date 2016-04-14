package com.getto.repository;

import org.springframework.data.repository.CrudRepository;

import com.getto.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);
}
