package com.tecsup.gestion.services;

import java.util.List;

import com.tecsup.gestion.exception.DAOException;
import com.tecsup.gestion.exception.EmptyResultException;
import com.tecsup.gestion.model.Department;
import com.tecsup.gestion.model.Employee;

public interface EmployeeService {
	
	Employee find(int employee_id) throws DAOException, EmptyResultException;

	List<Employee> findAll() 
			throws DAOException, EmptyResultException;
	
	void create(String login, String password, String lastname, String firstname, int salary, int deptId) throws DAOException;
	
	void update(String login, String password, String lastname, String firstname, int salary, int dptId)
			throws DAOException;
	
	void delete(String login)
			throws DAOException;
	
	
	
	Department findDep(int department_id) throws DAOException, EmptyResultException;
	
	List<Department> findAllDep() 
			throws DAOException, EmptyResultException;
	
	void create(String name, String desc, String city) throws DAOException;
	
	void updateDep(String name, String desc, String city)
			throws DAOException;
	
	void deleteDep(String name)
			throws DAOException;
	
}
