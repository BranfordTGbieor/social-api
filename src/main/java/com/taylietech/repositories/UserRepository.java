package com.taylietech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taylietech.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
