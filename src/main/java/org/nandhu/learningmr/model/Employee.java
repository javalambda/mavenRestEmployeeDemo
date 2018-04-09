package org.nandhu.learningmr.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
public class Employee implements Serializable{
	
	private int employeeId;
	
	private String employeeName;
	
	@JsonIgnore
	private String salary;
	
	private Date dob;

	public Employee()
	{}
	
	public Employee(int id, String employeeName, String salary, Date dob)
	{
		this.employeeId = id;
		this.employeeName = employeeName;
		this.salary = salary;
		this.dob= dob;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	

}
