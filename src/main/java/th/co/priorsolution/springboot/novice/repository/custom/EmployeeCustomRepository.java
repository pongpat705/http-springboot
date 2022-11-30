package th.co.priorsolution.springboot.novice.repository.custom;

import th.co.priorsolution.springboot.novice.model.EmployeeModel;
import th.co.priorsolution.springboot.novice.model.nativesql.EmployeeOfficeInfos;

import java.util.List;

public interface EmployeeCustomRepository {
    public List<EmployeeOfficeInfos> getEmployInfos(EmployeeModel employeeModel);

}
