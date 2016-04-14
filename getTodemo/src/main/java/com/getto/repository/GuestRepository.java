package com.getto.repository;

import org.springframework.data.repository.CrudRepository;

import com.getto.domain.Guest;

public interface GuestRepository extends CrudRepository<Guest, Long> {

}
