package ru.example.hr.hr1;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonService {
  private PersonRepository repository;
  private CompanyService companyService;

  public List<Person> findAll(){
    return repository.findAll();
  }

  public Person save(Person department) {
    return repository.saveAndFlush(department);
  }

  public void delete(UUID id) {
    repository.deleteById(id);
  }

  public Person getPersonById(UUID id) {
    return repository.findById(id).get();
  }

  public int getTotalPeopleCount() {
    return repository.findAll().size();
  }
}
