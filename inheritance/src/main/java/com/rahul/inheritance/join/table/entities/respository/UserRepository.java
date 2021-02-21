package com.rahul.inheritance.join.table.entities.respository;

import com.rahul.inheritance.join.table.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
