package com.rahul.caching.entities.repo;

import com.rahul.caching.entities.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}