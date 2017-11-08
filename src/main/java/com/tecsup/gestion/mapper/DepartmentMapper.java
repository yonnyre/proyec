package com.tecsup.gestion.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tecsup.gestion.model.Department;

public class DepartmentMapper implements RowMapper<Department> {
	
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		Department dep = new Department();
		dep.setDepartment_id(rs.getInt("department_id"));
		dep.setName(rs.getString("name"));
		dep.setDesc(rs.getString("description"));
		dep.setCity(rs.getString("city"));

		return dep;
	}
}
