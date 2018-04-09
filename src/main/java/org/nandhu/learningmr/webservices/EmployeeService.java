package org.nandhu.learningmr.webservices;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.nandhu.learningmr.dao.EmployeeDao;
import org.nandhu.learningmr.model.Employee;
import org.springframework.stereotype.Controller;

@Controller
@Path("/EmployeeService")
public class EmployeeService {
	
	@Inject
	private EmployeeDao dao ;
	
	public EmployeeService()
	{
		// To be declared when using spring.xml
		//AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		// ~= <context:annotation-config/> in spring.xml and register our configuration class
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoAppContext.class);
//		dao = ctx.getBean(EmployeeDao.class);
//		
		System.out.println("After setting up the context in constructor");
		//ctx.close();	
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Employee> getAllEmployees()
	{
		System.out.println("Inside get all efmp method");
		return dao.getAllEmployees();
	}
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Employee getEmployeeById(@PathParam("id") int id)
	{
		System.out.println("inside get by id method"+id);
		return dao.getEmployeeById(id);
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_XML)
	public String getEmpCount()
	{
		System.out.println("Inside getempcount() method");
		return Integer.toString(dao.getEmployeeCount());
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_XML)
	public String getTest()
	{
		System.out.println("Inside getempcount() method");
		return "Tested";
	}
	
	@POST
	@Path("/store")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Employee storeEmployeeDetails(Employee e) throws IOException
	{
		System.out.println("INSIDE POST " +  " Request: " + e.getSalary());
		dao.insertEmployee(e);
		return e;
	}

}
