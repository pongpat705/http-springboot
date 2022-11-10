package th.co.priorsolution.springboot.novice.restcontroller;

import org.springframework.web.bind.annotation.*;
import th.co.priorsolution.springboot.novice.model.EmployeeModel;
import th.co.priorsolution.springboot.novice.model.ResponseModel;
import th.co.priorsolution.springboot.novice.model.nativesql.EmployeeOfficeInfos;
import th.co.priorsolution.springboot.novice.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeApiRestController {

    private EmployeeService employeeService;

    public EmployeeApiRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/employee/{number}")
    public ResponseModel<EmployeeModel> getEmployeeByNumber(@PathVariable(name = "number") String number){
        return employeeService.getEmployeeByNumber(number);
    }

    @PostMapping("/employee")
    public ResponseModel<Void> insertEmployee(@RequestBody EmployeeModel employeeModel){
        return this.employeeService.insertEmployee(employeeModel);
    }

    @PatchMapping("/employee")
    public ResponseModel<Void> updateEmployee(@RequestBody EmployeeModel employeeModel){
        return this.employeeService.updateEmployee(employeeModel);
    }

    @GetMapping("/employee/employee-office-infos")
    public ResponseModel<List<EmployeeOfficeInfos>> getEmployeeOfficeInfo(@RequestBody EmployeeModel employeeModel){
        return employeeService.getEmployeeOfficeInfos(employeeModel);
    }
}
