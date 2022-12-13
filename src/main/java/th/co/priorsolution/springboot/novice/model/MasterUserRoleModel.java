package th.co.priorsolution.springboot.novice.model;

import lombok.Data;

@Data
public class MasterUserRoleModel {
    private Long userRoleId;

    private String userCode;
    private String roleName;
    private String objectName;

    public MasterUserRoleModel(Long userRoleId, String userCode, String roleName, String objectName) {
        this.userRoleId = userRoleId;
        this.userCode = userCode;
        this.roleName = roleName;
        this.objectName = objectName;
    }

    public MasterUserRoleModel() {
    }
}
