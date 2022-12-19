package th.co.priorsolution.springboot.novice.repository.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.co.priorsolution.springboot.novice.model.EmployeeResponseModel;
import th.co.priorsolution.springboot.novice.model.nativesql.EmployeeOfficeInfos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Repository
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository{

    private JdbcTemplate jdbcTemplate;

    public EmployeeCustomRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeeOfficeInfos> getEmployInfos(EmployeeResponseModel employeeModel){

        List<Object> params = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        sb.append("  select e.employeeNumber , e.firstName , e.lastName, e.email , e.jobTitle , o.territory   ");
        sb.append("  , o.country , o.state, o.phone, o.postalCode   ");
        sb.append("  FROM employees e   ");
        sb.append("  inner join offices o on e.officeCode  = o.officeCode   ");
        sb.append("  WHERE 1=1  ");

        if(!StringUtils.isEmpty(employeeModel.getFirstName())){
            sb.append("  and e.firstName like ?  ");
            params.add(employeeModel.getFirstName()+"%");
        }
        if(!StringUtils.isEmpty(employeeModel.getJobTitle())){
            sb.append("  and e.jobTitle  = ?  ");
            params.add(employeeModel.getJobTitle());
        }
        if(!StringUtils.isEmpty(employeeModel.getEmployeeNumber())){
            sb.append("  and e.employeeNumber  = ?  ");
            params.add(employeeModel.getEmployeeNumber());
        }
        log.debug("getEmployInfos sql {}", sb.toString());

        List<EmployeeOfficeInfos> resultList = this.jdbcTemplate.query(sb.toString(), new RowMapper<EmployeeOfficeInfos>() {
            @Override
            public EmployeeOfficeInfos mapRow(ResultSet rs, int rowNum) throws SQLException {
                int cols = 1;
                EmployeeOfficeInfos result = new EmployeeOfficeInfos();
                result.setEmployeeNumber(rs.getString(cols++));
                result.setFirstName(rs.getString(cols++));
                result.setLastName(rs.getString(cols++));
                result.setEmail(rs.getString(cols++));
                result.setJobTitle(rs.getString(cols++));
                result.setTerritory(rs.getString(cols++));
                result.setCountry(rs.getString(cols++));
                result.setState(rs.getString(cols++));
                result.setPhone(rs.getString(cols++));

                Date date = rs.getDate((cols++));

                LocalDateTime y = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();


                LocalDateTime localDateTime = LocalDateTime.now();

                String sqlFormat = "16/11/2022 13:42:06";
                String dateFormat = "dd/MM/yyyy H:m:s";
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);


                String dateStr = dateTimeFormatter.format(localDateTime);
                LocalDateTime x = LocalDateTime.from(dateTimeFormatter.parse(sqlFormat));




                result.setPostalCode(rs.getString(cols++));
                return result;
            }
        }, params.toArray());

        return resultList;

    }


}
