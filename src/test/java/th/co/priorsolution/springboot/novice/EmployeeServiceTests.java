package th.co.priorsolution.springboot.novice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import th.co.priorsolution.springboot.novice.component.EmployeeModelTransformComponent;
import th.co.priorsolution.springboot.novice.component.EmployeeValidatorComponent;
import th.co.priorsolution.springboot.novice.model.EmployeeResponseModel;
import th.co.priorsolution.springboot.novice.model.ResponseModel;
import th.co.priorsolution.springboot.novice.repository.EmployeeRepository;
import th.co.priorsolution.springboot.novice.repository.EmployeeRepositoryTestOkImpl;
import th.co.priorsolution.springboot.novice.service.EmployeeService;

import java.io.IOException;

public class EmployeeServiceTests {


    @Test
    public void testInsertEmployeeExpectStatus501() throws IOException {

        EmployeeService employeeService = new EmployeeService(null, null, new EmployeeValidatorComponent(), null);

//		mock input value
        EmployeeResponseModel employeeModel = new EmployeeResponseModel();
//		assertion
        ResponseModel<Void> result = employeeService.insertEmployee(employeeModel);
        Assertions.assertTrue(result.getStatus() == 501);
    }

    @Test
    public void testInsertEmployeeExpectEmployeeNumberEmpty() throws IOException {
//		mock input value
        EmployeeResponseModel employeeModel = new EmployeeResponseModel();
        employeeModel.setExtension("x");
        employeeModel.setEmail("x");
        employeeModel.setLastName("x");
        employeeModel.setJobTitle("x");
        employeeModel.setOfficeCode("x");
        employeeModel.setFirstName("x");
        employeeModel.setReportsTo("x");
//		assertion
//        ResponseModel<Void> result = employeeService.insertEmployee(employeeModel);
//        Assertions.assertTrue(result.getErrors().size() == 1);
//        Assertions.assertTrue(result.getErrors().get(0).getDescription().equals("employee number empty"));
    }

    @Test
    public void testInsertEmployeeExpectStatus201() {
        EmployeeRepository employeeRepository = new EmployeeRepositoryTestOkImpl();

        EmployeeService employeeService = new EmployeeService(employeeRepository, new EmployeeModelTransformComponent(), new EmployeeValidatorComponent(), null);
//		mock input value
        EmployeeResponseModel employeeModel = new EmployeeResponseModel();
        employeeModel.setEmployeeNumber("x");
        employeeModel.setExtension("x");
        employeeModel.setEmail("x");
        employeeModel.setLastName("x");
        employeeModel.setJobTitle("x");
        employeeModel.setOfficeCode("x");
        employeeModel.setReportsTo("x");
//		assertion


        ResponseModel<Void> result = employeeService.insertEmployee(employeeModel);
        Assertions.assertTrue(result.getStatus() == 201);
    }

}
