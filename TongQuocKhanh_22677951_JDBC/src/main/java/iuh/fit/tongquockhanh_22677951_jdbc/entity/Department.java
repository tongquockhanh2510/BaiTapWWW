package iuh.fit.tongquockhanh_22677951_jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(name = "departments")
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    private int id;
    private String name;

}
