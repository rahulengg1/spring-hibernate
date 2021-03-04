package com.rahul.mongodb.repo;

import com.rahul.mongodb.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product, String> {
}
