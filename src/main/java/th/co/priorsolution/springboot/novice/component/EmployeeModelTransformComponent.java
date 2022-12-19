package th.co.priorsolution.springboot.novice.component;

import org.springframework.stereotype.Component;
import th.co.priorsolution.springboot.novice.entity.EmployeeEntity;
import th.co.priorsolution.springboot.novice.model.EmployeeInsertModel;
import th.co.priorsolution.springboot.novice.model.EmployeeResponseModel;

@Component
public class EmployeeModelTransformComponent {

    public EmployeeResponseModel transFormEntityToModel(EmployeeEntity employeeEntity){
        EmployeeResponseModel EmployeeResponseModel = new EmployeeResponseModel();

        EmployeeResponseModel.setEmployeeNumber(employeeEntity.getEmployeeNumber());
        EmployeeResponseModel.setEmail(employeeEntity.getEmail());
        EmployeeResponseModel.setExtension(employeeEntity.getExtension());
        EmployeeResponseModel.setFirstName(employeeEntity.getFirstName());
        EmployeeResponseModel.setLastName(employeeEntity.getLastName());
        EmployeeResponseModel.setJobTitle(employeeEntity.getJobTitle());
        EmployeeResponseModel.setOfficeCode(employeeEntity.getOfficeCode());
        EmployeeResponseModel.setReportsTo(employeeEntity.getReportsTo());
        EmployeeResponseModel.setProfilePicture("/api/employee/profile-picture/"+employeeEntity.getEmployeeNumber());

        return EmployeeResponseModel;
    }

    public EmployeeEntity transFormModelToEntity(EmployeeResponseModel EmployeeResponseModel){
        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setEmployeeNumber(EmployeeResponseModel.getEmployeeNumber());
        employeeEntity.setEmail(EmployeeResponseModel.getEmail());
        employeeEntity.setExtension(EmployeeResponseModel.getExtension());
        employeeEntity.setFirstName(EmployeeResponseModel.getFirstName());
        employeeEntity.setLastName(EmployeeResponseModel.getLastName());
        employeeEntity.setJobTitle(EmployeeResponseModel.getJobTitle());
        employeeEntity.setOfficeCode(EmployeeResponseModel.getOfficeCode());
        employeeEntity.setReportsTo(EmployeeResponseModel.getReportsTo());

        return employeeEntity;
    }

    public EmployeeEntity transFormInsertModelToEntity(EmployeeInsertModel EmployeeResponseModel){
        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setEmployeeNumber(EmployeeResponseModel.getEmployeeNumber());
        employeeEntity.setEmail(EmployeeResponseModel.getEmail());
        employeeEntity.setExtension(EmployeeResponseModel.getExtension());
        employeeEntity.setFirstName(EmployeeResponseModel.getFirstName());
        employeeEntity.setLastName(EmployeeResponseModel.getLastName());
        employeeEntity.setJobTitle(EmployeeResponseModel.getJobTitle());
        employeeEntity.setOfficeCode(EmployeeResponseModel.getOfficeCode());
        employeeEntity.setReportsTo(EmployeeResponseModel.getReportsTo());

        return employeeEntity;
    }

    public void updateEntityByModel(EmployeeEntity employeeEntity, EmployeeResponseModel EmployeeResponseModel){

        employeeEntity.setEmail(EmployeeResponseModel.getEmail());
        employeeEntity.setExtension(EmployeeResponseModel.getExtension());
        employeeEntity.setFirstName(EmployeeResponseModel.getFirstName());
        employeeEntity.setLastName(EmployeeResponseModel.getLastName());
        employeeEntity.setJobTitle(EmployeeResponseModel.getJobTitle());
        employeeEntity.setOfficeCode(EmployeeResponseModel.getOfficeCode());
        employeeEntity.setReportsTo(EmployeeResponseModel.getReportsTo());

    }
}
