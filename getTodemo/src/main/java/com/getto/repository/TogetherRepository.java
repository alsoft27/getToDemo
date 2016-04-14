package com.getto.repository;

import org.springframework.data.repository.CrudRepository;

import com.getto.domain.Together;

public interface TogetherRepository extends CrudRepository<Together, Long> {

    Together findByName(String name);
}
