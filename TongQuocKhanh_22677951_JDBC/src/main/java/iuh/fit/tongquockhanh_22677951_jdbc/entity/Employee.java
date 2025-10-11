package iuh.fit.tongquockhanh_22677951_jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private Double salary;
    @Column("department_id")
    private Integer departmentID;

    public Employee(String name, Integer age, Double salary, Integer departmentID) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.departmentID = departmentID;
    }
}
