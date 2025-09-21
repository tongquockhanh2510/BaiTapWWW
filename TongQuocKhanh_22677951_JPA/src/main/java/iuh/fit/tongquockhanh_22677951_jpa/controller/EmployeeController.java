package iuh.fit.tongquockhanh_22677951_jpa.controller;




import iuh.fit.tongquockhanh_22677951_jpa.entity.Employee;
import iuh.fit.tongquockhanh_22677951_jpa.service.EmployeeService;
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
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updated) {
        Employee employee = employeeService.findById(id).orElseThrow();
        employee.setName(updated.getName());
        employee.setSalary(updated.getSalary());
        employee.setDepartment(updated.getDepartment());
        return employeeService.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
    }

    @GetMapping("/search/id/{id}")
    public Employee searchById(@PathVariable Long id) {
        return employeeService.findById(id).orElseThrow();
    }

    @GetMapping("/search/name/{name}")
    public List<Employee> searchByName(@PathVariable String name) {
        return employeeService.findByName(name);
    }

    @GetMapping("/search/department/{departmentId}")
    public List<Employee> searchByDepartment(@PathVariable Long departmentId) {
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