package iuh.fit.tongquockhanh_22677951_mongodb.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "employees")
public class Employee {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Double salary;
    @Field("department_id")
    private String departmentId;

}