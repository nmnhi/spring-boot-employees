package com.employees.employee.controller;

import com.employees.employee.dto.CustomResponsePagination;
import com.employees.employee.dto.EmployeResponseSize;
import com.employees.employee.dto.EmployeeDto;
import com.employees.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  // REST API create new Employee
  @PostMapping
  public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
    EmployeeDto employee = employeeService.createsEmployee(employeeDto);
    return new ResponseEntity<>(employee, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
    EmployeeDto employeeDto = employeeService.getEmployeeById(id);
    return new ResponseEntity<>(employeeDto, HttpStatus.OK);
  }

  @GetMapping()
  public ResponseEntity<EmployeResponseSize> getAllEmployees(
      @RequestParam(defaultValue = "id") String fieldName,
      @RequestParam(defaultValue = "ASC") String direction) {
    Sort.Direction sortDirection = Sort.Direction.fromString(direction);
    EmployeResponseSize employees = employeeService.getAllEmployees(fieldName, sortDirection);
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,
      @RequestBody EmployeeDto employee) {
    EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employee);
    return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
    employeeService.deleteEmployee(id);
    return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
  }

  @GetMapping("/page")
  public ResponseEntity<CustomResponsePagination> getAllEmployeesWithPage(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "id") String sortBy,
      @RequestParam(defaultValue = "ASC") String sortDirection) {

    // Convert sortDirection to Sort.Direction
    Sort.Direction direction = Sort.Direction.fromString(sortDirection.toUpperCase());

    // Create a Pageable object with the page number, page size, and sort direction
    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

    // Wrap the Page<EmployeeDto> into the CustomPageResponse
    CustomResponsePagination<EmployeeDto> response = employeeService.getEmployeesWithPagination(
        pageable);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
