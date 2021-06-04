package com.example.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AcademyController {

    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private AcademyService academyService;

    @GetMapping("/academies")
    public List<Academy> getAcademies() {
        List<Academy> academies = academyService.findAll();
        System.out.println();
        return academies;
    }
}
