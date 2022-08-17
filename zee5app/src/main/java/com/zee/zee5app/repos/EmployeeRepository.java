package com.zee.zee5app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	  List<Employee> findByDept(String deptName);
	  List<Employee> findBySalaryGreaterThan(int salary);
	  List<Employee> findBySalaryGreaterThanEqual(int salary);
	  List<Employee> findByDeptAndSalaryLessThan(String deptName, int salary);
	  List<Employee> findBySalary(int salary);
}
