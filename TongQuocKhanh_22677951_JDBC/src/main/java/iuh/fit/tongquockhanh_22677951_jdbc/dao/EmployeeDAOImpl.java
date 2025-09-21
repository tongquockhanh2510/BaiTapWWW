package iuh.fit.tongquockhanh_22677951_jdbc.dao;

import iuh.fit.tongquockhanh_22677951_jdbc.entity.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO employees (name, salary, department_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, employee.getName(), employee.getSalary(), employee.getDepartmentID());
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employees SET name = ?, salary = ?, department_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, employee.getName(), employee.getSalary(), employee.getDepartmentID(), employee.getId());
    }

    @Override
    public Employee getById(long id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Employee> getAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, rowMapper);
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

    @Override
    public List<Employee> findByName(String name) {
        String sql = "SELECT * FROM employees WHERE name LIKE ?";
        return jdbcTemplate.query(sql, rowMapper, "%" + name + "%");
    }

    @Override
    public List<Employee> findByDepartmentId(int departmentId) {
        String sql = "SELECT * FROM employees WHERE department_id = ?";
        return jdbcTemplate.query(sql, rowMapper, departmentId);
    }

    @Override
    public List<Employee> findBySalaryRange(double minSalary, double maxSalary) {
        String sql = "SELECT * FROM employees WHERE salary BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, rowMapper, minSalary, maxSalary);
    }

    @Override
    public List<Employee> findBySalaryGreaterThan(double salary) {
        String sql = "SELECT * FROM employees WHERE salary > ?";
        return jdbcTemplate.query(sql, rowMapper, salary);
    }

    @Override
    public List<Employee> findBySalaryLessThan(double salary) {
        String sql = "SELECT * FROM employees WHERE salary < ?";
        return jdbcTemplate.query(sql, rowMapper, salary);
    }

    private RowMapper<Employee> rowMapper = new RowMapper<Employee>() {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Employee(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("salary"),
                                rs.getInt("department_id")
                    );
        }
    };
}
