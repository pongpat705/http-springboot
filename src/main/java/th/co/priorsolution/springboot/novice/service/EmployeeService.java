package th.co.priorsolution.springboot.novice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import th.co.priorsolution.springboot.novice.component.EmployeeModelTransformComponent;
import th.co.priorsolution.springboot.novice.component.EmployeeValidatorComponent;
import th.co.priorsolution.springboot.novice.component.security.model.AuthenticatedUsers;
import th.co.priorsolution.springboot.novice.entity.EmployeeEntity;
import th.co.priorsolution.springboot.novice.logging.model.ComplexLog;
import th.co.priorsolution.springboot.novice.model.EmployeeInsertModel;
import th.co.priorsolution.springboot.novice.model.EmployeeResponseModel;
import th.co.priorsolution.springboot.novice.model.EmployeeResponseModel;
import th.co.priorsolution.springboot.novice.model.ErrorModel;
import th.co.priorsolution.springboot.novice.model.ResponseModel;
import th.co.priorsolution.springboot.novice.model.nativesql.EmployeeOfficeInfos;
import th.co.priorsolution.springboot.novice.repository.EmployeeRepository;
import th.co.priorsolution.springboot.novice.repository.custom.EmployeeCustomRepositoryImpl;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

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

    public ResponseModel<EmployeeResponseModel> getEmployeeByNumber(String number){
        ResponseModel<EmployeeResponseModel> result = new ResponseModel<>();
        result.setStatus(404);
        result.setDescription("employee not found");

        AuthenticatedUsers x = (AuthenticatedUsers) SecurityContextHolder.getContext().getAuthentication();


        log.info("getEmployeeByNumber {} by {}", number
                ,  x.getCredentials().getUsername()
                , ComplexLog.builder().key(number).build().toMap()
        );
        try{
            Optional<EmployeeEntity> optionalEmployee = this.employeeRepository.findById(number);

            if(optionalEmployee.isPresent()){
                EmployeeEntity employeeEntity = optionalEmployee.get();
                EmployeeResponseModel data = this.employeeModelTransformComponent.transFormEntityToModel(employeeEntity);
                result.setData(data);
                result.setStatus(200);
                result.setDescription("");
                log.info("getEmployeeByNumber {} response 200", number, ComplexLog.builder()
                        .key(number)
                                .data(data)
                        .build().toMap());
            }
        } catch (Exception e) {
            log.info("getEmployeeByNumber error {}",e.getMessage(),
                    ComplexLog.builder().key(number).build().toMap());
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }

        return result;
    }


    public void getEmployeeProfilePictureByNumber(String number, HttpServletResponse response){

        AuthenticatedUsers x = (AuthenticatedUsers) SecurityContextHolder.getContext().getAuthentication();


        log.info("getEmployeeProfilePictureByNumber {} by {}", number
                ,  x.getCredentials().getUsername()
                , ComplexLog.builder().key(number).build().toMap()
        );
        try{
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            OutputStream outputStream = response.getOutputStream();

            Optional<EmployeeEntity> optionalEmployee = this.employeeRepository.findById(number);

            if(optionalEmployee.isPresent()){
                EmployeeEntity employeeEntity = optionalEmployee.get();
//                get picture
                InputStream in = new FileInputStream(employeeEntity.getProfilePicture());
                outputStream.write(IOUtils.toByteArray(in));
                outputStream.flush();
            }

        } catch (Exception e) {
            log.info("getEmployeeProfilePictureByNumber error {}",e.getMessage());

            ResponseModel<Void> result = new ResponseModel<>();
            result.setStatus(500);
            result.setDescription("getEmployeeProfilePictureByNumber error "+e.getMessage());
            ObjectMapper mapper = new ObjectMapper();
            response.setHeader("Content-Disposition"
                    , "inline");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            OutputStream outputStream = null;
            try {
                outputStream = response.getOutputStream();
                outputStream.write(mapper.writeValueAsBytes(result));
                outputStream.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }

    }

    public ResponseModel<EmployeeResponseModel> uploadEmployeeProfilePicture(EmployeeInsertModel employeeInsertModel){
        ResponseModel<EmployeeResponseModel> result = new ResponseModel<>();
        result.setStatus(404);
        result.setDescription("employee not found");

        AuthenticatedUsers x = (AuthenticatedUsers) SecurityContextHolder.getContext().getAuthentication();


        log.info("uploadEmployeeProfilePicture {} by {}", employeeInsertModel.getFirstName()
                ,  x.getCredentials().getUsername()
                , ComplexLog.builder().key(employeeInsertModel.getFirstName()).build().toMap()
        );
        try{
            EmployeeEntity employeeEntity = this.employeeModelTransformComponent.transFormInsertModelToEntity(employeeInsertModel);

            //save image to folder
            String fileLocation = "/home/pongpat/Pictures/"+LocalDateTime.now().getNano()+".png";
            employeeEntity.setProfilePicture(fileLocation);

            File fileToWrite = new File(fileLocation);
            FileOutputStream fo = new FileOutputStream(fileToWrite);
            fo.write(employeeInsertModel.getProfilePicture().getBytes());
            fo.flush();
            fo.close();
            //save data to db
            this.employeeRepository.save(employeeEntity);
            //transform response
            EmployeeResponseModel employeeResponseModel = this.employeeModelTransformComponent
                    .transFormEntityToModel(employeeEntity);
            result.setData(employeeResponseModel);
            result.setStatus(200);
            result.setDescription("");
            log.info("uploadEmployeeProfilePicture {} response 200", employeeResponseModel
                    .getEmployeeNumber()

                    , ComplexLog.builder()
                    .key(employeeResponseModel.getEmployeeNumber())
                    .data(employeeResponseModel)
                    .build().toMap());

        } catch (Exception e) {
            log.info("getEmployeeByNumber error {}",e.getMessage(),
                    ComplexLog.builder().key(employeeInsertModel.getFirstName()).build().toMap());
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }

        return result;

    }


    public ResponseModel<Void> insertEmployee(String employeeNumber, EmployeeResponseModel employeeModel){
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

    public ResponseModel<Void> updateEmployee(EmployeeResponseModel employeeModel){
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


    public ResponseModel<List<EmployeeOfficeInfos>> getEmployeeOfficeInfos(EmployeeResponseModel employeeModel){
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
