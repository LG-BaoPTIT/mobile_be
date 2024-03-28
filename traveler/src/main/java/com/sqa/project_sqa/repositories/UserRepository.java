package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByEmail(String email);
    User findUserByUserName(String email);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByUserName(String userName);
}
