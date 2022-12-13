package th.co.priorsolution.springboot.novice.restcontroller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import th.co.priorsolution.springboot.novice.model.ResponseModel;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body
            , HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseModel<Void> result = new ResponseModel<>();
        result.setStatus(status.value());
        result.setDescription(ex.getLocalizedMessage());

        return super.handleExceptionInternal(ex, result, headers, HttpStatus.OK, request);
    }

}
