package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.entity.CasCompany;

public interface CasCompanyDao extends BaseDao<CasCompany, Long> {

    CasCompany customGet(Long companyId);
}
