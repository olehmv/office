package co.api;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import co.entity.Employee;
import co.entity.Manager;
import co.exception.EmployeeNotFoundException;
import co.exception.ManagerListNotEmptyException;
import co.exception.ManagerNotFoundException;
import co.exception.error.ErrorInfo;
import co.service.EmployeeService;
import co.service.ManagerService;

@RestController
@RequestMapping(value = "/api")
public class OfficeRestController {
	@Autowired
	private ManagerService managerService;
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/managers", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Manager> showOffice() {
		List<Manager> list = managerService.getManagers();
		return list;
	}

	@RequestMapping(value = "/managers/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Manager showManager(@PathVariable long id) {
		Manager manager = managerService.getManager(id);
		if (manager == null) {
			throw new ManagerNotFoundException();
		}
		return manager;
	}

	@RequestMapping(value = "/managers", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Manager> createManager(@RequestBody Manager manager, UriComponentsBuilder builder) {
		Manager entity = managerService.saveManager(manager);
		HttpHeaders header = new HttpHeaders();
		URI location = builder.path("/api/managers/").path(String.valueOf(entity.getId())).build().toUri();
		header.setLocation(location);
		ResponseEntity<Manager> response = new ResponseEntity<Manager>(entity, header, HttpStatus.CREATED);
		return response;
	}

	@RequestMapping(value = "/managers/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteManager(@PathVariable long id)  {
		Manager manager = managerService.getManager(id);
		if (manager == null) {
			throw new ManagerNotFoundException();
		} else if(!manager.getList().isEmpty()){
			throw new ManagerListNotEmptyException();
		}	else {
		
			managerService.delete(manager);
		}
	}

	@RequestMapping(value = "/managers/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Manager updateManager(@PathVariable long id, @RequestBody Manager manager) {
		Manager entity = managerService.getManager(id);
		if (manager == null) {
			throw new ManagerNotFoundException();
		}
		entity.getList().addAll(manager.getList());
		entity.setFirstName(manager.getFirstName());
		entity.setLastName(manager.getLastName());
		entity.setDepartment(manager.getDepartment());
		entity = managerService.saveManager(entity);
		return entity;
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> showEmployees() {
		List<Employee> list = employeeService.getEmployees();
		return list;
	}

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee getEmployee(@PathVariable long id) {
		Employee entity = employeeService.getEmployee(id);
		if (entity == null) {
			throw new EmployeeNotFoundException();
		}
		return entity;
	}

	@RequestMapping(value = "/employees", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee empl,
			UriComponentsBuilder builder) {
		Employee entity = employeeService.saveEmployee(empl);
		HttpHeaders header = new HttpHeaders();
		URI location = builder.path("/api/employees/").path(String.valueOf(entity.getId())).build().toUri();
		System.err.println(location.toString());
		header.setLocation(location);
		ResponseEntity<Employee> response = new ResponseEntity<>(entity, header, HttpStatus.CREATED);
		return response;
	}

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee updateEmployee(@PathVariable long id, @RequestBody Employee empl) {
		System.out.println("-----------------------------------------------------------------");
		Employee entity = employeeService.getEmployee(id);
		if (entity == null) {
			throw new EmployeeNotFoundException();
		}
		entity.setFirstName(empl.getFirstName());
		entity.setLastName(empl.getLastName());
		entity.setManager(empl.getManager());
		return employeeService.saveEmployee(entity);
	}

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteEmployee(@PathVariable long id) {
		Employee empl = employeeService.getEmployee(id);
		if (empl == null) {
			throw new EmployeeNotFoundException();
		} else {
			employeeService.deleteEmployee(id);
		}
	}
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ManagerNotFoundException.class)
	ErrorInfo handleBadRequestEmployee(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL(), ex);
	}
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmployeeNotFoundException.class)
	ErrorInfo handleBadRequestManager(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL(), ex);
	}
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ManagerListNotEmptyException.class)
	ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL(), ex);
	}

}
