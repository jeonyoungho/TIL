package com.example.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AcademyServiceTest {


    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private AcademyService academyService;

    @BeforeEach
    void setUp() {
        List<Academy> academies = new ArrayList<>();

        for(int i=0;i<10;i++) {
            Academy academy = Academy.builder()
                    .name("강남스쿨" + i)
                    .build();

            academy.addSubject(Subject.builder().name("자바웹개발" + i).build());
            academies.add(academy);
        }

        academyRepository.saveAll(academies);
    }

    @AfterEach
    void cleanAll() {
        academyRepository.deleteAll();
    }

    @Test
    public void Academy여러개를_조회시_Subject가_N1_쿼리가발생한다() throws Exception {
        //given
        List<String> subjectNames = academyService.findAllSubjectName();

        //then
        assertThat(subjectNames.size(), is(10));
    }

    @Test
    public void Academy여러개를_joinFetch로_가져온다() throws Exception {
        //given
        List<Academy> academies = academyRepository.findAllJoinFetch();
        List<String> subjectNames = academyService.findAllSubjectNamesByJoinFetch();

        //then
        assertThat(academies.size(), is(20)); // 20개가 조회!?
        assertThat(subjectNames.size(), is(20)); // 20개가 조회!?
    }
}