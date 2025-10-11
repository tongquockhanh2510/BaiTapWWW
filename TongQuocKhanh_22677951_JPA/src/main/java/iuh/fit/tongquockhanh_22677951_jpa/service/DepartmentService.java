package iuh.fit.tongquockhanh_22677951_jpa.service;

import iuh.fit.tongquockhanh_22677951_jpa.entity.Department;
import iuh.fit.tongquockhanh_22677951_jpa.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Optional<Department> findById(Integer id) {
        return departmentRepository.findById(id);
    }
}


