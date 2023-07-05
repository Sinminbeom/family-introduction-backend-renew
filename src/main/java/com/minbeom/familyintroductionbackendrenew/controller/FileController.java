package com.minbeom.familyintroductionbackendrenew.controller;

import com.minbeom.familyintroductionbackendrenew.google.GoogleDrive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@Slf4j
public class FileController {

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam MultipartFile uploadFiles) throws IOException, GeneralSecurityException {
        String fileId = GoogleDrive.getInstance().uploadFile(uploadFiles, uploadPath);
        return "https://drive.google.com/uc?export=view&id=" + fileId;
    }


}
