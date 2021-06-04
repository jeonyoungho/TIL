package com.example.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AcademyService {

    private AcademyRepository academyRepository;

    public AcademyService(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    @Transactional
    public List<Academy> findAll() {
        List<Academy> academies = academyRepository.findAll();
        return academies;
    }

    @Transactional(readOnly = true)
    public List<String> findAllSubjectName() {
        //        List<Academy> academies = academyRepository.findAll();
        List<Academy> academies = academyRepository.findAllEntityGraph();
        return extractSubjectNames(academies);
    }

    @Transactional(readOnly = true)
    public List<String> findAllSubjectNamesByJoinFetch() {
        List<Academy> academies = academyRepository.findAllJoinFetch();
        return extractSubjectNames(academies);
    }

    @Transactional(readOnly = true)
    public List<String> findAllSubjectNamesByEntityGraph() {
        return extractSubjectNames(academyRepository.findAllEntityGraph());
    }

    @Transactional(readOnly = true)
    public List<String> findAllSubjectNamesByJoinFetchDistinct(){
        return extractSubjectNames(academyRepository.findAllJoinFetchDistinct());
    }

    @Transactional(readOnly = true)
    public List<String> findAllSubjectNamesByEntityGraphDistinct() {
        return extractSubjectNames(academyRepository.findAllEntityGraphDistinct());
    }

    private List<String> extractSubjectNames(List<Academy> academies) {

        System.out.println(">>>>>>>>>>>> [모든 과목을 추출한다] <<<<<<<");
        System.out.println("Academy Size: " + academies.size());

        List<String> subjectNames = new ArrayList<>();
        System.out.println("===========================================");
        for(Academy academy:academies) {
            String subjectName = academy.getSubjects().get(0).getName();
            subjectNames.add(subjectName);
        }
        System.out.println("===========================================");

        return subjectNames;
    }


}
