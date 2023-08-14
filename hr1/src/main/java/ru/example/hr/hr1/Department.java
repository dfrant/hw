package ru.example.hr.hr1;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
  @Id
  private UUID id;

  @Transient
  private String name;

  @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
  private Set<Person> people;

  @ManyToOne
  private Company company;
}
