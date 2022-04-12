package hu.webuni.hr.minta.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.minta.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	List<Employee> findBySalaryGreaterThan(Integer minSalary);

	List<Employee> findByJobTitle(String jobTitle);
	List<Employee> findByNameStartingWithIgnoreCase(String name);
	List<Employee> findByDateOfStartWorkBetween(LocalDateTime start, LocalDateTime end);
	
	
}
