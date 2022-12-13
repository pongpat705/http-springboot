package th.co.priorsolution.springboot.novice.component.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import th.co.priorsolution.springboot.novice.model.ResponseModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        writeCustomResponse(response, accessDeniedException);
    }

    private void writeCustomResponse(HttpServletResponse response, AccessDeniedException accessDeniedException) {
        if (!response.isCommitted()) {
            try {
                ResponseModel<Void> result = new ResponseModel<>();
                result.setStatus(403);
                result.setDescription(accessDeniedException.getLocalizedMessage());

                response.setContentType("application/json");
                response.setStatus(HttpStatus.OK.value());
                response.getWriter().write(new ObjectMapper().writeValueAsString(result));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
