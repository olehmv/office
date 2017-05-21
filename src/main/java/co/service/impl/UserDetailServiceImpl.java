package co.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.entity.Department;
import co.entity.Employee;
import co.entity.Manager;
import co.service.EmployeeService;
import co.service.ManagerService;
@Service("userDetailsService")
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

	
	
	@Autowired
	private ManagerService managerService;
	@Autowired
	private EmployeeService employeeService;
	@PostConstruct
	public void populateDummyOffice() {
		Manager m = new Manager("ivan", "tuck", Department.CREDIT);
		Manager m1 = new Manager("petro", "colobok", Department.IT);
		Manager m2 = new Manager("maria", "club", Department.HR);
		m.getList().add(new Employee("jack", "black", m));
		m1.getList().add(new Employee("bob", "white", m1));
		m2.getList().add(new Employee("sveta", "rock", m2));
		managerService.saveManager(m);
		managerService.saveManager(m1);
		managerService.saveManager(m2);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return managerService.getManager(username);
	}
}
