package hu.webuni.hr.minta.web;

import java.util.List;

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

import hu.webuni.hr.minta.dto.CompanyDto;
import hu.webuni.hr.minta.dto.EmployeeDto;
import hu.webuni.hr.minta.model.Company;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	// 1. megoldás
	@GetMapping
	public List<CompanyDto> getAll(@RequestParam(required = false) Boolean full) {
		return null;

//		if(isFull(full)) {
//			return new ArrayList<>(companies.values());
//		} else {
//			return companies.values().stream()
//					.map(this::mapCompanyWithoutEmployees)
//					.collect(Collectors.toList());
//		}
	}

	// 2. megoldás: JsonView annotációval
//	@GetMapping(params = "full=true")
//	public List<CompanyDto> getAllFull() {
//		return new ArrayList<>(companies.values());
//	}

	private boolean isFull(Boolean full) {
		return full != null && full;
	}

//	@JsonView(View.BaseData.class)
//	@GetMapping
//	public List<CompanyDto> getAllNonFull() {
//		return new ArrayList<>(companies.values());
//	}

	private CompanyDto mapCompanyWithoutEmployees(CompanyDto c) {
		return new CompanyDto(c.getId(), c.getRegistrationNumber(), c.getName(), c.getAddress(), null);
	}

	@GetMapping("/{id}")
	public CompanyDto getById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
		return null;
//		CompanyDto companyDto = companies.get(id);
//		if(isFull(full))
//			return companyDto;
//		else
//			return mapCompanyWithoutEmployees(companyDto);
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		return null;
//      return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		return null;
//      companyDto.setId(id);
//      Company updatedCompany = companyService.update(companyMapper.dtoToCompany(companyDto));
//      if (updatedCompany == null) {
//          return ResponseEntity.notFound().build();
//      }
//
//      return ResponseEntity.ok(companyMapper.companyToDto(updatedCompany));
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
//      companyService.delete(id);
	}

//	private CompanyDto findByIdOrThrow(long id) {
//		CompanyDto company = companies.get(id);
//		if (company == null)
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		return company;
//	}
	
	@PostMapping("/{companyId}/employees")
	public CompanyDto addNewEmployee(@PathVariable long companyId, @RequestBody EmployeeDto employeeDto) {
		return null;
//      try {
//      return companyMapper.companyToDto(
//              companyService.addEmployee(id, employeeMapper.dtoToEmployee(employeeDto))
//              );
//  } catch (NoSuchElementException e) {
//      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//  }
	}



	@DeleteMapping("/{companyId}/employees/{empId}")
	public CompanyDto deleteEmployee(@PathVariable long companyId, @PathVariable long empId) {
		return null;
//      try {
//      return companyMapper.companyToDto(
//              companyService.deleteEmployee(id, employeeId)
//              );
//  } catch (NoSuchElementException e) {
//      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//  }
	}

	@PutMapping("/{companyId}/employees")
	public CompanyDto replaceEmployees(@PathVariable long companyId, @RequestBody List<EmployeeDto> newEmployees) {
		return null;
//      try {
//          return companyMapper.companyToDto(
//                  companyService.replaceEmployees(id, employeeMapper.dtosToEmployees(employees))
//                  );
//      } catch (NoSuchElementException e) {
//          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//      }
	}
}
