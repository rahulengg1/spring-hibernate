package com.rahul.composite.entities;

import com.rahul.composite.entities.repo.CustomerRepo;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerTest {

    @Autowired
    CustomerRepo customerRepo;

    @Test
    public void testSave()
    {
        Customer customer= new Customer();
        customer.setEmailId("test@test.com");
        customer.setName("Test Customer");
        customer.setId(1);
        customerRepo.save(customer);
    }

    @Test
    public void testDuplicate()
    {
        Customer customer= new Customer();
        customer.setEmailId("test@test.com");
        customer.setName("Test Customer");
        customer.setId(1);
        customerRepo.save(customer);

        List<Customer> customers = customerRepo.findAll();
        assertThat(customers).hasSize(1);
        assertThat(customers.get(0).getName()).isEqualTo("Test Customer");

        Customer updateCustomer= new Customer();
        updateCustomer.setEmailId("test@test.com");
        updateCustomer.setName("Test Customer 2");
        updateCustomer.setId(1);
        customerRepo.save(updateCustomer);

        customers = customerRepo.findAll();
        assertThat(customers).hasSize(1);

        assertThat(customers.get(0).getName()).isEqualTo("Test Customer 2");
    }


}