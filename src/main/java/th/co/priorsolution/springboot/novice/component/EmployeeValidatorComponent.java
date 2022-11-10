package th.co.priorsolution.springboot.novice.component;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import th.co.priorsolution.springboot.novice.model.EmployeeModel;
import th.co.priorsolution.springboot.novice.model.ErrorModel;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeValidatorComponent {

    public List<ErrorModel> validateEmployeeModel(EmployeeModel employeeModel) {
        List<ErrorModel> errorModelList = new ArrayList<>();

        if(StringUtils.isEmpty(employeeModel.getEmployeeNumber())){
            ErrorModel e = new ErrorModel();
            e.setCode("01");
            e.setDescription("employee number empty");
            errorModelList.add(e);
        }
        if(StringUtils.isEmpty(employeeModel.getFirstName())){
            ErrorModel e = new ErrorModel();
            e.setCode("01");
            e.setDescription("first name empty");
            errorModelList.add(e);
        }
        if(StringUtils.isEmpty(employeeModel.getLastName())){
            ErrorModel e = new ErrorModel();
            e.setCode("01");
            e.setDescription("last name empty");
            errorModelList.add(e);
        }
        if(StringUtils.isEmpty(employeeModel.getEmail())){
            ErrorModel e = new ErrorModel();
            e.setCode("01");
            e.setDescription("email empty");
            errorModelList.add(e);
        }
        if(StringUtils.isEmpty(employeeModel.getExtension())){
            ErrorModel e = new ErrorModel();
            e.setCode("01");
            e.setDescription("extension empty");
            errorModelList.add(e);
        }
        if(StringUtils.isEmpty(employeeModel.getOfficeCode())){
            ErrorModel e = new ErrorModel();
            e.setCode("01");
            e.setDescription("office Code empty");
            errorModelList.add(e);
        }
        if(StringUtils.isEmpty(employeeModel.getJobTitle())){
            ErrorModel e = new ErrorModel();
            e.setCode("01");
            e.setDescription("job title empty");
            errorModelList.add(e);
        }


        return errorModelList;

    }
}
