package iuh.fit.tongquockhanh_22677951_jpa.service;

import iuh.fit.tongquockhanh_22677951_jpa.entity.Employee;
import iuh.fit.tongquockhanh_22677951_jpa.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> findAllFiltered(String name, Integer age, Double salary, Integer departmentId) {
        List<Employee> employees = findAll();
        return employees.stream()
                .filter(e -> name == null || e.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(e -> age == null || e.getAge() != null && e.getAge().equals(age))
                .filter(e -> salary == null || e.getSalary() != null && e.getSalary().equals(salary))
                .filter(e -> departmentId == null || (e.getDepartment() != null && departmentId.equals(e.getDepartment().getId())))
                .toList();
    }
    public List<Employee> findBySalaryRange(Double min, Double max) {
        return employeeRepository.findBySalaryBetween(min, max);
    }
    public List<Employee> findBySalaryGreaterThan(Double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }
    public List<Employee> findBySalaryLessThan(Double salary) {
        return employeeRepository.findBySalaryLessThan(salary);
    }
}