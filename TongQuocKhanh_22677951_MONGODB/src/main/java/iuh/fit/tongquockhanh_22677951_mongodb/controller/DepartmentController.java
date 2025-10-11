package iuh.fit.tongquockhanh_22677951_mongodb.controller;

import iuh.fit.tongquockhanh_22677951_mongodb.entity.Department;
import iuh.fit.tongquockhanh_22677951_mongodb.entity.Employee;
import iuh.fit.tongquockhanh_22677951_mongodb.service.DepartmentService;
import iuh.fit.tongquockhanh_22677951_mongodb.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping("/{id}/employees")
    public String employeesByDepartment(@PathVariable String id, Model model, RedirectAttributes ra) {
        // Try exact lookup first
        Optional<Department> deptOpt = departmentService.findById(id);
        Department dept = null;
        if (deptOpt.isPresent()) {
            dept = deptOpt.get();
        } else {
            // Fallback: scan all departments and try to match by numeric value as well
            List<Department> all = departmentService.findAll();
            for (Department d : all) {
                if (d.getId() == null) continue;
                if (d.getId().equals(id)) {
                    dept = d; break;
                }
                try {
                    long a = Long.parseLong(d.getId());
                    long b = Long.parseLong(id);
                    if (a == b) { dept = d; break; }
                } catch (NumberFormatException ignored) {
                }
            }
        }
        if (dept == null) {
            ra.addAttribute("error", true);
            return "redirect:/departments";
        }

        // Use the matched department id (string) to find employees with flexible matching
        String matchedId = dept.getId();
        List<Employee> employees = employeeService.findAllByDepartmentIdFlexible(matchedId);
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("employees", employees);
        model.addAttribute("department", dept);
        return "department-employees";
    }
}
