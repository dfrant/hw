package ru.example.hr.hr1;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {
  @Id
  private UUID id;

  private String name;
  private Double minPayment;
  private Double maxPayment;

  @OneToMany
  private Set<Person> people;
}
