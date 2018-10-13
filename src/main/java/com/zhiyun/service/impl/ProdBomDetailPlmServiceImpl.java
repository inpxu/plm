/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.ProdBomDetailPlmDao;
import com.zhiyun.dto.ProdBomDetailPlmDto;
import com.zhiyun.entity.ProdBomDetailPlm;
import com.zhiyun.service.ProdBomDetailPlmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品物料构成明细表（BOM）Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodBomDetailPlmService")
public class ProdBomDetailPlmServiceImpl extends BaseServiceImpl<ProdBomDetailPlm, Long> implements ProdBomDetailPlmService {

    @Resource
    private ProdBomDetailPlmDao prodBomDetailPlmDao;

    @Override
    protected BaseDao<ProdBomDetailPlm, Long> getBaseDao() {
        return this.prodBomDetailPlmDao;
    }

    @Override
    public List<ProdBomDetailPlmDto> getMatter(ProdBomDetailPlmDto prodBomDetailPlmDto) {
        String prod = prodBomDetailPlmDto.getProdNo();
      //  String mid = prodBomDetailPlmDto.getMidNo();
        String parent = prodBomDetailPlmDto.getMidProdNo();
        //        if (prod != mid && parent == null) {
        //            throw new BusinessException("半成品编码不能为空");
        //        }
//        if (prod == mid) {
//            prodBomDetailPlmDto.setMidProdNo(null);
//        }
        return prodBomDetailPlmDao.getMatter(prodBomDetailPlmDto);
    }
}
