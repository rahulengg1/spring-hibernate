package com.rahul.composite.embedded.entities.repo;

import com.rahul.composite.embedded.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
}
