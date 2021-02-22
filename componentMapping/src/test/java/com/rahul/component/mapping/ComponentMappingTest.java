package com.rahul.component.mapping;


import com.rahul.component.mapping.entities.Address;
import com.rahul.component.mapping.entities.Restaurant;
import com.rahul.component.mapping.entities.repository.RestaurantRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComponentMappingTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    public void Test()
    {
        Restaurant restaurant = new Restaurant();
        Address address= new Address();

        restaurant.setName("test restaurant");
        address.setStreetAddress("test street address");
        address.setCity("Berlin");
        address.setState("Berlin");
        address.setCountry("Germany");
        address.setZipCode("14197");
        restaurant.setAddress(address);

        restaurantRepository.save(restaurant);

        List<Restaurant> savedInfo= restaurantRepository.findAll();

        assertEquals(1, savedInfo.size());
        assertEquals("test restaurant", savedInfo.get(0).getName());
        assertEquals("Germany", savedInfo.get(0).getAddress().getCountry());
        assertEquals("Berlin", savedInfo.get(0).getAddress().getCity());
    }



}
