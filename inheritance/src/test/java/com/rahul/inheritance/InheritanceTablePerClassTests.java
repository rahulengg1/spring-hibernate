package com.rahul.inheritance;

import com.rahul.inheritance.table.per_class.entities.Customer;
import com.rahul.inheritance.table.per_class.entities.OnlineCustomer;
import com.rahul.inheritance.table.per_class.entities.WalkingCustomer;
import com.rahul.inheritance.table.per_class.entities.repository.CustomerRepository;
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
public class InheritanceTablePerClassTests {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testOnlineCustomer() {
        OnlineCustomer onlineCustomer = new OnlineCustomer();
        onlineCustomer.setName("test user 1");
        onlineCustomer.setId(1);
        onlineCustomer.setDiscount(100);
        customerRepository.save(onlineCustomer);

        List<Customer> savedCustomerInfo = customerRepository.findAll();
        assertEquals(1, savedCustomerInfo.size());
        assertEquals("test user 1", ((OnlineCustomer) savedCustomerInfo.get(0)).getName());
    }

    @Test
    public void testWalkingCustomer() {
        WalkingCustomer walkingCustomer = new WalkingCustomer();
        walkingCustomer.setName("test user 2");
        walkingCustomer.setId(2);
        walkingCustomer.setDiscount(150);
        customerRepository.save(walkingCustomer);

        List<Customer> savedCustomerInfo = customerRepository.findAll();
        assertEquals(1, savedCustomerInfo.size());
        assertEquals("test user 2", ((WalkingCustomer) savedCustomerInfo.get(0)).getName());
    }

    @AfterEach
    public void clearDb() {
        customerRepository.deleteAll();
    }
}
