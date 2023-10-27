package com.aquaboxs.poster.web.controller;

import com.aquaboxs.poster.exceptions.FileDownloadException;
import com.aquaboxs.poster.exceptions.FileEmptyException;
import com.aquaboxs.poster.exceptions.FileUploadException;
import com.aquaboxs.poster.service.FileService;
import com.aquaboxs.poster.service.PostService;
import com.aquaboxs.poster.web.APIResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/api")
@Validated
public class FileUploadController {
    private final FileService fileService;

    @Autowired
    private PostService postService;

    public FileUploadController(FileService fileUploadService) {
        this.fileService = fileUploadService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }


    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFile ,@RequestParam("msg") String msgText) throws FileEmptyException, FileUploadException, IOException {
        if (multipartFile.isEmpty()){
            throw new FileEmptyException("File is empty. Cannot save an empty file");
        }
        boolean isValidFile = isValidFile(multipartFile);
        List<String> allowedFileExtensions = new ArrayList<>(Arrays.asList("pdf", "txt", "epub", "csv", "png","PNG", "jpg","JPG","JPEG", "jpeg", "srt"));

        if (isValidFile && allowedFileExtensions.contains(FilenameUtils.getExtension(multipartFile.getOriginalFilename()))){

            String fileName = fileService.uploadFile(multipartFile);
            String uploadResult = postService.uploadPost(msgText, fileName);
            System.out.println(uploadResult);
            APIResponse apiResponse = APIResponse.builder()
                    .message("file uploaded successfully. File unique name =>" + fileName + "\nUpload Result =>" + uploadResult )
                    .isSuccessful(true)
                    .statusCode(200)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            APIResponse apiResponse = APIResponse.builder()
                    .message("Invalid File. File extension or File name is not supported")
                    .isSuccessful(false)
                    .statusCode(400)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }


    private boolean isValidFile(MultipartFile multipartFile){
        log.info("Empty Status ==> {}", multipartFile.isEmpty());
        if (Objects.isNull(multipartFile.getOriginalFilename())){
            return false;
        }
        return !multipartFile.getOriginalFilename().trim().equals("");
    }

    private String editFileName(String fileName){
        return "";
    }
}

