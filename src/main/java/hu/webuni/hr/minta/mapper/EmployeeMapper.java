package hu.webuni.hr.minta.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.minta.dto.EmployeeDto;
import hu.webuni.hr.minta.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	
	List<EmployeeDto> employeesToDtos(List<Employee> employees);

	@Mapping(source = "employeeId", target = "id")
	@Mapping(source = "position.name", target = "title")
	@Mapping(source = "dateOfStartWork", target = "entryDate")
	@Mapping(target = "company.employees", ignore = true)
	EmployeeDto employeeToDto(Employee employee);

	@InheritInverseConfiguration
	Employee dtoToEmployee(EmployeeDto employeeDto);

	List<Employee> dtosToEmployees(List<EmployeeDto> employees);

}
