package com.example.user_upload_service_bk.repository;

import com.example.user_upload_service_bk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
