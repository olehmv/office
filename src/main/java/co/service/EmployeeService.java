package co.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.entity.Employee;

public interface EmployeeService {
	public void deleteEmployee(long id);
	List<Employee> getEmployees();

	Employee getEmployee(long id);

	Employee saveEmployee(Employee empl);


}
