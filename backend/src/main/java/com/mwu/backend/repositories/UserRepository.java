package com.mwu.backend.repositories;


import com.mwu.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    void deleteById(int id);
    User findByEmail(String email);
}
