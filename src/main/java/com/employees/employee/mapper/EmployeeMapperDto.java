package com.employees.employee.mapper;

import com.employees.employee.dto.EmployeeDto;
import com.employees.employee.entity.Employee;

public class EmployeeMapperDto {

  public static EmployeeDto mapToEmployeeDto(Employee employee) {
    return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(),
        employee.getEmail(), employee.getPhoneNumber(), employee.getAddress());
  }

  public static Employee mapToEmployee(EmployeeDto employeeDto) {
    return new Employee(employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getLastName(),
        employeeDto.getEmail(), employeeDto.getPhoneNumber(), employeeDto.getAddress());
  }
}
