// ...existing code...

// ...existing code...
package iuh.fit.tongquockhanh_22677951_jdbc.service;

import iuh.fit.tongquockhanh_22677951_jdbc.dao.EmployeeDAO;
import iuh.fit.tongquockhanh_22677951_jdbc.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    public List<Employee> findByFilters(String name, Integer age, Double salary, Integer departmentId) {
        return employeeDAO.findByFilters(name, age, salary, departmentId);
    }
    public List<Employee> findByDepartmentIdAndFilter(int departmentId, String name, String position) {
        return employeeDAO.findByDepartmentIdAndFilter(departmentId, name, position);
    }
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public int save(Employee employee) {
        return employeeDAO.save(employee);
    }

    public void update(Employee employee) {
        employeeDAO.update(employee);
    }


    public void deleteById(long id) {
        employeeDAO.deleteById(id);
    }

    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }




}
