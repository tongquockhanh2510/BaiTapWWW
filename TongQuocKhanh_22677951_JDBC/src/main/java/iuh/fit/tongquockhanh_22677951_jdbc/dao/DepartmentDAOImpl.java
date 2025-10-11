package iuh.fit.tongquockhanh_22677951_jdbc.dao;

import iuh.fit.tongquockhanh_22677951_jdbc.entity.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

    private final JdbcTemplate jdbcTemplate;

    public DepartmentDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Department getById(int id) {
        String sql = "SELECT * FROM departments WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Department> getAll() {
        String sql = "SELECT * FROM departments";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // removed delete not used by current HTML flows

    private RowMapper<Department> rowMapper = new RowMapper<Department>() {
        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Department(rs.getInt("id"), rs.getString("name"));
        }
    };
}
