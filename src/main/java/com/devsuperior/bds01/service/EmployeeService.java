package com.devsuperior.bds01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repository.DepartmentRepository;
import com.devsuperior.bds01.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository emprep;

	@Autowired
	DepartmentRepository deprep;
	
	@Transactional
	public EmployeeDTO insere(EmployeeDTO empdto) {
		Employee emp = new Employee();	
		Department dep = new Department();		
		dep = deprep.getOne(empdto.getDepartmentId());
		
		emp.setDepartment(dep);
		emp.setEmail(empdto.getEmail());
		emp.setName(empdto.getName());
		emprep.save(emp);
		return new EmployeeDTO(emp);		
	}
	
	@Transactional 
	public Page<EmployeeDTO> findAllPaged(Pageable pageable){
		
		Page<Employee> lista = emprep.findAll(pageable);

				
		return lista.map(x -> new EmployeeDTO(x)); 
	}
	

}
