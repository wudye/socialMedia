package com.mwu.backend.repositories;

import com.mwu.backend.models.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
    Optional<UserImage> findByUser_Id(int userId);
}