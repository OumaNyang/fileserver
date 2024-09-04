package com.azacloud.fileserver.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azacloud.fileserver.FileMetadata;
import com.azacloud.fileserver.repository.FileRepository;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileMetadata saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileExtension = FilenameUtils.getExtension(fileName);
        boolean isImage = fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("png");

        if (isImage) {
            // Compress image and save
            BufferedImage image = ImageIO.read(file.getInputStream());
            File compressedFile = new File("/path/to/save/compressed_" + fileName);
            OutputStream os = new FileOutputStream(compressedFile);
            ImageIO.write(image, fileExtension, os);
            os.close();
        } else {
            // Save file without compression
            File destination = new File("/path/to/save/" + fileName);
            file.transferTo(destination);
        }

        FileMetadata metadata = new FileMetadata();
        metadata.setFileName(fileName);
        metadata.setFileUrl("/url/path/to/" + fileName); // Replace with actual URL path
        metadata.setFileSize(file.getSize());
        metadata.setUploadDate(LocalDateTime.now());

        return fileRepository.save(metadata);
    }

    public void deleteFile(String fileName) {
        fileRepository.deleteByFileName(fileName);
        // Additional logic to delete the physical file from the storage
    }

    public List<FileMetadata> listFiles() {
        return fileRepository.findAll();
    }

    public FileSummary getSummary() {
        List<FileMetadata> files = fileRepository.findAll();
        int totalFiles = files.size();
        long totalSize = files.stream().mapToLong(FileMetadata::getFileSize).sum();
        return new FileSummary(totalFiles, totalSize);
    }
}
