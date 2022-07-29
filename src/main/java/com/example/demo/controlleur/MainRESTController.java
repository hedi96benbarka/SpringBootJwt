package com.example.demo.controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.model.Employee;
import com.google.common.net.MediaType;

@RestController
public class MainRESTController {
	@Autowired
	private EmployeeDAO employeeDAO;

	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Welcome to Spring Boot + REST + JWT Example.";
	}

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "{greeting: 'Hello'}";
	}

	// URL:
	// http://localhost:8080/employees
	@RequestMapping(value = "/employees")
	@ResponseBody
	public List<Employee> getEmployees() {
		List<Employee> list = employeeDAO.getAllEmployees();
		return list;
	}

	// URL:
	// http://localhost:8080/employee/{empNo}
	@RequestMapping(value = "/employee/{empNo}", //
			method = RequestMethod.GET)
	@ResponseBody
	public Employee getEmployee(@PathVariable("empNo") String empNo) {
		return employeeDAO.getEmployee(empNo);
	}

	// URL:
	// http://localhost:8080/employee

	@RequestMapping(value = "/employee", //
			method = RequestMethod.POST)
	@ResponseBody
	public Employee addEmployee(@RequestBody Employee emp) {

		System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());

		return employeeDAO.addEmployee(emp);
	}

	// URL:
	// http://localhost:8080/employee
	@RequestMapping(value = "/employee", //
			method = RequestMethod.PUT)
	@ResponseBody
	public Employee updateEmployee(@RequestBody Employee emp) {

		System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());

		return employeeDAO.updateEmployee(emp);
	}

	// URL:
	// http://localhost:8080/employee/{empNo}
	@RequestMapping(value = "/employee/{empNo}", //
			method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteEmployee(@PathVariable("empNo") String empNo) {

		System.out.println("(Service Side) Deleting employee: " + empNo);

		employeeDAO.deleteEmployee(empNo);
	}

}