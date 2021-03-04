package com.rahul.caching;


import com.rahul.caching.entities.Employee;
import com.rahul.caching.entities.repo.EmployeeRepository;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.slf4j.event.LoggingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EntitiesTest {


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EntityManager entityManager;

    private List<Employee> employeeList;


    @BeforeEach
    public void setup() {
        Date date = new Date();
        List<Employee> employees = new ArrayList<>();

        Employee employee1 = new Employee();
        employee1.setEmail("employee1@test.com");
        employee1.setDateOfBirth(new Timestamp(date.getTime()));
        employee1.setName("employee 1");

        employees.add(employee1);

        employeeList = employeeRepository.saveAll(employees);
    }


    @Test
    @Transactional
    public void testCache() {
        Session session = entityManager.unwrap(Session.class);

        Employee employee = employeeRepository.findById(employeeList.get(0).getId()).get();
        employeeRepository.findById(employeeList.get(0).getId());

        //evict the first-level object which is created from first query above
        session.evict(employee);
        employeeRepository.findById(employeeList.get(0).getId());
        // there should be one entry of select query in log to verify the second-level cache is running or not.

    }


}
