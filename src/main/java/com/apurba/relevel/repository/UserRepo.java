package com.apurba.relevel.repository;

import com.apurba.relevel.model.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

	User findByemailid(String email);
}
