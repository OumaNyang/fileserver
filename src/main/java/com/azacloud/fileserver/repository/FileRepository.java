package com.azacloud.fileserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azacloud.fileserver.FileMetadata;

@Repository
public interface FileRepository extends JpaRepository<FileMetadata, Long> {

    // Method to find a file by its name
    Optional<FileMetadata> findByFileName(String fileName);

    // Method to delete a file by its name
    void deleteByFileName(String fileName);
}
