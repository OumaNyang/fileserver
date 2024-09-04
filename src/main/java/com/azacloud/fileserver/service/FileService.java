package com.azacloud.fileserver.service;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azacloud.fileserver.FileMetadata;

@Service
@Cacheable("files")

public class FileService {

    @Autowired FileMetadata;

  public FileSummary getSummary() {
        // Return total files count and storage size
   
   public List<FileMetadata> listFiles() {
    return fileRepository.findAll();
}
   
   
    }




}
