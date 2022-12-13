package th.co.priorsolution.springboot.novice.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import th.co.priorsolution.springboot.novice.model.MasterUserModel;

@Repository
@Slf4j
public class MasterUserRepositoryImpl implements MasterUserRepository {

    private JdbcTemplate jdbcTemplate;

    @Override
    public MasterUserModel findByUserNameAndPassword(String username, String password) {
        log.info("findByUserNameAndPassword");
        MasterUserModel result = null;
        if(username.equals("maoz")){
            result = new MasterUserModel(0L, "maoz", "password");
        } else  if(username.equals("rick")){
            result = new MasterUserModel(0L, "rick", "password");
        }
        return result;
    }
}
