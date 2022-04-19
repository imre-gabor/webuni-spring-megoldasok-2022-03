package hu.webuni.hr.minta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.minta.model.Company;
import hu.webuni.hr.minta.model.Employee;
import hu.webuni.hr.minta.model.Position;
import hu.webuni.hr.minta.repository.CompanyRepository;
import hu.webuni.hr.minta.repository.EmployeeRepository;
import hu.webuni.hr.minta.repository.PositionRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
	public Company save(Company company) {
		return companyRepository.save(company);
	}

	public Company update(Company company) {
		if(!companyRepository.existsById(company.getId()))
			return null;
		return companyRepository.save(company);
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Optional<Company> findById(long id) {
		return companyRepository.findById(id);
	}

	public void delete(long id) {
		companyRepository.deleteById(id);
	}
	
	public Company addEmployee(long id, Employee employee) {
		Company company = companyRepository.findById(id).get();
		company.addEmployee(employee);
		Position transientPosition = employee.getPosition();
		if(transientPosition != null) {
			List<Position> positionsByName = positionRepository.findByName(transientPosition.getName());
			if(positionsByName.isEmpty())
				throw new RuntimeException("position with this name does not exist in DB");
			
			Position positionInDb = positionsByName.get(0);
			employee.setPosition(positionInDb);
		}
		employeeRepository.save(employee);
		return company;
	}
	
	public Company deleteEmployee(long id, long employeeId) {
		Company company = companyRepository.findById(id).get();
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		employeeRepository.save(employee);
		return company;
	}
	
	public Company replaceEmployees(long id, List<Employee> employees) {
		Company company = companyRepository.findById(id).get();
		for (Employee employee : company.getEmployees()) {
			employee.setCompany(null);
		}
		company.getEmployees().clear();
		
		for (Employee employee : employees) {
			company.addEmployee(employee);
			Employee savedEmployee = employeeRepository.save(employee);
			employee.setEmployeeId(savedEmployee.getEmployeeId());
		}
	
		return company;
	}
	
}
