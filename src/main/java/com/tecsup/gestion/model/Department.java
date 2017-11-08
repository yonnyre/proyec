package com.tecsup.gestion.model;

public class Department {
	
	int department_id;
	String name;
	String desc;
	String city;
	
	
	public Department(int department_id, String name, String desc, String city) {
		super();
		this.department_id = department_id;
		this.name = name;
		this.desc = desc;
		this.city = city;
	}
	
	public Department() {
		super();
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Deparment [department_id=" + department_id + ", name=" + name + ", desc=" + desc + ", city=" + city
				+ "]";
	}
	
}
