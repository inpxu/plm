/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.controller;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.*;
import com.zhiyun.entity.*;
import com.zhiyun.form.ProdCrafworkPathPlmForm;
import com.zhiyun.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 产品工艺路线
 *
 * @author xufei
 * @version v1.0
 * @date 2018-9-10 09:54:37
 */
@Controller
@RequestMapping(value = "/prodCrafPath", produces = "application/json; charset=UTF-8")
public class ProdCrafPathController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdCrafPathController.class);

    @Resource
    private ProdCrafworkPathPlmService prodCrafworkPathPlmService;
    @Resource
    private ProdCrafworkMainPlmService prodCrafworkMainPlmService;
    @Resource
    private ProductMidPlmService productMidPlmService;
    @Resource
    private ProductStorePlmService productStorePlmService;
    @Resource
    private CrafworkStructPlmService crafworkStructPlmService;
    @Resource
    private CrafworkParamPlmService crafworkParamPlmService;
    @Resource
    private ProdCrafworkParamPlmService prodCrafworkParamPlmService;

    /**
     * 添加工艺路线号码
     *
     * @param prodCrafworkMainPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-10 13:10:05
     */
    @ResponseBody
    @RequestMapping(value = "/addPathNo", method = {RequestMethod.GET, RequestMethod.POST})
    public Object addPathNo(ProdCrafworkMainPlm prodCrafworkMainPlm, BindingResult bindingResult) {
        BaseResult<ProdCrafworkMainPlm> baseResult = new BaseResult<ProdCrafworkMainPlm>();
        baseResult.setResult(true);
        baseResult.setMessage("添加成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodCrafworkMainPlm.setCompanyId(UserHolder.getCompanyId());
            prodCrafworkMainPlmService.addPathNo(prodCrafworkMainPlm);
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
     * 获取产品半成品对应的状态,版本
     *
     * @param prodCrafworkMainPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-19 10:25:18
     */
    @ResponseBody
    @RequestMapping(value = "/getSV", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getSV(@RequestBody ProdCrafworkMainPlm prodCrafworkMainPlm, BindingResult bindingResult) {
        BaseResult<ProdCrafworkMainPlm> baseResult = new BaseResult<ProdCrafworkMainPlm>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodCrafworkMainPlm.setCompanyId(UserHolder.getCompanyId());
            ProdCrafworkMainPlm pm = prodCrafworkMainPlmService.getSV(prodCrafworkMainPlm);
            baseResult.setModel(pm);
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
     * 产品查询侧边栏
     *
     * @param productStorePlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-10 15:22:28
     */
    @ResponseBody
    @RequestMapping(value = "/prodDrop", method = {RequestMethod.GET, RequestMethod.POST})
    public Object prodDrop(ProductStorePlm productStorePlm, BindingResult bindingResult) {
        BaseResult<List<ProductStorePlmDto>> baseResult = new BaseResult<List<ProductStorePlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            productStorePlm.setCompanyId(UserHolder.getCompanyId());
            List<ProductStorePlmDto> list = productStorePlmService.prodDrop(productStorePlm);
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
     * 产品侧边栏展开
     *
     * @param productMidPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-10 16:22:28
     */
    @ResponseBody
    @RequestMapping(value = "/prodDropDown", method = {RequestMethod.GET, RequestMethod.POST})
    public Object prodDropDown(@Valid ProductMidPlm productMidPlm, BindingResult bindingResult) {
        BaseResult<List<ProdMidDto>> baseResult = new BaseResult<List<ProdMidDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            productMidPlm.setCompanyId(UserHolder.getCompanyId());
            List<ProdMidDto> list = productMidPlmService.prodDropDown(productMidPlm);
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
     * 产品工艺池
     *
     * @param prodCrafworkPathPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-11 10:08:14
     */
    @ResponseBody
    @RequestMapping(value = "/crafworkStruct", method = {RequestMethod.GET, RequestMethod.POST})
    public Object crafworkStruct(ProdCrafworkPathPlm prodCrafworkPathPlm, BindingResult bindingResult) {
        BaseResult<List<ProdCrafworkPathPlmDto>> baseResult = new BaseResult<List<ProdCrafworkPathPlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodCrafworkPathPlm.setCompanyId(UserHolder.getCompanyId());
            List<ProdCrafworkPathPlmDto> list = prodCrafworkPathPlmService.crafworkStruct(prodCrafworkPathPlm);
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
     * 新增工艺查询显示
     *
     * @param crafworkStructDto
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-11 11:11:14
     */
    @ResponseBody
    @RequestMapping(value = "/addCraGet", method = {RequestMethod.GET, RequestMethod.POST})
    public Object addCraGet(CrafworkStructDto crafworkStructDto, BindingResult bindingResult) {
        BaseResult<List<CrafworkStructDto>> baseResult = new BaseResult<List<CrafworkStructDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            crafworkStructDto.setCompanyId(UserHolder.getCompanyId());
            List<CrafworkStructDto> list = crafworkStructPlmService.addGet(crafworkStructDto);
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
     * 新增工艺
     *
     * @param prodCrafworkPathPlmForm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-11 14:53:53
     */
    @ResponseBody
    @RequestMapping(value = "/addStruct", method = {RequestMethod.GET, RequestMethod.POST})
    public Object addStruct(@RequestBody ProdCrafworkPathPlmForm prodCrafworkPathPlmForm, BindingResult bindingResult) {
        BaseResult<List<ProdCrafworkPathPlm>> baseResult = new BaseResult<List<ProdCrafworkPathPlm>>();
        baseResult.setResult(true);
        baseResult.setMessage("添加成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            List<ProdCrafworkPathPlm> list = prodCrafworkPathPlmService.addStruct(prodCrafworkPathPlmForm);
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
     * 调整工艺顺序
     *
     * @param prodCrafworkPathPlmForm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-12 9:10:40
     */
    @ResponseBody
    @RequestMapping(value = "/upStruct", method = {RequestMethod.GET, RequestMethod.POST})
    public Object upStruct(@RequestBody ProdCrafworkPathPlmForm prodCrafworkPathPlmForm, BindingResult bindingResult) {
        BaseResult<ProdCrafworkPathPlmForm> baseResult = new BaseResult<ProdCrafworkPathPlmForm>();
        baseResult.setResult(true);
        baseResult.setMessage("调整完成！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodCrafworkPathPlmService.upStruct(prodCrafworkPathPlmForm);
            baseResult.setModel(prodCrafworkPathPlmForm);
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
     * 产品工艺池删除工艺
     *
     * @param prodCrafworkPathPlmDto
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-12 10:21:40
     */
    @ResponseBody
    @RequestMapping(value = "/delStruct", method = {RequestMethod.GET, RequestMethod.POST})
    public Object delStruct(ProdCrafworkPathPlmDto prodCrafworkPathPlmDto, BindingResult bindingResult) {
        BaseResult<ProdCrafworkPathPlmDto> baseResult = new BaseResult<ProdCrafworkPathPlmDto>();
        baseResult.setResult(true);
        baseResult.setMessage("删除成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodCrafworkPathPlmDto.setCompanyId(UserHolder.getCompanyId());
            prodCrafworkPathPlmService.delStruct(prodCrafworkPathPlmDto);
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
     * 产品工艺参数
     *
     * @param prodCrafworkParamPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-12 9:10:40
     */
    @ResponseBody
    @RequestMapping(value = "/crafworkParam", method = {RequestMethod.GET, RequestMethod.POST})
    public Object crafworkParam(@RequestBody ProdCrafworkParamPlm prodCrafworkParamPlm, BindingResult bindingResult) {
        BaseResult<List<ProdCrafworkParamPlm>> baseResult = new BaseResult<List<ProdCrafworkParamPlm>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodCrafworkParamPlm.setCompanyId(UserHolder.getCompanyId());
            List<ProdCrafworkParamPlm> list = prodCrafworkParamPlmService.find(prodCrafworkParamPlm);
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
     * 新增工艺池参数查询显示
     *
     * @param crafworkParamPlmDto
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-11 11:11:14
     */
    @ResponseBody
    @RequestMapping(value = "/addParamGet", method = {RequestMethod.GET, RequestMethod.POST})
    public Object addParamGet(@RequestBody CrafworkParamPlmDto crafworkParamPlmDto, BindingResult bindingResult) {
        BaseResult<List<CrafworkParamPlm>> baseResult = new BaseResult<List<CrafworkParamPlm>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            crafworkParamPlmDto.setCompanyId(UserHolder.getCompanyId());
            List<CrafworkParamPlm> list = crafworkParamPlmService.addParamGet(crafworkParamPlmDto);
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
     * 产品参数删除
     *
     * @param prodCrafworkParamPlmDto
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-12 14:05:56
     */
    @ResponseBody
    @RequestMapping(value = "/delParam", method = {RequestMethod.GET, RequestMethod.POST})
    public Object delParam(@RequestBody ProdCrafworkParamPlmDto prodCrafworkParamPlmDto, BindingResult bindingResult) {
        BaseResult<ProdCrafworkParamPlm> baseResult = new BaseResult<ProdCrafworkParamPlm>();
        baseResult.setResult(true);
        baseResult.setMessage("删除成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodCrafworkParamPlmDto.setCompanyId(UserHolder.getCompanyId());
            prodCrafworkParamPlmService.delParam(prodCrafworkParamPlmDto);
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
     * 添加产品参数
     *
     * @param prodCrafworkParamPlms
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-12 15:23:03
     */
    @ResponseBody
    @RequestMapping(value = "/addParam", method = {RequestMethod.GET, RequestMethod.POST})
    public Object addParam(@Valid @RequestBody List<ProdCrafworkParamPlm> prodCrafworkParamPlms, BindingResult bindingResult) {
        BaseResult<List<ProdCrafworkParamPlm>> baseResult = new BaseResult<List<ProdCrafworkParamPlm>>();
        baseResult.setResult(true);
        baseResult.setMessage("添加成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            if (CollectionUtils.isNotEmpty(prodCrafworkParamPlms)) {
                prodCrafworkParamPlmService.addParam(prodCrafworkParamPlms);
            }
            baseResult.setModel(prodCrafworkParamPlms);
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