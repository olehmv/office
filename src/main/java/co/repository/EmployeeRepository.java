package co.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
