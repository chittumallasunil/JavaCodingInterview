package com.example.demo.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootApplication
@RestController
public class FileUploadMicroservice {

    private static final String UPLOAD_DIR = "C:/uploads"; // Directory where files will be uploaded

    public static void main(String[] args) {
        SpringApplication.run(FileUploadMicroservice.class, args);
    }

    @PostMapping("/upload")
    public String uploadFile(@PathVariable("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }

        try {
            // Create the directory if it doesn't exist
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Get the file and save it to the upload directory
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            File targetFile = new File(UPLOAD_DIR + "/" + fileName);
            FileOutputStream outputStream = new FileOutputStream(targetFile);
            outputStream.write(file.getBytes());
            outputStream.close();

            return "File uploaded successfully: " + fileName;
        } catch (IOException e) {
            return "Failed to upload file: " + e.getMessage();
        }
    }
}
