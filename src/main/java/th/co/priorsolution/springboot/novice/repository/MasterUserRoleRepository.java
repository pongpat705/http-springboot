package th.co.priorsolution.springboot.novice.repository;

import th.co.priorsolution.springboot.novice.model.MasterUserRoleModel;

import java.util.List;

public interface MasterUserRoleRepository {

    List<MasterUserRoleModel> findByMasterUserName(String userName);
}
