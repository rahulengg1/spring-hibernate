package com.rahul.association.onetoone.entities.repo;

import com.rahul.association.onetoone.entities.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Long> {
}
