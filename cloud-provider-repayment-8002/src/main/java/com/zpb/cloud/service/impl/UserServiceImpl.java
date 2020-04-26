package com.zpb.cloud.service.impl;

import com.zpb.cloud.dao.UserDao;
import com.zpb.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @dec :
 * @Date: 2020/4/15
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public Integer update(Long id, Integer age) {
        return userDao.update(1L, 3000);
    }
}
