package iuh.fit.tongquockhanh_22677951_jdbc.dao;

import iuh.fit.tongquockhanh_22677951_jdbc.entity.Department;

import java.util.List;

public interface DepartmentDAO {
    Department getById(int id);
    List<Department> getAll();
}
