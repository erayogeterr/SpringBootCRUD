package com.example.demo.Business.Concrete;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Business.Abstract.EmployeeService;
import com.example.demo.Entity.Employee;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.EmployeeRepository;

@Service
public class EmployeeManager implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	public EmployeeManager(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}
		else {
			throw new ResourceNotFoundException("Employee","Id", id); 
		}
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		employeeRepository.save(existingEmployee);
		return existingEmployee;
		
	}

	@Override
	public void deleteEmployee(long id) {
		employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
	    this.employeeRepository.deleteById(id);
	}
}

