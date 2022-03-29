package hu.webuni.hr.minta.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import hu.webuni.hr.minta.dto.CompanyDto;
import hu.webuni.hr.minta.dto.EmployeeDto;
import hu.webuni.hr.minta.dto.View;


@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private Map<Long, CompanyDto> companies = new HashMap<>();
	
	
	//1. megoldás: nem-full esetben lemásoljuk a companydto-t, de employees nélkül
//	@GetMapping
//	public List<CompanyDto> getAll(@RequestParam(required = false) Boolean full){
//		if(full == null || !full) {
//			return companies.values().stream()
//					.map(this::mapCompanyWithoutEmployees)
//					.collect(Collectors.toList());
//		} else {
//			return new ArrayList<>(companies.values());
//		}
//	}
	
	//2. megoldás: JsonView annotációval
	@GetMapping(params = "full=true")
	public List<CompanyDto> getAllFull(){
		return new ArrayList<>(companies.values());
	}
	

	@JsonView(View.BaseData.class)
	@GetMapping
	public List<CompanyDto> getAllNonFull(){
		return new ArrayList<>(companies.values());
	}
	
	

	private CompanyDto mapCompanyWithoutEmployees(CompanyDto c) {
		return new CompanyDto(c.getId(), c.getRegistrationNumber(), c.getName(), c.getAddress(), null);
	}
	
	
	@GetMapping("/{id}")
	public CompanyDto getById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
		CompanyDto fullCompany = getCompanyOrThrowNotFound(id);
		if(full == null || !full) {
			return mapCompanyWithoutEmployees(fullCompany);
		} else
			return fullCompany;
	}
	
	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		if(companies.containsKey(companyDto.getId()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		
		companies.put(companyDto.getId(), companyDto);
		return companyDto;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		if(!companies.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		
		companyDto.setId(id);
		companies.put(id, companyDto);
		return ResponseEntity.ok(companyDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companies.remove(id);
	}
	
	@PostMapping("/{id}/employees")
	public CompanyDto addEmployee(@PathVariable long id, @RequestBody EmployeeDto employee) {
		CompanyDto companyDto = getCompanyOrThrowNotFound(id);
		
		companyDto.getEmployees().add(employee);
		return companyDto;
	}


	private CompanyDto getCompanyOrThrowNotFound(long id) {
		CompanyDto companyDto = companies.get(id);
		if(companyDto == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return companyDto;
	}
	
	@DeleteMapping("/{id}/employees/{employeeId}")
	public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long employeeId) {
		CompanyDto companyDto = getCompanyOrThrowNotFound(id);
		
		companyDto.getEmployees().removeIf(e -> e.getId() == employeeId);
		return companyDto;
	}
	
	@PutMapping("/{id}/employees")
	public CompanyDto replaceAllEmployees(@PathVariable long id, @RequestBody List<EmployeeDto> employees) {
		CompanyDto companyDto = getCompanyOrThrowNotFound(id);
		
		companyDto.setEmployees(employees);
		return companyDto;
	}
	
	
}
