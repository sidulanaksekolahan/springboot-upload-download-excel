package com.nucsaping.controller;

import com.nucsaping.model.Tutorial;
import com.nucsaping.response.ResponseMessage;
import com.nucsaping.service.TutorialService;
import com.nucsaping.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/tutorials")
public class TutorialController {

    private TutorialService tutorialService;

    @Autowired
    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadTutorials(@RequestParam MultipartFile file) {

        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                tutorialService.save(file);

                message = "Uploaded the file successfully : " + file.getOriginalFilename();

                return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
            } catch (Exception e) {
                message = "Could not upload the file : " + file.getOriginalFilename();
                return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.EXPECTATION_FAILED);
            }
        }

        message = "Please upload an excel file!";
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        try {
            List<Tutorial> tutorials = tutorialService.getAllTutorials();

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
