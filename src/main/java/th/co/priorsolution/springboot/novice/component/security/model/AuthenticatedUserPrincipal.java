package th.co.priorsolution.springboot.novice.component.security.model;

import lombok.Data;
import th.co.priorsolution.springboot.novice.model.MasterUserModel;
import th.co.priorsolution.springboot.novice.model.MasterUserRoleModel;

import java.util.List;

@Data
public class AuthenticatedUserPrincipal {
    private MasterUserModel masterUserModel;
    private List<MasterUserRoleModel> masterUserRoleModels;
}
