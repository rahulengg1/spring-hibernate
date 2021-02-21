package com.rahul.inheritance;

import com.rahul.inheritance.join.table.entities.Agent;
import com.rahul.inheritance.join.table.entities.Employees;
import com.rahul.inheritance.join.table.entities.User;
import com.rahul.inheritance.join.table.entities.respository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InheritanceTableJoinTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testEmployee() {
        Employees employees = new Employees();
        employees.setName("Employee");
        employees.setId(1);
        employees.setDiscount(100);
        userRepository.save(employees);

        List<User> savedUserInfo = userRepository.findAll();
        assertEquals(1, savedUserInfo.size());
        assertEquals("Employee", ((Employees) savedUserInfo.get(0)).getName());
    }

    @Test
    public void testAgent() {
        Agent agent = new Agent();
        agent.setName("Agent");
        agent.setId(2);
        agent.setDiscount(140);
        userRepository.save(agent);

        List<User> savedUserInfo = userRepository.findAll();
        assertEquals(1, savedUserInfo.size());
        assertEquals("Agent", ((Agent) savedUserInfo.get(0)).getName());
    }

    @AfterEach
    public void clearDb() {
        userRepository.deleteAll();
    }
}
