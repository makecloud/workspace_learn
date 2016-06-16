package com.liuyihui.webapp.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Conventions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liuyihui.webapp.entity.Course;
import com.liuyihui.webapp.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {
	private static Logger logger = LoggerFactory.getLogger(CourseController.class);
	private CourseService courseService;
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String viewCourse(@RequestParam("courseId")int courseId,Model model){
		Course c=courseService.getCourseById(courseId);
		model.addAttribute(c);
		return "view_course";
	}
	@RequestMapping(value="/view/{courseId}")
	public String viewCourse2(@PathVariable int courseId,Model model){
		Course c=courseService.getCourseById(courseId);
		model.addAttribute(c);
		System.out.println("查看："+Conventions.getVariableName(c));
		return "view_course";
	}
	@RequestMapping(value="/admin",method = RequestMethod.GET,params = "add")//�涨��һ���������add
	public String viewCourse3(){
		return "course_edit/edit";
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String doSave(Course course){
		
		logger.info("info of course:");
		logger.info(ReflectionToStringBuilder.toString(course));
		//�˴�ʵ��ҵ���߼�
		course.setCourseId(12);
		
		return "redirect:view/"+course.getCourseId();
	}
	@RequestMapping(value="/upload",method=RequestMethod.GET)
	public String initFileUploadPage(){
		return "upload/uploadpage";
	}
	@RequestMapping(value="/doUpload")
	public String doUpload(@RequestParam("file") MultipartFile file,Model model) throws IOException{
		if(!file.isEmpty()){
			logger.info("upload process:{}", file.getOriginalFilename());
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("C://TEMP//",System.currentTimeMillis()+file.getOriginalFilename()));
			model.addAttribute("success");
			return "/upload/upload_result";
		}else{
			model.addAttribute("fail");
			return "/upload/upload_result";
		}
	}
	@RequestMapping(value="/{courseId}",method=RequestMethod.GET)
	public @ResponseBody Course getCourseInJson(@PathVariable("courseId") int courseId){
		Course c=new Course();
		c.setCourseId(courseId);
		c.setCourseName("�й�");
		c.setLevel("8");
		return c;
	}
	@RequestMapping(value="/jsontype/{courseId}",method=RequestMethod.GET)
	public ResponseEntity<Course> getCourseInJson2(@PathVariable("courseId")int courseId){
		Course c=new Course();
		c.setCourseId(courseId);
		c.setCourseName("�ձ�");
		c.setLevel("7389274");
		c.setTitle("Japanes");
		return new ResponseEntity<Course>(c,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	@Autowired
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
}
