package com.employees.employee.service.impl;

import com.employees.employee.dto.CustomResponsePagination;
import com.employees.employee.dto.EmployeResponseSize;
import com.employees.employee.dto.EmployeeDto;
import com.employees.employee.entity.Employee;
import com.employees.employee.mapper.EmployeeMapperDto;
import com.employees.employee.repositories.EmployeeRepository;
import com.employees.employee.service.EmployeeService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  @Override
  public EmployeeDto createsEmployee(EmployeeDto employeeDto) {
    Employee employee = EmployeeMapperDto.mapToEmployee(employeeDto);
    Employee savedEmployee = employeeRepository.save(employee);
    return EmployeeMapperDto.mapToEmployeeDto(savedEmployee);
  }

  @Override
  public EmployeeDto getEmployeeById(Long employeeId) {
    Employee employee = employeeRepository.getById(employeeId);
    return EmployeeMapperDto.mapToEmployeeDto(employee);
  }

  @Override
  public EmployeResponseSize getAllEmployees(String fieldName, Direction direction) {
    List<Employee> employees = employeeRepository.findAll(Sort.by(direction, fieldName));
    List<EmployeeDto> employeeDtos = employees.stream()
        .map(employee -> EmployeeMapperDto.mapToEmployeeDto(employee)).collect(Collectors.toList());
    return new EmployeResponseSize(employeeDtos.size(), employeeDtos);
  }

  /*@Override
  public EmployeResponseSize getAllEmployees() {
    List<Employee> employees = employeeRepository.findAll();
    List<EmployeeDto> employeeDtos = employees.stream()
        .map(employee -> EmployeeMapperDto.mapToEmployeeDto(employee)).collect(Collectors.toList());
    return new EmployeResponseSize(employeeDtos.size(), employeeDtos);
  } */

  @Override
  public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
    Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new RuntimeException("Could not find employee " + employeeId));
    employee.setFirstName(employeeDto.getFirstName());
    employee.setLastName(employeeDto.getLastName());
    employee.setEmail(employeeDto.getEmail());
    employee.setPhoneNumber(employeeDto.getPhoneNumber());
    employee.setAddress(employeeDto.getAddress());

    Employee updatedEmployee = employeeRepository.save(employee);

    return EmployeeMapperDto.mapToEmployeeDto(updatedEmployee);
  }

  @Override
  public void deleteEmployee(Long employeeId) {
    Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new RuntimeException("Could not find employee" + employeeId));
    employeeRepository.deleteById(employeeId);
  }

  @Override
  public CustomResponsePagination<EmployeeDto> getEmployeesWithPagination(Pageable pageable) {
    Page<Employee> employeesPage = employeeRepository.findAll(pageable);
    List<EmployeeDto> employeeDtos = employeesPage.map(EmployeeMapperDto::mapToEmployeeDto)
        .getContent();
    return new CustomResponsePagination<>(employeeDtos,
        employeesPage.getNumber(),  // pageNumber
        employeesPage.getSize(),    // pageSize
        employeesPage.getTotalElements(), // totalElements
        employeesPage.getTotalPages());  // totalPages)
  }
}
