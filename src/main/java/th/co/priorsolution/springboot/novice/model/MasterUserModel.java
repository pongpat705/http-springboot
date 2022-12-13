package th.co.priorsolution.springboot.novice.model;

import lombok.Data;

@Data
public class MasterUserModel {
    private Long masterUserId;

    private String userName;
    private String password;

    public MasterUserModel(Long masterUserId, String userName, String password) {
        this.masterUserId = masterUserId;
        this.userName = userName;
        this.password = password;
    }

    public MasterUserModel() {
    }
}
