package com.employees.employee.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponsePagination<T> {

  private List<T> employees;
  private int pageNumber;
  private int pageSize;
  private long totalElements;
  private int totalPages;
}
