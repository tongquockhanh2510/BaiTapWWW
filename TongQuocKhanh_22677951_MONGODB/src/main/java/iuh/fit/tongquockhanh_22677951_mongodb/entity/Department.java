package iuh.fit.tongquockhanh_22677951_mongodb.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "departments")
public class Department {
    @Id
    private String id;
    private String name;

}
