package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.CasCompanyDao;
import com.zhiyun.entity.CasCompany;
import org.springframework.stereotype.Repository;

@Repository("casCompanyDao")
public class CasCompanyDaoImpl extends BaseDaoImpl<CasCompany, Long> implements CasCompanyDao {

    @Override
    public CasCompany customGet(Long companyId) {
        return this.selectOne(getMethodName(), companyId);
    }
}
