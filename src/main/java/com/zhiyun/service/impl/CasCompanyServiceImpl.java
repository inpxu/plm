package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.CasCompanyDao;
import com.zhiyun.entity.CasCompany;
import com.zhiyun.service.CasCompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("casCompanyService")
public class CasCompanyServiceImpl extends BaseServiceImpl<CasCompany, Long> implements CasCompanyService {

    @Resource
    private CasCompanyDao casCompanyDao;

    @Override
    protected BaseDao<CasCompany, Long> getBaseDao() {
        return this.casCompanyDao;
    }

    @Override
    public CasCompany customGet(Long companyId) {
        return casCompanyDao.customGet(companyId);
    }
}
