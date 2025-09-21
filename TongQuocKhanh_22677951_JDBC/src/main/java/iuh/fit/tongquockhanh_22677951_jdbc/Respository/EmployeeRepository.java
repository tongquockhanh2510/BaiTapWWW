package iuh.fit.tongquockhanh_22677951_jdbc.Respository;

import iuh.fit.tongquockhanh_22677951_jdbc.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findByDeparmentID(int deparmentID);
}
