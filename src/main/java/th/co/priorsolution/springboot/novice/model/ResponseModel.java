package th.co.priorsolution.springboot.novice.model;

import lombok.Data;

import java.util.List;

@Data
public class ResponseModel<T> {
    private int status;
    private String description;
    private List<ErrorModel> errors;
    private T data;
}
