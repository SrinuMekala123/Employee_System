package com.prog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prog.entity.Employee;
import com.prog.repository.EmpRepo;
import com.prog.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
/* @RestController */
public class EmpController {
	@Autowired
	private EmpService service;

	@GetMapping("/")
	public String home(Model m) {
		/*
		 * List<Employee> emp = service.getAllEmp(); m.addAttribute("emp", emp);
		 * 
		 * return "index";
		 */

		 return findPaginated(0,m); 

	}

	@GetMapping("/addemp")
	public String addEmpForm() {
		return "add_emp";

	}

	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e, HttpSession session) {
		System.out.println(e);
		service.addEmp(e);
		session.setAttribute("msg", "Employee added successfully..");
		return "redirect:/";

	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model m) {

		Employee e = service.getEmpById(id);

		m.addAttribute("emp", e);
		return "edit";

	}

	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e, HttpSession session) {
		service.addEmp(e);
		session.setAttribute("msg", "Employeee Data Update Successfully..");
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id, HttpSession session) {

		service.deleteEmp(id);
		session.setAttribute("msg", "Employeee Data Delete Successfully..");
		return "redirect:/";

	}

	/* for pagination */
	@GetMapping("/pagination")
	public String getAllEmployees(Model model, @PageableDefault(size = 2) Pageable pageable) {
		Page<Employee> pagination = service.getAllEmployees(pageable);
		model.addAttribute("pagination", pagination);

		return "pagination";
	}

	/* 2nd pagination code */

	
	  @GetMapping("/page/{pageno}") public String findPaginated(@PathVariable int
	  pageno,Model m) {
	  
	  Page <Employee> emplist=service.getEmpByPaginate(pageno, 10);
	  m.addAttribute("emp",emplist); m.addAttribute("currentpage",pageno);
	  m.addAttribute("totalpages",emplist.getTotalPages());
	  m.addAttribute("totalItem",emplist.getTotalElements()); return "index";
	  
	  }
	 
	
	/* 3rd pagination code */
	/*
	 * @GetMapping("/employees") public Page<Employee>
	 * getAllEmployees(@RequestParam(defaultValue = "0") int page,
	 * 
	 * @RequestParam(defaultValue = "10") int size) { return
	 * service.getAllEmployees(page, size); }
	 */

}
