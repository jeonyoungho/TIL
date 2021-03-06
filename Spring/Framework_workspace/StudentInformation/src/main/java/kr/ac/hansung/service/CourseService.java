package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.CourseDao;
import kr.ac.hansung.model.Course;

@Service
public class CourseService {
	
	@Autowired
	private CourseDao takedCourseDao;
	
	public List<Course> getTakedCurrent(){
		return takedCourseDao.getTakedCourse();
	}

	public List<Course> getApplyedCurrent(){
		return takedCourseDao.getApplyedCourse();
	}
	
	public void insert(Course course) {
		takedCourseDao.insert(course);
		
	}
}
