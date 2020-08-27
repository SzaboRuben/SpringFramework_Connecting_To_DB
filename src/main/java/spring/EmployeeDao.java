package spring;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class EmployeeDao {


    private DataSource dataSource;

    public EmployeeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //   Boilerplate coding with JDBC -->
    public void saveEmployee(String name) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("insert into employees(emp_name) values (?)");
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot insert", e);
        }
    }

    public List<String> getEmployees() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("select emp_name from employees");
             ResultSet rs = ps.executeQuery()
        ) {
            List<String> result = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("emp_name");
                result.add(name);
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException("Can not query employees", e);
        }
    }


}
