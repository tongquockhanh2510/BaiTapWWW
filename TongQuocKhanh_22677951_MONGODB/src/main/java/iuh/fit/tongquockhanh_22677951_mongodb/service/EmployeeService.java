package iuh.fit.tongquockhanh_22677951_mongodb.service;

import iuh.fit.tongquockhanh_22677951_mongodb.entity.Employee;
import iuh.fit.tongquockhanh_22677951_mongodb.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) { return employeeRepository.save(employee); }
    public List<Employee> findAll() { return employeeRepository.findAll(); }
    public Optional<Employee> findById(String id) { return employeeRepository.findById(id); }
    public void deleteById(String id) { employeeRepository.deleteById(id); }
    public List<Employee> findByName(String name) { return employeeRepository.findByNameContaining(name); }
    public List<Employee> findByDepartmentId(String departmentId) { return employeeRepository.findByDepartmentId(departmentId); }
    public List<Employee> findBySalaryRange(Double min, Double max) { return employeeRepository.findBySalaryBetween(min, max); }
    public List<Employee> findBySalaryGreaterThan(Double salary) { return employeeRepository.findBySalaryGreaterThan(salary); }
    public List<Employee> findBySalaryLessThan(Double salary) { return employeeRepository.findBySalaryLessThan(salary); }
}
