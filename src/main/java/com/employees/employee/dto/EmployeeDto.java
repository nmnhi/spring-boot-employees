package com.employees.employee.dto;


import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

  private UUID id;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String address;
}
