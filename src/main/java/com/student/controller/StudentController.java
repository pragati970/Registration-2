package com.student.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.student.service.entity.Login;
import com.student.service.entity.Student;
import com.student.service.service.StudentService;
import com.student.service.service.UserService;

@Controller
public class StudentController {
	@Autowired
	private UserService userservice;
	@Autowired
	private StudentService studentService;
	private Long id;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
		
	}
	
	@PostMapping("/students")
	public String saveStudent(@Valid @ModelAttribute ("student") Student student,BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
            return "create_student";
        }

        studentService.saveStudent(student);
        return "redirect:/students";
		
		
		
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "edit_student";
        }
		
		// get student from database by id
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		
		// save updated student object
		studentService.updateStudent(existingStudent);
		return "redirect:/students";
	}
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}
	
	
	
	
	//Login
	
	
	
	  @RequestMapping("/login")
	    public String loginForm() {
	        return "login";
	    }

	    @RequestMapping("/signup")
	    public String signupForm(Model model) {
	        model.addAttribute("user", new Login());
	        return "signup";
	    }

	    @PostMapping("/signup")
	    public String signup(@ModelAttribute Login user) {
	        userservice.registerNewUser(user);
	        return "redirect:/login";
	    }
	    @PostMapping("/login")
	    public String Login(@ModelAttribute Login user) {
	        userservice.registerNewUser(user);
	        return "redirect:/students/new";
	    }
	   

}
