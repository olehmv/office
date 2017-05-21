package co.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.entity.Employee;
import co.repository.EmployeeRepository;
import co.service.EmployeeService;
@Service
@Transactional
public class EmpoyeeImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository repository;

	@Override
	public List<Employee> getEmployees() {
		return repository.findAll();
	}

	@Override
	public Employee getEmployee(long id) {
		return repository.findOne(id);
	}

	@Override
	public Employee saveEmployee(Employee empl) {
		return repository.save(empl);
	}
	public void deleteEmployee(long id){
		repository.delete(id);;
	}
}
