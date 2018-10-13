package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.CasUserDao;
import com.zhiyun.entity.CasUser;
import com.zhiyun.service.CasUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("casUserService")
public class CasUserServiceImpl extends BaseServiceImpl<CasUser, Long> implements CasUserService {

    @Resource
    private CasUserDao casUserDao;

    @Override
    protected BaseDao<CasUser, Long> getBaseDao() {
        return this.casUserDao;
    }

    @Override
    public List<CasUser> customeFind(CasUser casUser) {
        return casUserDao.customeFind(casUser);
    }

}
