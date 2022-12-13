package th.co.priorsolution.springboot.novice.repository;

import th.co.priorsolution.springboot.novice.model.MasterUserModel;

public interface MasterUserRepository {

    public MasterUserModel findByUserNameAndPassword(String username, String password);
}
