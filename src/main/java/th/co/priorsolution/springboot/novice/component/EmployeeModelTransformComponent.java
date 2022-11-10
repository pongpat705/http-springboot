package th.co.priorsolution.springboot.novice.component;

import org.springframework.stereotype.Component;
import th.co.priorsolution.springboot.novice.entity.EmployeeEntity;
import th.co.priorsolution.springboot.novice.model.EmployeeModel;

@Component
public class EmployeeModelTransformComponent {

    public EmployeeModel transFormEntityToModel(EmployeeEntity employeeEntity){
        EmployeeModel employeeModel = new EmployeeModel();

        employeeModel.setEmployeeNumber(employeeEntity.getEmployeeNumber());
        employeeModel.setEmail(employeeEntity.getEmail());
        employeeModel.setExtension(employeeEntity.getExtension());
        employeeModel.setFirstName(employeeEntity.getFirstName());
        employeeModel.setLastName(employeeEntity.getLastName());
        employeeModel.setJobTitle(employeeEntity.getJobTitle());
        employeeModel.setOfficeCode(employeeEntity.getOfficeCode());
        employeeModel.setReportsTo(employeeEntity.getReportsTo());

        return employeeModel;
    }

    public EmployeeEntity transFormModelToEntity(EmployeeModel employeeModel){
        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setEmployeeNumber(employeeModel.getEmployeeNumber());
        employeeEntity.setEmail(employeeModel.getEmail());
        employeeEntity.setExtension(employeeModel.getExtension());
        employeeEntity.setFirstName(employeeModel.getFirstName());
        employeeEntity.setLastName(employeeModel.getLastName());
        employeeEntity.setJobTitle(employeeModel.getJobTitle());
        employeeEntity.setOfficeCode(employeeModel.getOfficeCode());
        employeeEntity.setReportsTo(employeeModel.getReportsTo());

        return employeeEntity;
    }

    public void updateEntityByModel(EmployeeEntity employeeEntity, EmployeeModel employeeModel){

        employeeEntity.setEmail(employeeModel.getEmail());
        employeeEntity.setExtension(employeeModel.getExtension());
        employeeEntity.setFirstName(employeeModel.getFirstName());
        employeeEntity.setLastName(employeeModel.getLastName());
        employeeEntity.setJobTitle(employeeModel.getJobTitle());
        employeeEntity.setOfficeCode(employeeModel.getOfficeCode());
        employeeEntity.setReportsTo(employeeModel.getReportsTo());

    }
}
