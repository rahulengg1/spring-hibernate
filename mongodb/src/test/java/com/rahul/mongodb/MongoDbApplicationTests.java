package com.rahul.mongodb;

import com.rahul.mongodb.model.Product;
import com.rahul.mongodb.repo.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MongoDbApplicationTests {

	@Autowired
	ProductRepo productRepo;


	@Test
	public void testSave()
	{
		Product product=new Product();
		product.setName("iphone");
		product.setPrice(1000);
	    productRepo.save(product);

	    List<Product> savedProduct = productRepo.findAll();
		assertThat(savedProduct).isNotNull();
		assertThat(savedProduct).hasSize(1);
		assertThat(savedProduct.get(0).getName()).isEqualTo("iphone");
	}

	@AfterEach
	public void clear()
	{
		productRepo.deleteAll();
	}

}
