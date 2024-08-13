package com.employees.employee.service;

import com.employees.employee.dto.CustomResponsePagination;
import com.employees.employee.dto.EmployeResponseSize;
import com.employees.employee.dto.EmployeeDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface EmployeeService {

  EmployeeDto createsEmployee(EmployeeDto employeeDto);

  EmployeeDto getEmployeeById(Long employeeId);

  EmployeResponseSize getAllEmployees(String sortBy, Sort.Direction direction);

  EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);

  void deleteEmployee(Long employeeId);

  CustomResponsePagination<EmployeeDto> getEmployeesWithPagination(Pageable pageable);
}
 
