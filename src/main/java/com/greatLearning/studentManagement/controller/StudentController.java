package com.greatLearning.studentManagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// add mapping for "/list"

	@RequestMapping("/list")
	public String listOfStudents(Model theModel) {

	
		List<Student> theStudents = studentService.findAll();

		
		theModel.addAttribute("Students", theStudents);

		return "list-Students";
	}
	
	

	@RequestMapping("/showFormForAdd")
	public String addForm(Model theModel) {

		// create model attribute to bind form data
		Student theStudent = new Student();

		theModel.addAttribute("Student", theStudent);

		return "Student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String updateForm(@RequestParam(name ="id") int theId, Model theModel) {
		System.out.println(theId);
		// get the Book from the service
		Student theStudent = studentService.findById(theId);
		System.out.println(theStudent);
		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Student", theStudent);

	
		return "Student-form";
	}

	@PostMapping("/save")
	public String saveStudentDetails(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course,
			@RequestParam("country") String country) {

		System.out.println(id);
		Student theStudent;
		if (id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setFirstName(firstName);
			theStudent.setLastName(lastName);
			theStudent.setCourse(course);
			theStudent.setCountry(country);
		} else
			theStudent = new Student(firstName, lastName, course, country);
				studentService.save(theStudent);

		
		return "redirect:/student/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int theId) {

		
		studentService.deleteById(theId);

		
		return "redirect:/student/list";

	}

}