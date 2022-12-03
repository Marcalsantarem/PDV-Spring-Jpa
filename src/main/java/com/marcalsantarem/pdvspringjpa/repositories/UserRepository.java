package com.marcalsantarem.pdvspringjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcalsantarem.pdvspringjpa.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
