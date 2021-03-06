package kr.ac.hansung.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.model.Course;
import kr.ac.hansung.service.CourseService;

@Controller
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("/takedcourse")
	public String takedCourse(Model model) {
		List<Course> takedCourse = courseService.getTakedCurrent(); //학기별 누적 이수학점을 리스트형식으로 리턴
		model.addAttribute("takedcourse", takedCourse);
		
		return "takedcourse";
	}
	
	@RequestMapping("/applycourse")
	public String applyCourse(Model model) {
		Course course = new Course();
		
		//applycourse.jsp에 전달할 course모델에 연도와 학기를 지정 (고정값이므로)  
		course.setYear(2020);
		course.setSemester(1);
		
		model.addAttribute("course",course);
		
		return "applycourse";
	}
	
	@RequestMapping("/doapply")
	public String doApply(Model model,@Valid Course course, BindingResult result) { //스프링에의해 자동적으로 바인딩되서 착착 들어간다
		//offer 자체가 model에도 들어가서 controller에 넘어감 , Valid annotation을 달아주면 스프링이 검증해서 결과를 BindingResult 객체에 넣어줌
		if(result.hasErrors()) { //BindingResult객체에 에러가 있을시에 에러출력 로직
			System.out.println("=== Web Form data does not validated ===");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "applycourse";
		}
		
		/*applyCourse에서 보냈던 모델과 다른모델이므로 연도와 학기를 다시 등록해줘야함*/
		course.setYear(2020);
		course.setSemester(1);
		
		courseService.insert(course); //controller -> service -> dao
		
		return "applyedsuccess";
	}
	
	@RequestMapping("/applyedcourse")
	public String applyedCourse(Model model) {
		List<Course> applyedCourse = courseService.getApplyedCurrent();//수강 신청내역을 리스트형식으로 리턴
		model.addAttribute("applyedcourse", applyedCourse);
		
		return "applyedcourse";
	}
	
}
