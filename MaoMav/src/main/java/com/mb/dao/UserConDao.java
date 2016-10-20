package com.mb.dao;

import org.springframework.stereotype.Component;

/**
 * Created by Maobo on 2016/10/18.
 */
@Component
public class UserConDao extends    BaseDao{

    @Override
    public void getConnection() {
        try {
            con = C3P0Utils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
