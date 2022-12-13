package th.co.priorsolution.springboot.novice.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import th.co.priorsolution.springboot.novice.model.MasterUserRoleModel;

import java.util.Arrays;
import java.util.List;

@Repository
@Slf4j
public class MasterUserRoleRepositoryImpl implements MasterUserRoleRepository {
    @Override
    public List<MasterUserRoleModel> findByMasterUserName(String userName) {
        log.info("findByMasterUserName");
        List<MasterUserRoleModel> result = null;
        if("maoz".equals(userName)){
            result = Arrays.asList(new MasterUserRoleModel(0L, "xxx", "ROLE_STAFF", "ROLE_STAFF")) ;
        } else if ("rick".equals(userName)){
            result = Arrays.asList(new MasterUserRoleModel(0L, "xxx", "ccc", "ccc")) ;
        }
        return result;
    }
}
