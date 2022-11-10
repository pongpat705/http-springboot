package th.co.priorsolution.springboot.novice.repository.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.co.priorsolution.springboot.novice.model.EmployeeModel;
import th.co.priorsolution.springboot.novice.model.nativesql.EmployeeOfficeInfos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class EmployeeCustomRepository {

    private JdbcTemplate jdbcTemplate;

    public EmployeeCustomRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EmployeeOfficeInfos> getEmployInfos(EmployeeModel employeeModel){
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
                result.setPostalCode(rs.getString(cols++));
                return result;
            }
        }, params.toArray());

        return resultList;

    }


}
