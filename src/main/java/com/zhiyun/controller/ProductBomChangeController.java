package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.ProdBomPlmDto;
import com.zhiyun.service.ProdBomPlmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 产品bom变更申请controller
 *
 * @author 邓艺
 * @version v1.0
 * @date 2018-10-08 14:29
 */
@Slf4j
@RestController
@RequestMapping("productBomChange")
public class ProductBomChangeController {
    @Autowired
    private ProdBomPlmService prodBomPlmService;

    /**
     * 产品bom变更申请分页查询
     *
     * @param vocherNo 单据号
     * @param productName 产品名
     * @param bomCode bom编码
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/8 14:33
     */
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public String searchForProduct(String vocherNo, String productName, String bomCode, Pager pager) {
        BaseResult<DataGrid<ProdBomPlmDto>> baseResult = new BaseResult<>();
        try {
            Map<String, Object> param = new HashMap<>(5);
            param.put("vocherNo", vocherNo);
            param.put("productName", productName);
            param.put("bomCode", bomCode);
            param.put("companyId", UserHolder.getCompanyId());
            DataGrid<ProdBomPlmDto> page = prodBomPlmService.page(Params.create().add(param), pager);
            baseResult.setResult(true);
            baseResult.setMessage("查询成功");
            baseResult.setModel(page);
        } catch (BusinessException be) {
            log.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            log.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

}
