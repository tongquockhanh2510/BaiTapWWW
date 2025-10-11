    // ...existing code...
// ...existing code...
package iuh.fit.tongquockhanh_22677951_jdbc.dao;

import iuh.fit.tongquockhanh_22677951_jdbc.entity.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    @Override
    public List<Employee> findByFilters(String name, Integer age, Double salary, Integer departmentId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM employees WHERE 1=1");
        java.util.List<Object> params = new java.util.ArrayList<>();
        if (name != null && !name.isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name + "%");
        }
        if (age != null) {
            sql.append(" AND age = ?");
            params.add(age);
        }
        if (salary != null) {
            sql.append(" AND salary = ?");
            params.add(salary);
        }
        if (departmentId != null) {
            sql.append(" AND department_id = ?");
            params.add(departmentId);
        }
        return jdbcTemplate.query(sql.toString(), rowMapper, params.toArray());
    }
    @Override
    public List<Employee> findByDepartmentIdAndFilter(int departmentId, String name, String position) {
        StringBuilder sql = new StringBuilder("SELECT * FROM employees WHERE department_id = ?");
        java.util.List<Object> params = new java.util.ArrayList<>();
        params.add(departmentId);
        if (name != null && !name.isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name + "%");
        }
        if (position != null && !position.isEmpty()) {
            sql.append(" AND position LIKE ?");
            params.add("%" + position + "%");
        }
        return jdbcTemplate.query(sql.toString(), rowMapper, params.toArray());
    }

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Employee employee) {
        String sql = "INSERT INTO employees (name, age, salary, department_id) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, employee.getName(), employee.getAge(), employee.getSalary(), employee.getDepartmentID());
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employees SET name = ?, age = ?, salary = ?, department_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, employee.getName(), employee.getAge(), employee.getSalary(), employee.getDepartmentID(), employee.getId());
    }


    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Employee findById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }



    private RowMapper<Employee> rowMapper = new RowMapper<Employee>() {
        @Override
        public Employee mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            Integer id = (Integer) rs.getObject("id");
            Integer age = (Integer) rs.getObject("age");
            Double salary = rs.getObject("salary") != null ? ((Number) rs.getObject("salary")).doubleValue() : null;
            Integer departmentId = (Integer) rs.getObject("department_id");

            employee.setId(id);
            employee.setName(rs.getString("name"));
            employee.setAge(age);
            employee.setSalary(salary);
            employee.setDepartmentID(departmentId);
            return employee;
        }
    };
}
