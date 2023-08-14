package com.nucsaping.exceptionhandler;

import com.nucsaping.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Controller
public class FileUploadExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseMessage> handleException(MaxUploadSizeExceededException e) {
        return new ResponseEntity<>(new ResponseMessage("File to large!"), HttpStatus.EXPECTATION_FAILED);
    }
}
