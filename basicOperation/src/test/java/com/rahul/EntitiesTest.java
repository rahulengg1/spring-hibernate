package com.rahul;


import com.rahul.entities.Employee;
import com.rahul.entities.repo.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EntitiesTest {


    @Autowired
    EmployeeRepository employeeRepository;


    @Test
    public void contextLoads() {
    }


    /*
     * Prepare initial data
     */
    @BeforeEach
    public void prepareInitialData() {
        Date date = new Date();
        List<Employee> employees = new ArrayList<>();

        Employee employee1 = new Employee();
        employee1.setEmail("employee1@test.com");
        employee1.setDateOfBirth(new Timestamp(date.getTime()));
        employee1.setName("employee 1");

        Employee employee2 = new Employee();
        employee2.setEmail("employee2@test.com");
        employee2.setDateOfBirth(new Timestamp(date.getTime()));
        employee2.setName("employee 2");

        Employee employee3 = new Employee();
        employee3.setEmail("employee1@test.com");
        employee3.setDateOfBirth(new Timestamp(date.getTime()));
        employee3.setName("employee 3");

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        employeeRepository.saveAll(employees);
    }


    // Check total records
    @Test
    public void testTotalRecords() {

        List<Employee> employees = employeeRepository.findAll();
        // Total Records must be 3
        assertEquals(3, employees.size());
    }

    @Test
    public void testFindOneByExample() {
        //Find the db record of a Employee
        Employee employee1 = new Employee();
        employee1.setName("employee 1");
        Example<Employee> example = Example.of(employee1);
        Optional<Employee> employee = employeeRepository.findOne(example);
        assertTrue(employee.isPresent());

        assertNotNull(employee.get());
        assertEquals("employee1@test.com", employee.get().getEmail());
    }


    @Test
    public void testFindByName() {
        List<Employee> employees = employeeRepository.findByName("employee 2");
        assertEquals("employee2@test.com", employees.get(0).getEmail());
    }


    @Test
    public void testFindByIdGreaterThan() {
        List<Employee> employees = employeeRepository.findByIdGreaterThan(1);
        assertEquals("employee 2", employees.get(0).getName());
    }

    @Test
    public void testUpdate() {
        Optional<Employee> employee = employeeRepository.findById(1);
        if (employee.isPresent()) {
            Employee employee1 = employee.get();
            employee1.setName("test1234");
            employeeRepository.save(employee1);
        }

        Optional<Employee> savedEmployeeRecord = employeeRepository.findById(1);
        assertEquals("test1234", savedEmployeeRecord.get().getName());
    }

    @Test
    public void testDelete() {
        if (employeeRepository.existsById(1)) {
            employeeRepository.deleteById(1);
        }

        assertFalse(employeeRepository.existsById(1));
    }


    /*
    *  Total 3 records are there, Page starts from 0 and for each page 2 records are set
    *  then employee stream count should be 2.
    *  For page 1, only one record left
     */
    @Test
    public void testFindAllPaging() {
        Pageable pageable = PageRequest.of(0, 2);

        Page<Employee> employees = employeeRepository.findAll(pageable);
        Stream<Employee> employeeStream = employees.get();
        assertEquals(2, employeeStream.count());

        Pageable pageableForSecondPage = PageRequest.of(1, 2);
        Page<Employee> employeeSecondPage = employeeRepository.findAll(pageableForSecondPage);
        Stream<Employee> employeeStreamForSecondPage = employeeSecondPage.get();
        assertEquals(1, employeeStreamForSecondPage.count());

    }


    // Sorting by email
    @Test
    public void testFindAllSorting() {
        List<Employee> employees = employeeRepository.findAll(Sort.by("email"));
        assertEquals("employee 1", employees.get(0).getName());
        assertEquals("employee 3", employees.get(1).getName());
        assertEquals("employee 2", employees.get(2).getName());
    }

    //Sorting for more then one column and in descending order
    @Test
    public void testFindAllSortingDirection() {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(Sort.Order.desc("email"));
        orders.add(Sort.Order.desc("id"));
        List<Employee> employees = employeeRepository.findAll(Sort.by(orders));
        assertEquals("employee 2", employees.get(0).getName());
        assertEquals("employee 3", employees.get(1).getName());
        assertEquals("employee 1", employees.get(2).getName());
    }


    // Paging and sorting
    @Test
    public void testFindAllPagingAndSorting() {
        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.ASC, "name");
        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<Employee> employeeList = employees.toList();

        assertEquals("employee 1", employeeList.get(0).getName());
        assertEquals("employee 2", employeeList.get(1).getName());
    }


    // Paging and sorting
    @Test
    public void testFindAllPagingAndMultiColumnSorting() {

        Sort sort = Sort.by(Sort.Order.desc("email"), Sort.Order.asc("id"));
        Pageable pageable = PageRequest.of(0, 3, sort);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<Employee> employeeList = employees.toList();

        assertEquals("employee 2", employeeList.get(0).getName());
        assertEquals("employee 1", employeeList.get(1).getName());
        assertEquals("employee 3", employeeList.get(2).getName());
    }

    // Paging and Sorting along with findByEmail
    @Test
    public void testFindByEmail() {

        Sort sort = Sort.by(Sort.Order.desc("email"), Sort.Order.asc("id"));
        Pageable pageable = PageRequest.of(0, 3, sort);
        List<Employee> employees = employeeRepository.findByEmail("employee1@test.com", pageable);


        assertEquals("employee 1", employees.get(0).getName());
        assertEquals("employee 3", employees.get(1).getName());

    }


    // JPQL query to find all Employee
    @Test
    public void testFindAllEmployee() {
        List<Employee> employees = employeeRepository.findAllEmployee();
        assertEquals("employee 1", employees.get(0).getName());
        assertEquals("employee 2", employees.get(1).getName());
        assertEquals("employee 3", employees.get(2).getName());
    }

    // JPQL query to find all Employee with paging support
    @Test
    public void testFindAllEmployeePageable() {
        List<Employee> employees = employeeRepository.findAllEmployee(PageRequest.of(0, 2));
        assertEquals(2, employees.size());
        assertEquals("employee 1", employees.get(0).getName());
        assertEquals("employee 2", employees.get(1).getName());
    }

    // JPQL query to fetch partial column, In below case its id and name only
    @Test
    public void testFindAllEmployeeWithIdName() {
        List<Tuple> employees = employeeRepository.findAllEmployeeWithIdName();
        assertEquals(1, employees.get(0).get(0));
        assertEquals("employee 1", employees.get(0).get(1));

        assertEquals(2, employees.get(1).get(0));
        assertEquals("employee 2", employees.get(1).get(1));

        assertEquals(3, employees.get(2).get(0));
        assertEquals("employee 3", employees.get(2).get(1));
    }

    //Named Query
    @Test
    public void testFindAllEmployeeByName() {
        List<Employee> employees = employeeRepository.findAllEmployeeByName("employee 1");
        assertEquals(1, employees.size());
        assertEquals(1, employees.get(0).getId().intValue());
    }

    /*
     * Check transaction works properly, Deleting one records and then check how many records left.
     */
    @Test
    @Transactional
    public void testDeleteAllEmployeeByName() {
        employeeRepository.deleteAllEmployeeByName("employee 1");
        List<Employee> employees = employeeRepository.findAll();
        assertEquals(2, employees.size());
        assertEquals("employee 2", employees.get(0).getName());
        assertEquals("employee 3", employees.get(1).getName());
    }

    // Native Query, use normal SQL queries as it is.
    @Test
    public void testAllEmployeeNativeQuery() {
        List<Employee> employees = employeeRepository.findAllEmployeeNQ();
        assertEquals(3, employees.size());
        assertEquals("employee 1", employees.get(0).getName());
        assertEquals("employee 2", employees.get(1).getName());
        assertEquals("employee 3", employees.get(2).getName());
    }

    // Conditional Native Query
    @Test
    public void testAllEmployeeNativeQueryWithParameter() {
        List<Employee> employees = employeeRepository.findAllEmployeeNQ("employee 1");
        assertEquals(1, employees.size());
        assertEquals("employee 1", employees.get(0).getName());
    }


    // Clean the database after each test run
    @AfterEach
    public void clean() {
        employeeRepository.deleteAll();
    }
}
