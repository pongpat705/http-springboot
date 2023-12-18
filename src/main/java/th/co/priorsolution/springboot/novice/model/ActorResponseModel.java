package th.co.priorsolution.springboot.novice.model;

import lombok.Data;
import th.co.priorsolution.springboot.novice.annotations.Hash;

@Data
public class ActorResponseModel {
    @Hash
    private String name;
    private String lastName;
}
