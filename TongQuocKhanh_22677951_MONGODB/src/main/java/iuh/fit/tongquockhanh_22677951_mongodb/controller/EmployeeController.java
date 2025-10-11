package iuh.fit.tongquockhanh_22677951_mongodb.controller;

import iuh.fit.tongquockhanh_22677951_mongodb.entity.Employee;
import iuh.fit.tongquockhanh_22677951_mongodb.entity.Department;
import iuh.fit.tongquockhanh_22677951_mongodb.service.EmployeeService;
import iuh.fit.tongquockhanh_22677951_mongodb.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping({"/", "/employees"})
    public String listEmployees(@RequestParam(required = false) String name,
                                @RequestParam(required = false) Integer age,
                                @RequestParam(required = false) Double salary,
                                @RequestParam(required = false) String departmentId,
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

    @GetMapping("/employees/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.findAll());
        return "employee-form";
    }

    @PostMapping("/employees")
    public String createEmployee(@ModelAttribute Employee employee, RedirectAttributes ra) {
        String validation = validateEmployee(employee);
        if (validation != null) {
            ra.addFlashAttribute("errorMessage", validation);
            return "redirect:/employees/new";
        }
        employeeService.save(employee);
        ra.addAttribute("success", true);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes ra) {
        Optional<Employee> emp = employeeService.findById(id);
        if (emp.isEmpty()) {
            ra.addAttribute("error", true);
            return "redirect:/employees";
        }
        model.addAttribute("employee", emp.get());
        model.addAttribute("departments", departmentService.findAll());
        return "employee-form";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable String id, @ModelAttribute Employee employee, RedirectAttributes ra) {
        Optional<Employee> existing = employeeService.findById(id);
        if (existing.isEmpty()) {
            ra.addAttribute("error", true);
            return "redirect:/employees";
        }
        String validation = validateEmployee(employee);
        if (validation != null) {
            ra.addFlashAttribute("errorMessage", validation);
            return "redirect:/employees/edit/" + id;
        }
        employee.setId(id);
        employeeService.save(employee);
        ra.addAttribute("success", true);
        return "redirect:/employees";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable String id, RedirectAttributes ra) {
        Optional<Employee> existing = employeeService.findById(id);
        if (existing.isEmpty()) {
            ra.addAttribute("error", true);
            return "redirect:/employees";
        }
        employeeService.deleteById(id);
        ra.addAttribute("success", true);
        return "redirect:/employees";
    }

    private String validateEmployee(Employee e) {
        if (e.getName() == null || e.getName().trim().isEmpty()) return "Tên không được để trống";
        if (e.getAge() != null && (e.getAge() < 18 || e.getAge() > 65)) return "Tuổi phải trong khoảng 18-65";
        if (e.getSalary() != null && e.getSalary() < 0) return "Lương phải >= 0";
        return null;
    }
}
