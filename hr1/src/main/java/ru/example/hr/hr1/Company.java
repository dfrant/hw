package ru.example.hr.hr1;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {
  @Id
  private UUID id;

  private String name;

  private String address;

  @OneToMany(mappedBy = "company", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  private List<Department> departments;
}
