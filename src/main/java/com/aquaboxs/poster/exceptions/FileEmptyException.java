package com.aquaboxs.poster.exceptions;

public class FileEmptyException extends SpringBootFileUploadException {
    public FileEmptyException(String message) {
        super(message);
    }
}
