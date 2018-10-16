/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.FormulaDto;
import com.zhiyun.entity.FormulaBomPlm;

/**
 * Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface FormulaBomPlmService extends BaseService<FormulaBomPlm, Long> {

    /**
     * 新增或修改配方
     *
     * @param formulaBomPlm
     * @return void
     * @author 邓艺
     * @date 2018/10/11 16:14
     */
    void addOrUpdateFormula(FormulaBomPlm formulaBomPlm);

    /**
     * 根据id数组删除
     *
     * @param ids
     * @return void
     * @author 邓艺
     * @date 2018/10/11 16:27
     */
    void deleteByIds(Long[] ids);

    /**
     * 通过产品编码查询配方bom
     *
     * @param pNo
     * @return com.zhiyun.dto.FormulaDto
     * @author 邓艺
     * @date 2018/10/14 18:36
     */
    FormulaDto findBomByPnoOrMpno(String pNo);
}
