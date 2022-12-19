package th.co.priorsolution.springboot.novice.restcontroller;

import org.springframework.web.bind.annotation.*;
import th.co.priorsolution.springboot.novice.model.EmployeeInsertModel;
import th.co.priorsolution.springboot.novice.model.EmployeeResponseModel;
import th.co.priorsolution.springboot.novice.model.ResponseModel;
import th.co.priorsolution.springboot.novice.model.nativesql.EmployeeOfficeInfos;
import th.co.priorsolution.springboot.novice.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeApiRestController {

    private EmployeeService employeeService;

    public EmployeeApiRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/employee/{number}")
    public ResponseModel<EmployeeResponseModel> getEmployeeByNumber(@PathVariable(name = "number") String number){
        return employeeService.getEmployeeByNumber(number);
    }

    @GetMapping("/employee/profile-picture/{number}")
    public void getEmployeeByNumber(@PathVariable(name = "number") String number
            , HttpServletRequest request, HttpServletResponse response){
        employeeService.getEmployeeProfilePictureByNumber(number, response);
    }

    @PostMapping("/employee/profile-picture")
    public ResponseModel<EmployeeResponseModel> uploadEmployeeByNumber(@ModelAttribute EmployeeInsertModel employeeInsertModel, HttpServletRequest request, HttpServletResponse response){
        return employeeService.uploadEmployeeProfilePicture(employeeInsertModel);
    }

    @PostMapping("/employee")
    public ResponseModel<Void> insertEmployee(@RequestBody EmployeeResponseModel employeeModel){
        return this.employeeService.insertEmployee(employeeModel.getEmployeeNumber(),employeeModel);
    }

    @PatchMapping("/employee")
    public ResponseModel<Void> updateEmployee(@RequestBody EmployeeResponseModel employeeModel){
        return this.employeeService.updateEmployee(employeeModel);
    }

    @GetMapping("/employee/employee-office-infos")
    public ResponseModel<List<EmployeeOfficeInfos>> getEmployeeOfficeInfo(@RequestBody EmployeeResponseModel employeeModel){
        return employeeService.getEmployeeOfficeInfos(employeeModel);
    }
}
