package com.aquaboxs.poster.service;

import com.aquaboxs.poster.exceptions.FileDownloadException;
import com.aquaboxs.poster.exceptions.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
public interface FileService {
    String uploadFile(MultipartFile multipartFile) throws FileUploadException, IOException;

    Object downloadFile(String fileName) throws FileDownloadException, IOException;

    boolean delete(String fileName);
}

