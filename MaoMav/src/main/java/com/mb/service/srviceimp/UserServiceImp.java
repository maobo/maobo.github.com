package com.mb.service.srviceimp;

import com.mb.dao.UserDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by Maobo on 2016/10/17.
 */
@Service
public class UserServiceImp implements UserService {

    @Resource
     private UserDao userDaoimp;

    public void findUser() {
        System.out.println("----service  find ---------");
        userDaoimp.findUser();
    }
}
