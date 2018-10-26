/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.MattersStoreDto;
import com.zhiyun.entity.MattersStoreIos;

import java.util.List;

/**
 * 物料库Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface MattersStoreIosService extends BaseService<MattersStoreIos, Long> {
    //新增物料库
    void insertStore(MattersStoreIos mattersStoreIos);

    //编辑物料库
    void updateStore(MattersStoreDto mattersStoreIos);

    //删除物料库
    void deletedStore(Long[] id);

    //查询物料库
    DataGrid<MattersStoreDto> select(Params params, Pager pager);

    // 模糊查询物料信息
    List<MattersStoreDto> getMatter(MattersStoreIos mattersStoreIos);

    /**
     * 物料下拉查询
     *
     * @param
     * @return java.util.List<com.zhiyun.entity.MattersStoreIos>
     * @author 邓艺
     * @date 2018/10/14 19:50
     */
    List<MattersStoreIos> mattersOption();
}
