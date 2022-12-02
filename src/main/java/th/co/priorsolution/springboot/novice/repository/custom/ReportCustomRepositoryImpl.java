package th.co.priorsolution.springboot.novice.repository.custom;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.co.priorsolution.springboot.novice.model.nativesql.FilmByCustomerModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<FilmByCustomerModel> getFilmByCustomerId(String customerId){

        String sb = """
                SELECT  f.title , f.release_year
                , a.address as store_branch
                , a.postal_code as store_postal_code
                FROM rental r
                inner join inventory i on r.inventory_id  = i.inventory_id\s
                inner join film f on i.film_id  = f.film_id\s
                inner join store s on i.store_id = s.store_id\s
                inner join address a on s.address_id  = a.address_id
                where r.customer_id = ?
                    """;
        List<Object> parameters = new ArrayList<>();
        parameters.add(customerId);

        List<FilmByCustomerModel> result = this.jdbcTemplate.query(sb, parameters.toArray(), (rs, rowNum) -> {
            FilmByCustomerModel x = new FilmByCustomerModel();
            int cols = 1;
            x.setTitle(rs.getString(cols++));
            x.setReleaseYear(rs.getLong(cols++));
            x.setStoreBranch(rs.getString(cols++));
            x.setStorePostalCode(rs.getString(cols++));
            return x;
        });

        return result;
    }


}
