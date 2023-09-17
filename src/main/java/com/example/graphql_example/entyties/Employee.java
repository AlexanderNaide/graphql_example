package com.example.graphql_example.entyties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String firstname;
    private String lastname;
    private String position;
    private int salary;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    public Employee(String firstname, String lastname, String position, int salary, int age, Department department, Organization organization) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.salary = salary;
        this.age = age;
        this.department = department;
        this.organization = organization;
    }
}
