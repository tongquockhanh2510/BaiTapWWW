package iuh.fit.tongquockhanh_22677951_mongodb.controller;


import iuh.fit.tongquockhanh_22677951_mongodb.entity.Employee;
import iuh.fit.tongquockhanh_22677951_mongodb.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() { return employeeService.findAll(); }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable String id) {
        return employeeService.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable String id, @RequestBody Employee updated) {
        Employee employee = employeeService.findById(id).orElseThrow();
        employee.setName(updated.getName());
        employee.setSalary(updated.getSalary());
        employee.setDepartmentId(updated.getDepartmentId());
        return employeeService.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeService.deleteById(id);
    }

    // Search APIs
    @GetMapping("/search/id/{id}")
    public Employee searchById(@PathVariable String id) {
        return employeeService.findById(id).orElseThrow();
    }

    @GetMapping("/search/name/{name}")
    public List<Employee> searchByName(@PathVariable String name) {
        return employeeService.findByName(name);
    }

    @GetMapping("/search/department/{departmentId}")
    public List<Employee> searchByDepartment(@PathVariable String departmentId) {
        return employeeService.findByDepartmentId(departmentId);
    }

    @GetMapping("/search/salary/range")
    public List<Employee> searchBySalaryRange(@RequestParam Double minSalary, @RequestParam Double maxSalary) {
        return employeeService.findBySalaryRange(minSalary, maxSalary);
    }

    @GetMapping("/search/salary/greater-than/{salary}")
    public List<Employee> searchBySalaryGreaterThan(@PathVariable Double salary) {
        return employeeService.findBySalaryGreaterThan(salary);
    }

    @GetMapping("/search/salary/less-than/{salary}")
    public List<Employee> searchBySalaryLessThan(@PathVariable Double salary) {
        return employeeService.findBySalaryLessThan(salary);
    }
}
