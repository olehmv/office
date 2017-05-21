package co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import co.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager,Long> {

	Manager findManagerByFirstName(String username);

}
