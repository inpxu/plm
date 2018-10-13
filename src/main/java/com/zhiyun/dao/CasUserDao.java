package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.entity.CasUser;

import java.util.List;

public interface CasUserDao extends BaseDao<CasUser, Long> {

    Long insertSpace(Long userId);

    List<CasUser> customeFind(CasUser casUser);
}
