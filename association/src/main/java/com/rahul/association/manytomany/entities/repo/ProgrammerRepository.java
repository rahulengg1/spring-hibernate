package com.rahul.association.manytomany.entities.repo;

import com.rahul.association.manytomany.entities.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammerRepository extends JpaRepository<Programmer, Integer> {
}
