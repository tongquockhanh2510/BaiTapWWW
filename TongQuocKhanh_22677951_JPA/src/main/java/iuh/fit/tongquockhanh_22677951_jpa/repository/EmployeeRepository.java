package iuh.fit.tongquockhanh_22677951_jpa.repository;

import iuh.fit.tongquockhanh_22677951_jpa.entity.Department;
import iuh.fit.tongquockhanh_22677951_jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContaining(String name);
    List<Employee> findByDepartment(Department department);
    List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);
    List<Employee> findBySalaryGreaterThan(Double salary);
    List<Employee> findBySalaryLessThan(Double salary);
}
