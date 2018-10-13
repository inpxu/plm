package com.zhiyun.service;

import com.zhiyun.entity.CasUser;

import java.util.List;

/**
 * @author 邓艺
 * @version v1.0
 * @date 2018-09-05 16:48
 */
public interface CasUserService {
    List<CasUser> customeFind(CasUser casUser);
}
