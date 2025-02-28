package org.employee.employee_CRUD.Repository;

import org.employee.employee_CRUD.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

}
