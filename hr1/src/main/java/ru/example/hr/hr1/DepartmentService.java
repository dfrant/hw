package ru.example.hr.hr1;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentService {
  private DepartmentRepository repository;
  private PersonService personService;

  public DepartmentService(DepartmentRepository repository, @Lazy PersonService personService) {
    this.repository = repository;
    this.personService = personService;
  }

  public List<Department> findAll() {
    return repository.findAll();
  }

  public Department save(Department department) {
    return repository.saveAndFlush(department);
  }

  public void delete(UUID id) {
    repository.deleteById(id);
  }

  public Department getDepartmentById(UUID id) {
    return repository.findById(id).get();
  }

  public Long getTotalPeopleCount() {
    return repository.findAll().stream().map(d -> d.getPeople().size()).count();
  }

  public Long getDepartmentPeopleCount(UUID id) {
    return repository.findById(id).stream().map(d -> d.getPeople().size()).count();
  }

  public Map<Job, List<Double>> getSalariesByJobInDepartment(Department department) {
    Map<Job, List<Double>> jobsSalaries = new HashMap<>();

    department.getPeople().forEach(person ->
        jobsSalaries.merge(
            person.getJob(),
            List.of(person.getSalary()),
            (currentList, toAdd) -> {
              currentList.addAll(toAdd);
              return currentList;
            }));

    return jobsSalaries;
  }
}
