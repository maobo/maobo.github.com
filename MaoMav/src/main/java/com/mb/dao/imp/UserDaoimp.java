package com.mb.dao.imp;

import com.mb.dao.BaseDao;
import com.mb.dao.UserConDao;
import com.mb.dao.UserDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Maobo on 2016/10/17.
 */
@Repository
public class UserDaoimp  implements UserDao{
//    @Resource
//    private BaseDao userConDao;

    public void findUser() {
        BaseDao userConDao = new UserConDao();
       for(int i =0;i<10;i++){
           ResultSet rs = userConDao.query("select * from stu");
           try {
               while(rs.next()){
                   System.out.println(rs.getString("name")+i);
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }finally {
               userConDao.closeResult(rs);
           }
       }
        System.out.println("======dao find user========");
    }
}
