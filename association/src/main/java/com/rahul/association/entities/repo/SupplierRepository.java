package com.rahul.association.entities.repo;

import com.rahul.association.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
