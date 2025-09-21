package iuh.fit.tongquockhanh_22677951_jdbc.controller;

import iuh.fit.tongquockhanh_22677951_jdbc.entity.Employee;
import iuh.fit.tongquockhanh_22677951_jdbc.service.EmployeeService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> save(@RequestBody Employee employee) {
        employeeService.save(employee);
        return ResponseEntity.ok("Employee saved successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody Employee employee) {
        employee.setId((int) id);
        employeeService.update(employee);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable long id) {
        Employee employee = employeeService.getById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.getAll();
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
    @GetMapping("/search/id/{id}")
    public ResponseEntity<Employee> findById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<Employee>> findByName(@PathVariable String name) {
        List<Employee> employees = employeeService.findByName(name);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/department/{departmentId}")
    public ResponseEntity<List<Employee>> findByDepartmentId(@PathVariable int departmentId) {
        List<Employee> employees = employeeService.findByDepartmentId(departmentId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/salary/range")
    public ResponseEntity<List<Employee>> findBySalaryRange(
            @RequestParam double minSalary, 
            @RequestParam double maxSalary) {
        List<Employee> employees = employeeService.findBySalaryRange(minSalary, maxSalary);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/salary/greater-than/{salary}")
    public ResponseEntity<List<Employee>> findBySalaryGreaterThan(@PathVariable double salary) {
        List<Employee> employees = employeeService.findBySalaryGreaterThan(salary);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/salary/less-than/{salary}")
    public ResponseEntity<List<Employee>> findBySalaryLessThan(@PathVariable double salary) {
        List<Employee> employees = employeeService.findBySalaryLessThan(salary);
        return ResponseEntity.ok(employees);
    }
}
