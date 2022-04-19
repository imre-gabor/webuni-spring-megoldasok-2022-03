package hu.webuni.hr.minta.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.minta.dto.CompanyDto;
import hu.webuni.hr.minta.dto.EmployeeDto;
import hu.webuni.hr.minta.model.Company;
import hu.webuni.hr.minta.model.Employee;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
	List<CompanyDto> companiesToDtos(List<Company> companies);

	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companiesToDtosNoEmployees(List<Company> companies);

	CompanyDto companyToDto(Company company);

	@Mapping(target = "employees", ignore = true)
	@Named("summary")
	CompanyDto companyToDtoNoEmployees(Company company);

	Company dtoToCompany(CompanyDto companyDto);

	List<Company> dtosToCompanies(List<CompanyDto> companies);
	
	@Mapping(source = "employeeId", target = "id")
	@Mapping(source = "position.name", target = "title")
	@Mapping(source = "dateOfStartWork", target = "entryDate")
	@Mapping(target = "company", ignore = true)
	EmployeeDto employeeToDto(Employee employee);

	@InheritInverseConfiguration
	Employee dtoToEmployee(EmployeeDto employeeDto);

}
