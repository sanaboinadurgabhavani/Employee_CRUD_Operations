package org.employee.employee_CRUD.Controller;

import java.util.List;
import java.util.Optional;

import org.employee.employee_CRUD.Dao.EmployeeDao;
import org.employee.employee_CRUD.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees") 
public class EmployeeController {
	
	@Autowired
	private EmployeeDao empDao;
	
	@PostMapping("/save")
    public String saveEmployee(@RequestBody Employee employee) {
        empDao.saveEmployee(employee);
        return "Employee saved successfully!";
    }
	
		@PostMapping("/saveAll")
	    public String saveAllEmployees(@RequestBody List<Employee> employees) {
	        empDao.saveEmployeeAll(employees);
	        return "All employees saved successfully!";
	    }
		
		 // Get Employee by ID
	    @GetMapping("/find/{id}")
	    public Optional<Object> findById(@PathVariable int id) {
	        Optional<Employee> employee = empDao.findById(id);
	        return employee.map(ResponseEntity::ok);
	    }

	    // Get All Employees
	    @GetMapping("/findAll")
	    public ResponseEntity<List<Employee>> findAll() {
	        List<Employee> employees = empDao.findAll();
	        return ResponseEntity.ok(employees);
	    }

	    // Get Employee Count
	    @GetMapping("/count")
	    public ResponseEntity<String> getEmployeeCount() {
	        long count = empDao.count();
	        return ResponseEntity.ok("Total number of employees: " + count);
	    }

	    // Delete Employee by ID
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) {
	        try {
	            empDao.deleteById(id);
	            return ResponseEntity.ok("Employee with ID " + id + " deleted successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("Employee with ID " + id + " not found.");
	        }
	    }

	    // Delete All Employees
	    @DeleteMapping("/deleteAll")
	    public ResponseEntity<String> deleteAllEmployees() {
	        empDao.deleteAll();
	        return ResponseEntity.ok("All employees deleted successfully.");
	    }

	    // Update Employee by ID
	    @PutMapping("/update/{id}")
	    public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee emp) {
	        try {
	            empDao.update(id, emp);
	            return ResponseEntity.ok("Employee with ID " + id + " updated successfully.");
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body(e.getMessage());
	        }
	    }
	}
		
		

