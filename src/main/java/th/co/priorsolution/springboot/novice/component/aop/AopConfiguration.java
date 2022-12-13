package th.co.priorsolution.springboot.novice.component.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import th.co.priorsolution.springboot.novice.entity.EmployeeEntity;
import th.co.priorsolution.springboot.novice.logging.model.ComplexLog;
import th.co.priorsolution.springboot.novice.model.EmployeeModel;
import th.co.priorsolution.springboot.novice.model.ResponseModel;

import java.util.Arrays;
import java.util.Optional;

@Component
@Aspect
@Slf4j
public class AopConfiguration {

    @Pointcut("within(th.co.priorsolution.springboot.novice.service.EmployeeService.*)")
    public void allMethodsPointcut(){}
    @Before("execution(* th.co.priorsolution.springboot.novice.service.EmployeeService.*(*))")
    public void doLogBeforeEmployeeService(JoinPoint joinPoint){
        log.info("Before running loggingAdvice on method="+joinPoint.toString());
        log.info("Before Arguments Passed=" + Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(pointcut="execution(* th.co.priorsolution.springboot.novice.service.EmployeeService.*(*))"
            , returning="result")
    public void doLogAfterEmployeeService(Object result){
        log.info("AfterReturning getEmployeeByNumber executed . Returned String="+result);
    }
    @Around("execution(* th.co.priorsolution.springboot.novice.service.EmployeeService.insertEmployee(*))")
    public Object doLogAroundEmployeeService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Around Before invoking getName() method");
        Object[] args = proceedingJoinPoint.getArgs();
        EmployeeModel employeeModels = (EmployeeModel) args[0];

//        execute target method
        Object value = proceedingJoinPoint.proceed(args);
//        after execute target method
        ResponseModel<Void> result = (ResponseModel<Void>) value;


        log.info("Around After invoking getName() method. Return value="+value);
        return value;
    }


}
