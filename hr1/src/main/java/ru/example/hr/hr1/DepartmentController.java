package ru.example.hr.hr1;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class DepartmentController {
  private final DepartmentService service;
  private final CompanyService companyService;

  @GetMapping(value = "/departments",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Department>> getAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @PostMapping(value = "/departments",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Department> save(@RequestBody Department department) {
    return ResponseEntity.ok(service.save(department));
  }

  @GetMapping(value = "/departments/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Department> getAll(@PathVariable("id") UUID id) { return ResponseEntity.ok(service.getDepartmentById(id)); }

  @GetMapping(value = "/departments/{id}/people/count",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Long getPeopleByDepartment(@PathVariable("id") UUID id) {
    return service.getDepartmentPeopleCount(id);
  }

  @GetMapping(value = "/departments/people/total",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Long getTotalPeople() {
    return service.getTotalPeopleCount();
  }

  @PutMapping("/departments/{id}")
  public ResponseEntity<Department> replace(@PathVariable("id") UUID id, @RequestBody Department newDepartment) {
    /* in progress */
    Department department = service.getDepartmentById(id);
    department.setName(newDepartment.getName());
    department.setPeople(newDepartment.getPeople());
    department.setCompany(newDepartment.getCompany());

    return ResponseEntity.ok(service.save(department));
  }

  @DeleteMapping("/departments/{id}")
  public void delete(@PathVariable("id") UUID id) {
    service.delete(id);
  }

  @GetMapping("/departments/{id}/clones")
  public List<Department> getSameByNameDepartmentsInOtherCompany(@PathVariable("id") String id) {
    return companyService.findSameDepartmentsWithNameInOtherCompanies(service.getDepartmentById(UUID.fromString(id)));
  }

}
