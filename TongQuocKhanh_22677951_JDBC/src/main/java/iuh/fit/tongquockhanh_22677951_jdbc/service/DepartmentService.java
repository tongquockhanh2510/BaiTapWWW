package iuh.fit.tongquockhanh_22677951_jdbc.service;

import iuh.fit.tongquockhanh_22677951_jdbc.dao.DepartmentDAO;
import iuh.fit.tongquockhanh_22677951_jdbc.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentDAO departmentDAO;

    public DepartmentService(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }
    public Department getById(int id) {
        return departmentDAO.getById(id);
    }

    public List<Department> getAll() {
        return departmentDAO.getAll();
    }
}
