package com.rahul.component.mapping.entities.repository;

import com.rahul.component.mapping.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
