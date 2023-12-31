package com.nucsaping.service;

import com.nucsaping.model.Tutorial;
import com.nucsaping.repository.TutorialRepository;
import com.nucsaping.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class TutorialService {

    private TutorialRepository tutorialRepository;

    @Autowired
    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    public void save(MultipartFile file) {
        try {
            List<Tutorial> tutorials = ExcelHelper.excelTutorials(file.getInputStream());
            tutorialRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("Fail to store excel data " + e.getMessage());
        }
    }

    public List<Tutorial> getAllTutorials() {
        return tutorialRepository.findAll();
    }
}
