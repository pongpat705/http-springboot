package th.co.priorsolution.springboot.novice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import th.co.priorsolution.springboot.novice.component.EmployeeModelTransformComponent;
import th.co.priorsolution.springboot.novice.component.EmployeeValidatorComponent;
import th.co.priorsolution.springboot.novice.entity.EmployeeEntity;
import th.co.priorsolution.springboot.novice.model.EmployeeModel;
import th.co.priorsolution.springboot.novice.model.ErrorModel;
import th.co.priorsolution.springboot.novice.model.ResponseModel;
import th.co.priorsolution.springboot.novice.model.nativesql.EmployeeOfficeInfos;
import th.co.priorsolution.springboot.novice.repository.EmployeeRepository;
import th.co.priorsolution.springboot.novice.repository.custom.EmployeeCustomRepositoryImpl;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeModelTransformComponent employeeModelTransformComponent;
    private final EmployeeValidatorComponent employeeValidatorComponent;
    private final EmployeeCustomRepositoryImpl employeeCustomRepository;

    public EmployeeService(@Qualifier("employeeRepository") EmployeeRepository employeeRepository
            , EmployeeModelTransformComponent employeeModelTransformComponent
            , EmployeeValidatorComponent employeeValidatorComponent
            , EmployeeCustomRepositoryImpl employeeCustomRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeModelTransformComponent = employeeModelTransformComponent;
        this.employeeValidatorComponent = employeeValidatorComponent;
        this.employeeCustomRepository = employeeCustomRepository;
    }

    public ResponseModel<EmployeeModel> getEmployeeByNumber(String number){
        ResponseModel<EmployeeModel> result = new ResponseModel<>();
        result.setStatus(404);
        result.setDescription("employee not found");

        log.info("getEmployeeByNumber {}", number);
        try{
            Optional<EmployeeEntity> optionalEmployee = this.employeeRepository.findById(number);

            if(optionalEmployee.isPresent()){
                EmployeeEntity employeeEntity = optionalEmployee.get();
                EmployeeModel data = this.employeeModelTransformComponent.transFormEntityToModel(employeeEntity);
                result.setData(data);
                result.setStatus(200);
                result.setDescription("");
            }
        } catch (Exception e) {
            log.info("getEmployeeByNumber error {}",e.getMessage());
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }

        return result;
    }


    public ResponseModel<Void> insertEmployee(EmployeeModel employeeModel){
        ResponseModel<Void> result = new ResponseModel<>();
        result.setStatus(500);

        log.info("insertEmployee {}", employeeModel);
        try{
            List<ErrorModel> errorModelList = this.employeeValidatorComponent
                    .validateEmployeeModel(employeeModel);

            if(errorModelList.isEmpty()){//mean no error
                EmployeeEntity employeeEntity = this.employeeModelTransformComponent
                        .transFormModelToEntity(employeeModel);

                this.employeeRepository.save(employeeEntity);

                result.setStatus(201);
            } else {
                result.setErrors(errorModelList);
                result.setStatus(501);
                result.setDescription("input invalid");
            }
        } catch (Exception e) {
            log.info("getEmployeeByNumber error {}",e.getMessage());
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }

        return result;
    }

    public ResponseModel<Void> updateEmployee(EmployeeModel employeeModel){
        ResponseModel<Void> result = new ResponseModel<>();
        result.setStatus(500);
        log.info("updateEmployee {}", employeeModel);
        try{
            List<ErrorModel> errorModelList = this.employeeValidatorComponent
                    .validateEmployeeModel(employeeModel);

            if(errorModelList.isEmpty()){//mean no error

                Optional<EmployeeEntity> optionalEmployee = this.employeeRepository.findById(employeeModel.getEmployeeNumber());

                if(optionalEmployee.isPresent()){
                    EmployeeEntity employeeEntity = optionalEmployee.get();
                    this.employeeModelTransformComponent.updateEntityByModel(employeeEntity, employeeModel);
                    this.employeeRepository.save(employeeEntity);
                }
                result.setStatus(201);
            } else {
                result.setErrors(errorModelList);
                result.setStatus(501);
                result.setDescription("input invalid");
            }
        } catch (Exception e) {
            log.info("getEmployeeByNumber error {}",e.getMessage());
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }

        return result;
    }


    public ResponseModel<List<EmployeeOfficeInfos>> getEmployeeOfficeInfos(EmployeeModel employeeModel){
        ResponseModel<List<EmployeeOfficeInfos>> result = new ResponseModel<>();
        result.setStatus(404);
        result.setDescription("EmployeeOfficeInfos not found");

        log.info("getEmployeeOfficeInfos {}", employeeModel);
        try{
            List<EmployeeOfficeInfos> employeeOfficeInfos = this.employeeCustomRepository.getEmployInfos(employeeModel);
            result.setData(employeeOfficeInfos);
            result.setStatus(200);
            result.setDescription("");
        } catch (Exception e) {
            log.info("getEmployeeOfficeInfos error {}",e.getMessage());
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }

        return result;
    }


}
