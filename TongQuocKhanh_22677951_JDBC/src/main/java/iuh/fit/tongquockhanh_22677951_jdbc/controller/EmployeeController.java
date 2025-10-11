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
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Double salary,
            @RequestParam(required = false) Integer departmentId,
            Model model) {
        List<Employee> employees = employeeService.findByFilters(name, age, salary, departmentId);
        List<Department> departments = departmentService.getAll();
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("salary", salary);
        model.addAttribute("departmentId", departmentId);
        return "employees";
    }

    @GetMapping("/employees")
    public String employees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Double salary,
            @RequestParam(required = false) Integer departmentId,
            Model model) {
        List<Employee> employees = employeeService.findByFilters(name, age, salary, departmentId);
        List<Department> departments = departmentService.getAll();
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("salary", salary);
        model.addAttribute("departmentId", departmentId);
        return "employees";
    }

    @GetMapping("/employees/new")
    public String newEmployeeForm(Model model) {
        Employee employee = new Employee();
        List<Department> departments = departmentService.getAll();
        employee.setDepartmentID(null);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        return "employee-form";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute Employee employee, Model model) {
        try {
            String errorMessage = null;
            if (employee.getName() == null || employee.getName().trim().isEmpty()) {
                errorMessage = "Tên không được để trống";
            } else if (employee.getAge() == null) {
                errorMessage = "Tuổi không được để trống";
            } else if (employee.getSalary() == null) {
                errorMessage = "Lương không được để trống";
            } else if (employee.getDepartmentID() == null) {
                errorMessage = "Vui lòng chọn phòng ban";
            }

            if (errorMessage != null) {
                List<Department> departments = departmentService.getAll();
                model.addAttribute("departments", departments);
                model.addAttribute("employee", employee);
                model.addAttribute("errorMessage", errorMessage);
                return "employee-form";
            }

            int rows = employeeService.save(employee);
            if (rows > 0) {
                return "redirect:/employees?success=true";
            }
            return "redirect:/employees?error=true";
        } catch (Exception e) {
            List<Department> departments = departmentService.getAll();
            model.addAttribute("departments", departments);
            model.addAttribute("employee", employee);
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi lưu nhân viên");
            return "employee-form";
        }
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable int id, Model model) {
        try {
            Employee employee = employeeService.findById(id);
            if (employee == null) {
                return "redirect:/employees?error=true";
            }
            List<Department> departments = departmentService.getAll();
            model.addAttribute("employee", employee);
            model.addAttribute("departments", departments);
            return "employee-form";
        } catch (Exception e) {
            return "redirect:/employees?error=true";
        }
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable int id, @ModelAttribute Employee employee) {
        try {
            employee.setId(id);
            employeeService.update(employee);
            return "redirect:/employees?success=true";
        } catch (Exception e) {
            return "redirect:/employees?error=true";
        }
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        try {
            employeeService.deleteById(id);
            return "redirect:/employees?success=true";
        } catch (Exception e) {
            return "redirect:/employees?error=true";
        }
    }

}