package com.example.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AcademyService {

    private AcademyRepository academyRepository;

    public AcademyService(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    @Transactional(readOnly = true)
    public List<String> findAllSubjectName() {
        List<Academy> academies = academyRepository.findAll();
        return extractSubjectNames(academies);
    }

    private List<String> extractSubjectNames(List<Academy> academies) {

        log.info(">>>>>>>>>>>> [모든 괌고을 추출한다] <<<<<<<");
        log.info("Academy Size: {}", academies.size());

        List<String> subjectNames = new ArrayList<>();
        for(Academy academy:academies) {
            String subjectName = academy.getSubjects().get(0).getName();
            subjectNames.add(subjectName);
        }

        return subjectNames;
    }


}
