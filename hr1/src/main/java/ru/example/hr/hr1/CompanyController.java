package ru.example.hr.hr1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class CompanyController {
  private CompanyService service;

  @GetMapping("/companies/{id}/median")
  public Map<Job, Double> getMedianSalariesByJobInCompany(@PathVariable("id")UUID id) {
    return service.getMedianByJobInDepartmentById(id);
  }
}
