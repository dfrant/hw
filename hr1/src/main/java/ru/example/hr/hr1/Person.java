package ru.example.hr.hr1;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Person {
  @Id
  private UUID id;

  private String fio;

  @ManyToOne(fetch = FetchType.LAZY)
  private Department department;

  @ManyToOne
  private Job job;

  private Double salary;
}
