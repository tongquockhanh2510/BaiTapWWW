package iuh.fit.tongquockhanh_22677951_jdbc.service;

import iuh.fit.tongquockhanh_22677951_jdbc.dao.EmployeeDAO;
import iuh.fit.tongquockhanh_22677951_jdbc.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    public Employee getById(long id) {
        return employeeDAO.getById(id);
    }

    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    public void deleteById(long id) {
        employeeDAO.deleteById(id);
    }
    
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    public List<Employee> findByName(String name) {
        return employeeDAO.findByName(name);
    }

    public List<Employee> findByDepartmentId(int departmentId) {
        return employeeDAO.findByDepartmentId(departmentId);
    }

    public List<Employee> findBySalaryRange(double minSalary, double maxSalary) {
        return employeeDAO.findBySalaryRange(minSalary, maxSalary);
    }

    public List<Employee> findBySalaryGreaterThan(double salary) {
        return employeeDAO.findBySalaryGreaterThan(salary);
    }

    public List<Employee> findBySalaryLessThan(double salary) {
        return employeeDAO.findBySalaryLessThan(salary);
    }
}
