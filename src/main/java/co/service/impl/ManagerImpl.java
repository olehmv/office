package co.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.entity.Employee;
import co.entity.Manager;
import co.repository.EmployeeRepository;
import co.repository.ManagerRepository;
import co.service.ManagerService;
@Service
@Transactional
public class ManagerImpl implements ManagerService {
	@Autowired
	private ManagerRepository repository;
	@Autowired
	private EmployeeRepository emplRepository;
	@Override
	public List<Manager> getManagers() {
		return repository.findAll();
	}

	@Override
	public Manager getManager(long id) {
		return repository.findOne(id);
	}

	@Override
	public Manager saveManager(Manager manager) {
		for(Employee empl:manager.getList()){
			empl.setManager(manager);
		}
		return repository.save(manager);
	}

	@Override
	public void delete(Manager manager) {
		repository.delete(manager);
	}

	@Override
	public UserDetails getManager(String username) {
		return repository.findManagerByFirstName(username);
	}
	
}
