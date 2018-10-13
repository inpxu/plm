/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.ProdCrafworkResourceMesDao;
import com.zhiyun.dto.ProdCrafworkResourceMesDto;
import com.zhiyun.entity.ProdCrafworkResourceMes;
import com.zhiyun.service.ProdCrafworkResourceMesService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 产品工艺资料(文件、图纸)Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodCrafworkResourceMesService")
public class ProdCrafworkResourceMesServiceImpl extends BaseServiceImpl<ProdCrafworkResourceMes, Long> implements ProdCrafworkResourceMesService {

    private static final int MAX_PICTURE_NUM = 50;
    @Resource
    private ProdCrafworkResourceMesDao prodCrafworkResourceMesDao;

    @Override
    protected BaseDao<ProdCrafworkResourceMes, Long> getBaseDao() {
        return this.prodCrafworkResourceMesDao;
    }

    @Override
    public ProdCrafworkResourceMesDto add(ProdCrafworkResourceMesDto prodCrafworkResourceMesDto) {
        List<String> imgs = prodCrafworkResourceMesDto.getImages();
        String path = prodCrafworkResourceMesDto.getResPath();
        if (imgs == null && path == null) {
            throw new BusinessException("设计图稿或工程文件不能同时为空");
        }
        if (imgs.size() > MAX_PICTURE_NUM) {
            throw new BusinessException("设计图稿图片过多;");
        }
        ProdCrafworkResourceMes mes = new ProdCrafworkResourceMes();
        BeanUtils.copyProperties(prodCrafworkResourceMesDto, mes);
        String res = "";
        if (CollectionUtils.isNotEmpty(imgs)) {
            for (String img : imgs) {
                res = res + img + ",";
            }
        }
        mes.setResource(res.substring(0, res.length() - 1));
        prodCrafworkResourceMesDao.insert(mes);
        return prodCrafworkResourceMesDto;
    }

    @Override
    public List<ProdCrafworkResourceMesDto> get(ProdCrafworkResourceMes prodCrafworkResourceMes) {
        List<ProdCrafworkResourceMes> list = prodCrafworkResourceMesDao.find(prodCrafworkResourceMes);
        List<ProdCrafworkResourceMesDto> li = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ProdCrafworkResourceMes mes : list) {
                ProdCrafworkResourceMesDto dto = new ProdCrafworkResourceMesDto();
                List<String> imgs = new ArrayList<>();
                BeanUtils.copyProperties(mes, dto);
                imgs.addAll(Arrays.asList(mes.getResource().split(",")));
                dto.setImages(imgs);
                li.add(dto);
            }
        }
        return li;
    }

    @Override
    public ProdCrafworkResourceMesDto upd(ProdCrafworkResourceMesDto prodCrafworkResourceMesDto) {
        List<String> imgs = prodCrafworkResourceMesDto.getImages();
        String path = prodCrafworkResourceMesDto.getResPath();
        if (imgs == null && path == null) {
            throw new BusinessException("设计图稿或工程文件不能同时为空");
        }
        if (imgs.size() > MAX_PICTURE_NUM) {
            throw new BusinessException("设计图稿图片过多;");
        }
        ProdCrafworkResourceMes mes = new ProdCrafworkResourceMes();
        BeanUtils.copyProperties(prodCrafworkResourceMesDto, mes);
        String res = "";
        if (CollectionUtils.isNotEmpty(imgs)) {
            for (String img : imgs) {
                res = res + img + ",";
            }
        }
        mes.setResource(res.substring(0, res.length() - 1));
        prodCrafworkResourceMesDao.update(mes);
        return prodCrafworkResourceMesDto;
    }
}
