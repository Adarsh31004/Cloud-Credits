package com.cloudcredits.cloud.credits.repo;

import com.cloudcredits.cloud.credits.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);


    List<Users> findByRole(String role);
}
