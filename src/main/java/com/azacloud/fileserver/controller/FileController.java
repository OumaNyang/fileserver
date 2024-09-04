package com.azacloud.fileserver.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azacloud.fileserver.FileMetadata;
import com.azacloud.fileserver.service.FileService;
import com.azacloud.fileserver.service.FileSummary;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileMetadata> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        FileMetadata metadata = fileService.saveFile(file);
        return ResponseEntity.ok(metadata);
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<Void> deleteFile(@PathVariable String filename) {
        fileService.deleteFile(filename);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FileMetadata>> getFiles() {
        return ResponseEntity.ok(fileService.listFiles());
    }

    @GetMapping("/summary")
    public ResponseEntity<FileSummary> getSummary() {
        return ResponseEntity.ok(fileService.getSummary());
    }
}
