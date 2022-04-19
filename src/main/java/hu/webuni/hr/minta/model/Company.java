package hu.webuni.hr.minta.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Company {
	
	@Id
	@GeneratedValue
	private Long id;
	private int registrationNumber;
	private String name;
	private String address;
	
	@OneToMany(mappedBy = "company")
	@OrderBy("employeeId")
	private List<Employee> employees;
	
	@ManyToOne
	private CompanyType companyType;
	
	public Company() {
	}

	public Company(Long id, int registrationNumber, String name, String adress, List<Employee> employees) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = adress;
		this.employees = employees;
	}

	public int getRegistrationNumber() {
		return registrationNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRegistrationNumber(int registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public void addEmployee(Employee employee) {
		employee.setCompany(this);
		if(this.employees == null)
			this.employees = new ArrayList<>();
		this.employees.add(employee);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

        public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}	

}
