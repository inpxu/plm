package com.zhiyun.service.impl;

import com.zhiyun.dao.MattersStoreIosDao;
import com.zhiyun.internal.plm.MattersStoreDto;
import com.zhiyun.internal.plm.PlmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * plm服务接口实现类
 *
 * @author 邓艺
 * @version v1.0
 * @date 2018-10-17 13:32
 */
@Component("plmservice")
public class PlmServiceImpl implements PlmService {
    @Autowired
    private MattersStoreIosDao mattersStoreIosDao;


    @Override
    public List<MattersStoreDto> queryAllMatters(Long companyId) {
        return mattersStoreIosDao.queryAllMatters(companyId);
    }

    @Override
    public List<MattersStoreDto> queryAllMatters(Long companyId, Integer matterType) {
        return null;
    }
}
