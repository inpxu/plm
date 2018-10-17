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
import com.zhiyun.dto.CrafworkStructDto;
import com.zhiyun.dto.ProdCrafworkMainPlmDto;
import com.zhiyun.dto.ProdCrafworkPathPlmDto;
import com.zhiyun.dto.ProdMidDto;
import com.zhiyun.entity.ProdCrafworkPathPlm;
import com.zhiyun.entity.ProductMidPlm;
import com.zhiyun.service.CrafworkStructPlmService;
import com.zhiyun.service.ProdCrafworkMainPlmService;
import com.zhiyun.service.ProdCrafworkPathPlmService;
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
 * 工艺路线主表
 *
 * @author xufei
 */
@Controller
@RequestMapping(value = "/crafMain", produces = "application/json;charset=UTF-8")
public class ProdCrafworkMainController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProdCrafworkMainController.class);

    @Resource
    private ProdCrafworkMainPlmService prodCrafworkMainPlmService;
    @Resource
    private ProdCrafworkPathPlmService prodCrafworkPathPlmService;
    @Resource
    private CrafworkStructPlmService crafworkStructPlmService;

    /**
     * 路线号模糊查询
     *
     * @param prodCrafworkMainPlm
     * @return
     * @author xufei
     * @date 2018-9-22 11:30:39
     */
    @ResponseBody
    @RequestMapping(value = "/getPathMsg ", method = {RequestMethod.POST})
    public Object getPathMsg(ProdCrafworkMainPlmDto prodCrafworkMainPlm) {
        BaseResult<List<ProdCrafworkMainPlmDto>> baseResult = new BaseResult<List<ProdCrafworkMainPlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("下拉获取成功！");
        try {
            List<ProdCrafworkMainPlmDto> list = prodCrafworkMainPlmService.getPathMsg(prodCrafworkMainPlm);
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
     * 产品模糊查询
     *
     * @param prodCrafworkMainPlmDto
     * @return
     * @author xufei
     * @date 2018-9-22 13:31:16
     */
    @ResponseBody
    @RequestMapping(value = "/getProdMsg ", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getProdMsg(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto, BindingResult bindingResult) {
        BaseResult<List<ProdCrafworkMainPlmDto>> baseResult = new BaseResult<List<ProdCrafworkMainPlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("下拉获取成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            List<ProdCrafworkMainPlmDto> list = prodCrafworkMainPlmService.getProdMsg(prodCrafworkMainPlmDto);
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
     * 产品信息获取
     *
     * @param prodCrafworkMainPlmDto
     * @return
     * @author xufei
     * @date 2018-9-22 14:39:13
     */
    @ResponseBody
    @RequestMapping(value = "/findMainMess ", method = {RequestMethod.GET, RequestMethod.POST})
    public Object findMainMess(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto, BindingResult bindingResult) {
        BaseResult<List<ProdCrafworkMainPlmDto>> baseResult = new BaseResult<List<ProdCrafworkMainPlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            List<ProdCrafworkMainPlmDto> dto = prodCrafworkMainPlmService.findMainMess(prodCrafworkMainPlmDto);
            baseResult.setModel(dto);
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
     * 产品信息展开
     *
     * @param productMidPlm
     * @return
     * @author xufei
     * @date 2018-9-25 15:58:14
     */
    @ResponseBody
    @RequestMapping(value = "/prodDown ", method = {RequestMethod.GET, RequestMethod.POST})
    public Object prodDown(ProductMidPlm productMidPlm, BindingResult bindingResult) {
        BaseResult<List<ProdMidDto>> baseResult = new BaseResult<List<ProdMidDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            List<ProdMidDto> dto = prodCrafworkMainPlmService.prodDown(productMidPlm);
            baseResult.setModel(dto);
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
     * 半成品工艺详情
     *
     * @param prodCrafworkPathPlm
     * @return
     * @author xufei
     * @date 2018-9-25 08:44:26
     */
    @ResponseBody
    @RequestMapping(value = "/pathCraf ", method = {RequestMethod.GET, RequestMethod.POST})
    public Object pathCraf(ProdCrafworkPathPlm prodCrafworkPathPlm, BindingResult bindingResult) {
        BaseResult<List<ProdCrafworkPathPlmDto>> baseResult = new BaseResult<List<ProdCrafworkPathPlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            List<ProdCrafworkPathPlmDto> dto = prodCrafworkPathPlmService.pathCraf(prodCrafworkPathPlm);
            baseResult.setModel(dto);
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
     * 新增工艺/工艺下拉
     *
     * @param crafworkStructPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-25 09:46:52
     */
    @ResponseBody
    @RequestMapping(value = "/findCraf", method = {RequestMethod.GET, RequestMethod.POST})
    public Object findCraf(CrafworkStructDto crafworkStructPlm, BindingResult bindingResult) {
        BaseResult<List<CrafworkStructDto>> baseResult = new BaseResult<List<CrafworkStructDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            crafworkStructPlm.setCompanyId(UserHolder.getCompanyId());
            List<CrafworkStructDto> list = crafworkStructPlmService.findCraf(crafworkStructPlm);
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
     * 新增产品工艺
     *
     * @param prodCrafworkMainPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-25 11:09:11
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public Object add(@Valid ProdCrafworkPathPlmDto prodCrafworkMainPlm, BindingResult bindingResult) {
        BaseResult<ProdCrafworkPathPlm> baseResult = new BaseResult<ProdCrafworkPathPlm>();
        baseResult.setResult(true);
        baseResult.setMessage("添加成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            ProdCrafworkPathPlmDto list = prodCrafworkPathPlmService.add(prodCrafworkMainPlm);
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
     * 编辑产品工艺
     *
     * @param prodCrafworkMainPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-25 13:35:31
     */
    @ResponseBody
    @RequestMapping(value = "/upd", method = {RequestMethod.GET, RequestMethod.POST})
    public Object upd(ProdCrafworkPathPlmDto prodCrafworkMainPlm, BindingResult bindingResult) {
        BaseResult<ProdCrafworkPathPlm> baseResult = new BaseResult<ProdCrafworkPathPlm>();
        baseResult.setResult(true);
        baseResult.setMessage("编辑成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            ProdCrafworkPathPlm list = prodCrafworkPathPlmService.upd(prodCrafworkMainPlm);
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
     * @param prodCrafworkPathPlmDtos
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-25 15:29:25
     */
    @ResponseBody
    @RequestMapping(value = "/updSeq", method = {RequestMethod.GET, RequestMethod.POST})
    public Object updSeq(@RequestBody ProdCrafworkPathPlmDto[] prodCrafworkPathPlmDtos, BindingResult bindingResult) {
        BaseResult<List<ProdCrafworkPathPlmDto>> baseResult = new BaseResult<List<ProdCrafworkPathPlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("调整成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodCrafworkMainPlmService.switchSequence(prodCrafworkPathPlmDtos);
            //            List<ProdCrafworkPathPlmAudDto> list = prodCrafworkPathPlmForm.getProdCrafworkPathPlmAuds();
            //            for (ProdCrafworkPathPlmAudDto aud : list) {
            //                prodCrafworkPathPlmService.upd(aud);
            //            }
            //            baseResult.setModel(list);
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
     * 删除工艺
     *
     * @param prodCrafworkPathPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-25 15:29:25
     */
    @ResponseBody
    @RequestMapping(value = "/del", method = {RequestMethod.GET, RequestMethod.POST})
    public Object del(ProdCrafworkPathPlm prodCrafworkPathPlm, BindingResult bindingResult) {
        BaseResult<List<ProdCrafworkPathPlmDto>> baseResult = new BaseResult<List<ProdCrafworkPathPlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("调整成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            List<ProdCrafworkPathPlmDto> list = prodCrafworkPathPlmService.del(prodCrafworkPathPlm);
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

    //    @RequestMapping(value = "approve", method = RequestMethod.POST)
    //    @ResponseBody
    //    public BaseResult<String> approve(String pathNo) {
    //        BaseResult<String> baseResult = new BaseResult<>();
    //        baseResult.setResult(true);
    //        try {
    //            String approve = prodCrafworkPathPlmService.approve(pathNo);
    //            if (StringUtils.isEmpty(approve)) {
    //                throw new BusinessException("审批失败");
    //            }
    //        } catch (BusinessException be) {
    //            LOGGER.debug("业务异常" + be);
    //            baseResult.setResult(false);
    //            baseResult.setMessage(be.getMessage());
    //        } catch (Exception e) {
    //            LOGGER.debug("系统异常" + e);
    //            baseResult.setResult(false);
    //            baseResult.setMessage("系统异常");
    //        }
    //        return baseResult;
    //    }
}