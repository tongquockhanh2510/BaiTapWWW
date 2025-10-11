package iuh.fit.tongquockhanh_22677951_jdbc.controller;

import iuh.fit.tongquockhanh_22677951_jdbc.entity.Department;
import iuh.fit.tongquockhanh_22677951_jdbc.entity.Employee;
import iuh.fit.tongquockhanh_22677951_jdbc.service.DepartmentService;
import iuh.fit.tongquockhanh_22677951_jdbc.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping("/departments")
    public String departments(Model model) {
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping("/departments/{id}/employees")
    public String departmentEmployees(
            @PathVariable int id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            Model model) {
        try {
            Department department = departmentService.getById(id);
            if (department == null) {
                return "redirect:/departments?error=true";
            }
            List<Employee> employees = employeeService.findByDepartmentIdAndFilter(id, name, position);
            model.addAttribute("employees", employees);
            model.addAttribute("department", department);
            model.addAttribute("name", name);
            model.addAttribute("position", position);
            return "department-employees";
        } catch (Exception e) {
            return "redirect:/departments?error=true";
        }
    }
}
