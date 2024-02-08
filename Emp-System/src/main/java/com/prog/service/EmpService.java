package com.prog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prog.entity.Employee;
import com.prog.repository.EmpRepo;

@Service
public class EmpService {
	
	@Autowired
	private EmpRepo repo;
	public void addEmp(Employee e) {
		
		repo.save(e);
	}

	public List<Employee> getAllEmp(){
		return repo.findAll();
		
	}
	public Employee getEmpById(int id) {
		Optional<Employee> e= repo.findById(id);
		if(e.isPresent()) {
			return e.get();
		}
		return null;
	}
	public void deleteEmp(int id) {
		repo.deleteById(id);
	}
	/* for pagination */
	public Page<Employee> getAllEmployees(Pageable pageable) {
        return repo.findAll(pageable);
    }
	
	/* 2nd pagination code */
	
	
	  public Page<Employee> getEmpByPaginate(int currentpage,int size){ 
		  Pageable p=PageRequest.of(currentpage, size);
	  
	  return repo.findAll(p);
	  }
	  /* 3rd pagination code */
	  public Page<Employee> getAllEmployees(int pageNumber, int pageSize) {
	        Pageable pageable = PageRequest.of(pageNumber, pageSize);
	        return repo.findAll(pageable);
	    }
	 
}
