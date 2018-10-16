/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.*;
import com.zhiyun.entity.ProdBomDetailPlm;
import com.zhiyun.entity.ProdBomPlm;
import com.zhiyun.service.ProdBomPlmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 产品bom controller
 *
 * @author 邓艺
 * @date 2018/10/7 12:53
 */
@Controller
@RequestMapping(value = "/bomPlm", produces = "application/json; charset=UTF-8")
public class ProdBomPlmController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdBomPlmController.class);

    @Resource
    private ProdBomPlmService prodBomPlmService;

    /**
     * 根据产品获取下属半成品
     *
     * @param prodBomPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-10 13:10:05
     */
    @RequestMapping(value = "/getBom", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getBom(@RequestBody ProdBomPlm prodBomPlm, BindingResult bindingResult) {
        BaseResult<ProdBomPlm> baseResult = new BaseResult<ProdBomPlm>();
        baseResult.setResult(true);
        baseResult.setMessage("添加成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodBomPlm.setCompanyId(UserHolder.getCompanyId());
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 通过产品名模糊查询产品
     *
     * @param productName 产品名
     * @param bomCode bom编码
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/7 13:29
     */
    @RequestMapping(value = "searchForProduct", method = RequestMethod.POST)
    @ResponseBody
    public String searchForProduct(String productName, String bomCode) {
        BaseResult<ProductStorePlmDto> baseResult = new BaseResult<>();
        try {
            ProductStorePlmDto productStorePlmDto = prodBomPlmService.searchForProduct(productName, bomCode);
            baseResult.setResult(true);
            baseResult.setMessage("查询成功");
            baseResult.setModel(productStorePlmDto);
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 通过产品编码或者半成品编码查询bom
     *
     * @param pNo
     * @param mpno
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/7 14:51
     */
    @RequestMapping(value = "findBomByPnoOrMpno", method = RequestMethod.POST)
    @ResponseBody
    public String findBomByPnoOrMpno(String pNo, String mpno) {
        BaseResult<ProdBomPlmDto> baseResult = new BaseResult<>();
        try {
            ProdBomPlmDto prodBomPlmDto = prodBomPlmService.findBomByPnoOrMpno(pNo, mpno);
            if (prodBomPlmDto == null) {
                baseResult.setResult(false);
                baseResult.setMessage("还未添加bom编码");
            } else {
                baseResult.setResult(true);
                baseResult.setMessage("查询成功");
                baseResult.setModel(prodBomPlmDto);
            }
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 通过产品编码或者半成品编码查询bom
     *
     * @param prodBomPlm
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/7 14:51
     */
    @RequestMapping(value = "addBomNo", method = RequestMethod.POST)
    @ResponseBody
    public String addBomNo(ProdBomPlm prodBomPlm) {
        BaseResult<ProdBomPlm> baseResult = new BaseResult<>();
        try {
            //bom编码唯一性校验
            prodBomPlmService.uniqueBomNo(prodBomPlm.getBomNo());
            prodBomPlm.setCompanyId(UserHolder.getCompanyId());
            prodBomPlm.setMakeEmp(UserHolder.getUserName());
            prodBomPlm.setMakeDate(new Date());
            prodBomPlm.setBomStatus("启用中");
            prodBomPlmService.addBomNo(prodBomPlm);
            baseResult.setResult(true);
            baseResult.setMessage("新增成功");
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 新增物料
     *
     * @param prodBomDetailPlms 物料数组
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/7 14:51
     */
    @RequestMapping(value = "addMatters", method = RequestMethod.POST)
    @ResponseBody
    public String addMatters(@RequestBody ProdBomDetailPlm[] prodBomDetailPlms) {
        BaseResult<List<ProdBomDetailPlmDto>> baseResult = new BaseResult<>();
        try {
            List<ProdBomDetailPlmDto> list = prodBomPlmService.addMatters(prodBomDetailPlms);
            baseResult.setMessage("新增成功");
            baseResult.setResult(true);
            baseResult.setModel(list);
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 通过物料编码或者物料名查询
     *
     * @param codeOrName 编码或者名称
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/9 12:44
     */
    @RequestMapping(value = "SearchBeforeAddMatters", method = RequestMethod.POST)
    @ResponseBody
    public String SearchBeforeAddMatters(String codeOrName, String parentNo) {
        BaseResult<List<MattersStoreDto>> baseResult = new BaseResult<>();
        try {
            List<MattersStoreDto> list = prodBomPlmService.SearchBeforeAddMatters(codeOrName, parentNo);
            baseResult.setResult(true);
            baseResult.setModel(list);
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 提交审核
     *
     * @param bomCode
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/7 14:51
     */
    @RequestMapping(value = "commit2Approve", method = RequestMethod.POST)
    @ResponseBody
    public String commit2Approve(String bomCode) {
        BaseResult<String> baseResult = new BaseResult<>();
        try {
            prodBomPlmService.commit2Approve(bomCode);
            baseResult.setResult(true);
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 删除物料
     *
     * @param ids 物料id
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/8 9:10
     */
    @RequestMapping(value = "deleteMatters", method = RequestMethod.POST)
    @ResponseBody
    public String deleteMatters(Long[] ids) {
        BaseResult<String> baseResult = new BaseResult<>();
        try {
            prodBomPlmService.deleteMatters(ids);
            baseResult.setResult(true);
            baseResult.setMessage("删除成功");
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 更新物料
     *
     * @param prodBomDetailPlm
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/8 9:10
     */
    @RequestMapping(value = "updateMatter", method = RequestMethod.POST)
    @ResponseBody
    public String updateMatter(@RequestBody ProdBomDetailPlm prodBomDetailPlm) {
        BaseResult<String> baseResult = new BaseResult<>();
        try {
            prodBomPlmService.updateMatter(prodBomDetailPlm);
            baseResult.setResult(true);
            baseResult.setMessage("更新成功");
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 启停bom
     *
     * @param bomCode bom编码
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/8 9:10
     */
    @RequestMapping(value = "startOrStopBom", method = RequestMethod.POST)
    @ResponseBody
    public String startOrStopBom(String bomCode) {
        BaseResult<String> baseResult = new BaseResult<>();
        try {
            prodBomPlmService.startOrStopBom(bomCode);
            baseResult.setResult(true);
            baseResult.setMessage("操作成功");
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 通用组件搜索
     *
     * @param productName 产品名
     * @param bomCode bom编码
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/7 13:29
     */
    @RequestMapping(value = "searchForCompnent", method = RequestMethod.POST)
    @ResponseBody
    public String searchForCompnent(String productName, String bomCode) {
        BaseResult<ProdBomPlm> baseResult = new BaseResult<>();
        try {
            ProdBomPlm prodBomPlm = prodBomPlmService.searchForCompnent(productName, bomCode);
            baseResult.setResult(true);
            baseResult.setModel(prodBomPlm);
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 下拉查询bom编码及产品名
     *
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/8 14:40
     */
    @RequestMapping(value = "optionBomCodeAndProdName", method = RequestMethod.GET)
    @ResponseBody
    public String optionBomCodeAndProdName() {
        BaseResult<List<MattersStoreDto>> baseResult = new BaseResult<>();
        try {
            List<MattersStoreDto> mattersStoreDtos = prodBomPlmService.optionBomCodeAndProdName();
            baseResult.setResult(true);
            baseResult.setModel(mattersStoreDtos);
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 查询半成品
     *
     * @param parentNo
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/9 15:45
     */
    @RequestMapping(value = "findMidProduct", method = RequestMethod.POST)
    @ResponseBody
    public String findMidProduct(String parentNo) {
        BaseResult<List<ProductMidPlmDto>> baseResult = new BaseResult<>();
        try {
            List<ProductMidPlmDto> prodBomPlms = prodBomPlmService.findMidProduct(parentNo);
            baseResult.setResult(true);
            baseResult.setModel(prodBomPlms);
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 修改数量
     *
     * @param isMidProduct
     * @param id
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/10 14:18
     */
    @RequestMapping(value = "changeNumber", method = RequestMethod.POST)
    @ResponseBody
    public String changeNumber(boolean isMidProduct, Long id, Long number, Long numberBefore) {
        BaseResult<List<ProductMidPlmDto>> baseResult = new BaseResult<>();
        try {
            //TODO 修改数量
            prodBomPlmService.changeNumber(isMidProduct, id, number, numberBefore);
            baseResult.setResult(true);
            baseResult.setModel(null);
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

}