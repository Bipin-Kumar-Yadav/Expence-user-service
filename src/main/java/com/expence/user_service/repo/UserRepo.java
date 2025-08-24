package com.expence.user_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expence.user_service.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,String>{
    
}
