package com.rahul.file.operation.entities.repo;

import com.rahul.file.operation.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
