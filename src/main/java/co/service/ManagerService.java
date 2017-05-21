package co.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import co.entity.Manager;

public interface ManagerService {

	List<Manager> getManagers();

	Manager getManager(long id);

	Manager saveManager(Manager manager);

	void delete(Manager manager);

	UserDetails getManager(String username);

}
