package th.co.priorsolution.springboot.novice.repository.custom;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.co.priorsolution.springboot.novice.model.nativesql.CustomerModel;
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

    public List<CustomerModel> getCustomerInfoByCustomerId(String customerId){

        String sb = """
                select c.first_name,
                       c.last_name,
                       c.email,
                       case c.active when 1 then 'yes' else 'no' end as active,
                       date_format(c.create_date, '%d/%m/%Y') as create_date,
                       a.address,
                       a.address2,
                       a.district,
                       a.postal_code,
                       a.phone,
                       a.location,
                       c2.city,
                       c3.country
                from customer c
                         inner join address a on c.address_id = a.address_id
                inner join city c2 on a.city_id = c2.city_id
                inner join country c3 on c2.country_id = c3.country_id
                where customer_id = ?
                    """;
        List<Object> parameters = new ArrayList<>();
        parameters.add(customerId);

        List<CustomerModel> result = this.jdbcTemplate.query(sb, parameters.toArray(), (rs, rowNum) -> {
            CustomerModel x = new CustomerModel();
            int cols = 1;
            x.setFirstName(rs.getString(cols++));
            x.setLastName(rs.getString(cols++));
            x.setEmail(rs.getString(cols++));
            x.setActive(rs.getString(cols++));
            x.setCreateDate(rs.getString(cols++));
            x.setAddress(rs.getString(cols++));
            x.setAddress2(rs.getString(cols++));
            x.setDistrict(rs.getString(cols++));
            x.setPostalCode(rs.getString(cols++));
            x.setPhone(rs.getString(cols++));
            x.setLocation(rs.getString(cols++));
            x.setCity(rs.getString(cols++));
            x.setCountry(rs.getString(cols++));
            return x;
        });

        return result;
    }


}
