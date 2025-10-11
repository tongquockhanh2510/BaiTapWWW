package iuh.fit.tongquockhanh_22677951_jpa.controller;

import iuh.fit.tongquockhanh_22677951_jpa.entity.Department;
import iuh.fit.tongquockhanh_22677951_jpa.entity.Employee;
import iuh.fit.tongquockhanh_22677951_jpa.service.DepartmentService;
import iuh.fit.tongquockhanh_22677951_jpa.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public String home(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Integer age,
                       @RequestParam(required = false) Double salary,
                       @RequestParam(required = false) Integer departmentId,
                       Model model) {
        List<Employee> employees = employeeService.findAllFiltered(name, age, salary, departmentId);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("salary", salary);
        model.addAttribute("departmentId", departmentId);
        return "employees";
    }

    @GetMapping("/employees")
    public String employees(@RequestParam(required = false) String name,
                            @RequestParam(required = false) Integer age,
                            @RequestParam(required = false) Double salary,
                            @RequestParam(required = false) Integer departmentId,
                            Model model) {
        return home(name, age, salary, departmentId, model);
    }

    @GetMapping("/employees/new")
    public String newEmployeeForm(Model model) {
        Employee employee = new Employee();
        List<Department> departments = departmentService.findAll();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        return "employee-form";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute Employee employee, @RequestParam Integer departmentId, Model model) {
        Department department = departmentService.findById(departmentId).orElse(null);
        if (employee.getName() == null || employee.getName().trim().isEmpty() ||
            employee.getAge() == null || employee.getSalary() == null || department == null) {
            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);
            model.addAttribute("employee", employee);
            model.addAttribute("errorMessage", "Vui lòng nhập tên, tuổi, lương và chọn phòng ban");
            return "employee-form";
        }
        employee.setDepartment(department);
        employeeService.save(employee);
        return "redirect:/employees?success=true";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable Integer id, Model model) {
        Employee employee = employeeService.findById(id).orElse(null);
        if (employee == null) {
            return "redirect:/employees?error=true";
        }
        List<Department> departments = departmentService.findAll();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        return "employee-form";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Integer id, @ModelAttribute Employee updated, @RequestParam Integer departmentId) {
        Employee employee = employeeService.findById(id).orElse(null);
        Department department = departmentService.findById(departmentId).orElse(null);
        if (employee == null || department == null) {
            return "redirect:/employees?error=true";
        }
        employee.setName(updated.getName());
        employee.setAge(updated.getAge());
        employee.setSalary(updated.getSalary());
        employee.setDepartment(department);
        employeeService.save(employee);
        return "redirect:/employees?success=true";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteById(id);
        return "redirect:/employees?success=true";
    }
}


