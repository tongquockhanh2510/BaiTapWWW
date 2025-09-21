package iuh.fit.tongquockhanh_22677951_mongodb.repository;

import iuh.fit.tongquockhanh_22677951_mongodb.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepository extends MongoRepository<Department, String> { }
