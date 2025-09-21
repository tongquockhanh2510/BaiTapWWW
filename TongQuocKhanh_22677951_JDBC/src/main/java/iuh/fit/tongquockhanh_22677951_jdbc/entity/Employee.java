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
    private int id;
    private String name;
    private double salary;
    @Column("department_id")
    private int departmentID;
}
