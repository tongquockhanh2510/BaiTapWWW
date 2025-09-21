package iuh.fit.tongquockhanh_22677951_jdbc.dao;

import iuh.fit.tongquockhanh_22677951_jdbc.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    void save(Employee  employee);
    void update(Employee employee);
    Employee getById(long id);
    List<Employee> getAll();
    void deleteById(long id);

    Employee findById(int id);
    List<Employee> findByName(String name);
    List<Employee> findByDepartmentId(int departmentId);
    List<Employee> findBySalaryRange(double minSalary, double maxSalary);
    List<Employee> findBySalaryGreaterThan(double salary);
    List<Employee> findBySalaryLessThan(double salary);
}
