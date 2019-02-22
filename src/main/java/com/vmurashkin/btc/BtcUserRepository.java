package com.vmurashkin.btc;

import com.vmurashkin.btc.model.BtcUser;
import org.springframework.data.repository.CrudRepository;

public interface BtcUserRepository extends CrudRepository<BtcUser, String> {

    BtcUser findBtcUserById (Integer id);

}
