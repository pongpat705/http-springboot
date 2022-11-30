package th.co.priorsolution.springboot.novice.repository.custom;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class ReportCustomRepositoryImpl implements ReportCustomRepository{
    private JdbcTemplate jdbcTemplate;

    public ReportCustomRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection =  this.jdbcTemplate.getDataSource().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
