package org.nandhu.learningmr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.nandhu.learningmr.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int getEmployeeCount()
	{
		System.out.println("Inside dao count");
		return jdbcTemplate.queryForObject("SELECT count(*) FROM EMPLOYEE", Integer.class).intValue();
	}
	
	public List<Employee> getAllEmployees()
	{
		return jdbcTemplate.query("SELECT * FROM EMPLOYEE", new EmployeeRowMapper());
	}
	
	public Employee getEmployeeById(int id)
	{
		return jdbcTemplate.queryForObject("SELECT * FROM EMPLOYEE WHERE ID=?", new Object[]{id},new EmployeeRowMapper());
	}
	
	public Employee insertEmployee(Employee e)
	{
		int i = jdbcTemplate.update("INSERT INTO EMPLOYEE (id, name, salary, dob) values(?,?,?,?)", new Object[]{e.getEmployeeId(), e.getEmployeeName(),e.getSalary(),e.getDob()});
		return e;
	}
	
	public static final class EmployeeRowMapper implements RowMapper<Employee>
	{

		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Employee e = new Employee();
			
			e.setEmployeeId(rs.getInt("ID"));
			e.setDob(rs.getDate("DOB"));
			e.setEmployeeName(rs.getString("NAME"));
			e.setSalary(rs.getString("SALARY"));
			
			return e;
		}
		
	}
}
