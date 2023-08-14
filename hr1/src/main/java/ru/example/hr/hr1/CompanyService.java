package ru.example.hr.hr1;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {
  private CompanyRepository repository;
  private DepartmentService departmentService;

  public List<Department> findSameDepartmentsWithNameInOtherCompanies(Department d1) {
    return repository.getOtherCompanies(d1.getCompany().getId()).stream()
        .map(Company::getDepartments)
        .flatMap(Collection::stream)
        .filter(department -> d1.getName().equals(department.getName()))
        .collect(Collectors.toList());
  }

  public Map<Job, Double> getMedianByJobInDepartmentById(UUID id) {
    Company company = repository.findById(id).orElseThrow(EntityNotFoundException::new);

    Map<Job, List<Double>> jobsSalaries = new HashMap<>();

    company.getDepartments().stream()
        .map(department -> departmentService.getSalariesByJobInDepartment(department))
        .forEach(jobListMap ->
            jobListMap.forEach((k, v) ->
                jobsSalaries.merge(
                    k,
                    v,
                    (currentList, toAdd) -> {
                      currentList.addAll(toAdd);
                      return currentList;
                    })
            )
        );

    Map<Job, Double> medians = new HashMap<>();

    jobsSalaries.forEach((k, v) -> {
      Double median = 0d;
      List<Double> sortedSalaries = v.stream().sorted().collect(Collectors.toList());

      if (sortedSalaries.size() % 2 == 0) {
        median = ( (sortedSalaries.get(sortedSalaries.size() / 2) + sortedSalaries.get(sortedSalaries.size() / 2 + 1)) / 2 );
      } else {
        median = sortedSalaries.get(sortedSalaries.size() / 2 + 1);
      }

      medians.put(k, median);
    });

    return medians;
  }
}
