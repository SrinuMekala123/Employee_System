package com.prog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prog.entity.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer>{
	
	/* for pagination */
	
	static Page<Employee> findAllByOrderByRegistrationDateDesc(Pageable pageable) {
		// TODO Auto-generated method stub
		return null ;
}
}