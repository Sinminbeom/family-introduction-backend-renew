package com.minbeom.familyintroductionbackendrenew.controller;

import com.minbeom.familyintroductionbackendrenew.google.GoogleDrive;
import com.minbeom.familyintroductionbackendrenew.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

@RestController
@Slf4j
@CrossOrigin(origins = "*") // 허용할 출처를 설정합니다.
public class FileController {

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadFile(@RequestParam MultipartFile uploadFile) throws IOException, GeneralSecurityException {
        String fileId = GoogleDrive.getInstance().uploadFile(uploadFile, uploadPath);

        String url = "https://drive.google.com/uc?export=view&id=" + fileId;

        Response response = new Response(200, url);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


}
