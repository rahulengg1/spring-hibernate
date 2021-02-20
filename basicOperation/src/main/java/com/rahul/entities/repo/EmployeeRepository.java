package com.rahul.entities.repo;

import com.rahul.entities.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByName(String name);

    List<Employee> findByIdGreaterThan(Integer id);

    List<Employee> findByEmail(String name, Pageable pageable);

    @Query("from Employee")
    List<Employee> findAllEmployee();

    //Pagination
    @Query("from Employee")
    List<Employee> findAllEmployee(Pageable pageable);

    // Partial data fetch
    @Query("select id,name from Employee")
    List<Tuple> findAllEmployeeWithIdName();

    // Named query
    @Query("from Employee where name=:name")
    List<Employee> findAllEmployeeByName(@Param("name") String name);

    //Transactional
    @Modifying
    @Query("delete from Employee where name=:name")
    void deleteAllEmployeeByName(@Param("name") String name);

    //Native Query
    @Query(value = "select * from employee" , nativeQuery = true)
    List<Employee> findAllEmployeeNQ();

    //Native Query
    @Query(value = "select * from employee where name=:name" , nativeQuery = true)
    List<Employee> findAllEmployeeNQ(@Param("name") String name);

}