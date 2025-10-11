package iuh.fit.tongquockhanh_22677951_jdbc.dao;

import iuh.fit.tongquockhanh_22677951_jdbc.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    int save(Employee  employee);
    void update(Employee employee);
    void deleteById(long id);

    Employee findById(int id);
    List<Employee> findByFilters(String name, Integer age, Double salary, Integer departmentId);
    List<Employee> findByDepartmentIdAndFilter(int departmentId, String name, String position);
}
