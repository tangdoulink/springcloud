package com.zpb.cloud.service;

import org.apache.ibatis.annotations.Param;

/**
 * @dec :
 * @Date: 2020/4/15
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
public interface UserService {

    public Integer update(Long id, Integer age);
}
