package iuh.fit.tongquockhanh_22677951_mongodb.repository;


import iuh.fit.tongquockhanh_22677951_mongodb.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> findByDepartmentId(String departmentId);
}
