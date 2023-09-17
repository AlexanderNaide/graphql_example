package com.example.graphql_example.controllers;

import com.example.graphql_example.entyties.Department;
import com.example.graphql_example.entyties.Employee;
import com.example.graphql_example.entyties.Organization;
import com.example.graphql_example.repositories.DepartmentRepository;
import com.example.graphql_example.repositories.EmployeeRepository;
import com.example.graphql_example.repositories.OrganizationRepository;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

    DepartmentRepository departmentRepository;
    EmployeeRepository employeeRepository;
    OrganizationRepository organizationRepository;

    EmployeeController(DepartmentRepository departmentRepository,
                       EmployeeRepository employeeRepository,
                       OrganizationRepository organizationRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.organizationRepository = organizationRepository;
    }

    @QueryMapping
    public Iterable<Employee> employees() {
        return employeeRepository.findAll();
    }

    @QueryMapping
    public Employee employee(@Argument Integer id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    @MutationMapping
//    public Employee newEmployee(@Argument EmployeeInput employee) {
    public Employee newEmployee(@Argument String firstname,
                                @Argument String lastname,
                                @Argument String position,
                                @Argument Integer salary,
                                @Argument Integer age,
                                @Argument Integer organizationId,
                                @Argument Integer departmentId
                                ) {
        Department department = departmentRepository
                .findById(departmentId).get();
        Organization organization = organizationRepository
                .findById(organizationId).get();
        return employeeRepository.save(new Employee(
                firstname,
                lastname,
                position,
                salary,
                age,
                department,
                organization));
    }

/*    @QueryMapping
    public Iterable<Employee> employeesWithFilter(
            @Argument EmployeeFilter filter) {
        Specification<Employee> spec = null;
        if (filter.getSalary() != null)
            spec = bySalary(filter.getSalary());
        if (filter.getAge() != null)
            spec = (spec == null ? byAge(filter.getAge()) : spec.and(byAge(filter.getAge())));
        if (filter.getPosition() != null)
            spec = (spec == null ? byPosition(filter.getPosition()) :
                    spec.and(byPosition(filter.getPosition())));
        if (spec != null)
            return employeeRepository.findAll(spec);
        else
            return employeeRepository.findAll();
    }

    private Specification<Employee> bySalary(FilterField filterField) {
        return (root, query, builder) -> filterField
                .generateCriteria(builder, root.get("salary"));
    }

    private Specification<Employee> byAge(FilterField filterField) {
        return (root, query, builder) -> filterField
                .generateCriteria(builder, root.get("age"));
    }

    private Specification<Employee> byPosition(FilterField filterField) {
        return (root, query, builder) -> filterField
                .generateCriteria(builder, root.get("position"));
    }*/
}
