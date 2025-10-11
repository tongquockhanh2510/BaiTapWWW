package iuh.fit.tongquockhanh_22677951_mongodb.service;

import iuh.fit.tongquockhanh_22677951_mongodb.entity.Department;
import iuh.fit.tongquockhanh_22677951_mongodb.repository.DepartmentRepository;
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

    public Optional<Department> findById(String id) {
        return departmentRepository.findById(id);
    }
}


