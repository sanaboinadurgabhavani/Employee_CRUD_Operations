package org.employee.employee_CRUD.Dao;

import java.util.List;
import java.util.Optional;

import org.employee.employee_CRUD.Repository.EmployeeRepository;
import org.employee.employee_CRUD.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
	
	@Autowired
	private EmployeeRepository repo;
	
//	<----------------Save Data--------------->
	public void saveEmployee(Employee emp) {
		repo.save(emp);
	}
	
	public void saveEmployeeAll(Iterable<Employee> emps) {
		repo.saveAll(emps);
	}
//	<----------------Read Data---------------->
	 public Optional<Employee> findById(int id) {
		return repo.findById(id);
	 }
	  public List<Employee> findAll() {
		  return repo.findAll();
	  }
	  public long count() {
		 return repo.count();
	  }
//	  <--------------delete Data------------------>
	  public void deleteById(int id) {
		  repo.deleteById(id);
	  }
	   public void deleteAll() {
		   repo.deleteAll();
	   }
//	   <--------------Update data--------------->
	   public void update(int id,Employee emp) {
		   if(repo.existsById(id)) {
			   Employee existingEmployee = repo.findById(id).orElse(null);
		        
		        if (existingEmployee != null) {
		            existingEmployee.setEmp_name(emp.getEmp_name());
		            existingEmployee.setEmp_email(emp.getEmp_email());
		            existingEmployee.setEmp_password(emp.getEmp_password());
		            existingEmployee.setMobileno(emp.getMobileno());

		            repo.save(existingEmployee); // Save the updated employee
		        }
		    } else {
		        throw new RuntimeException("Employee with ID " + id + " not found.");
		   }
	   }
	   
}
