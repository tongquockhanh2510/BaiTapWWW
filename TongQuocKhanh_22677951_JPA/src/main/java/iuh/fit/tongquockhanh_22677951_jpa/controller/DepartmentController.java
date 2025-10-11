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
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping("/departments")
    public String departments(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping("/departments/{id}/employees")
    public String departmentEmployees(@PathVariable Integer id, Model model) {
        Department department = departmentService.findById(id).orElse(null);
        if (department == null) {
            return "redirect:/departments?error=true";
        }
        List<Employee> employees = employeeService.findAllFiltered(null, null, null, id);
        model.addAttribute("employees", employees);
        model.addAttribute("department", department);
        return "department-employees";
    }
}


