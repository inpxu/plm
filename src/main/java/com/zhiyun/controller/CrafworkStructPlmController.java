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
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.LabelValue;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.util.CommonUtils;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.CrafworkStructDto;
import com.zhiyun.entity.*;
import com.zhiyun.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sun
 * @version v1.0
 * @date 2018-09-07 11:04
 */
@Controller
@RequestMapping(value = "/carfwork", produces = "application/json;charset=UTF-8")
public class CrafworkStructPlmController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CrafworkStructPlmController.class);

    @Resource
    private CrafworkStructPlmService crafworkStructPlmService;
    @Resource
    private CrafworkParamPlmService crafworkParamPlmService;
    @Resource
    private ProdCrafworkPathPlmService prodCrafworkPathPlmService;
    @Resource
    private QuartersHcmService quartersHcmService;
    @Resource
    private JqmService jqmService;

    /**
     * 分页查询
     *
     * @param crafworkStructPlm 查询条件
     * @param pager 分页条件
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pageCarfwork", method = {RequestMethod.POST, RequestMethod.GET})
    public String pageCarfwork(CrafworkStructPlm crafworkStructPlm, Pager pager) {
        BaseResult<DataGrid<CrafworkStructDto>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功");
        try {
            crafworkStructPlm.setCompanyId(UserHolder.getCompanyId());
            Params params = Params.create().add("entity", crafworkStructPlm);
            DataGrid<CrafworkStructDto> dataGrid = crafworkStructPlmService.pageCrafwork(params, pager);
            baseResult.setModel(dataGrid);
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
     * 工艺新增
     *
     * @param bindingResult
     * @param crafworkStructPlm 新增信息
     */
    @ResponseBody
    @RequestMapping(value = "addCrafwork", method = {RequestMethod.POST, RequestMethod.GET})
    public String addCrafwork(@Valid CrafworkStructPlm crafworkStructPlm, BindingResult bindingResult) {
        BaseResult<CrafworkStructPlm> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("新增成功");
        try {
            //校验参数是否合法
            vaildParamsDefault(baseResult, bindingResult);
            crafworkStructPlm.setCompanyId(UserHolder.getCompanyId());
            // 存在校验
            CrafworkStructPlm crafworkStructPlm1 = new CrafworkStructPlm();
            crafworkStructPlm1.setCompanyId(UserHolder.getCompanyId());
            crafworkStructPlm1.setCrafworkName(crafworkStructPlm.getCrafworkName());
            List<CrafworkStructPlm> crafworkStructPlms = crafworkStructPlmService.find(crafworkStructPlm1);
            if (!CommonUtils.isEmpty(crafworkStructPlms)) {
                throw new BusinessException("工艺名称已存在");
            }
            // 新增
            crafworkStructPlm = crafworkStructPlmService.insert(crafworkStructPlm);
            baseResult.setModel(crafworkStructPlm);
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
     * 工艺编辑
     *
     * @param bindingResult
     * @param crafworkStructPlm 编辑信息
     */
    @ResponseBody
    @RequestMapping(value = "editCrafwork", method = {RequestMethod.POST, RequestMethod.GET})
    public String editCrafwork(@Valid CrafworkStructPlm crafworkStructPlm, BindingResult bindingResult) {
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("修改成功");
        try {
            //校验参数是否合法
            vaildParamsDefault(baseResult, bindingResult);
            crafworkStructPlm.setCompanyId(UserHolder.getCompanyId());
            // 存在校验
            CrafworkStructPlm crafworkStructPlm1 = new CrafworkStructPlm();
            crafworkStructPlm1.setCompanyId(UserHolder.getCompanyId());
            crafworkStructPlm1.setCrafworkName(crafworkStructPlm.getCrafworkName());
            List<CrafworkStructPlm> crafworkStructPlms = crafworkStructPlmService.find(crafworkStructPlm1);
            if (!CommonUtils.isEmpty(crafworkStructPlms) && crafworkStructPlms.size() > 1) {
                throw new BusinessException("工业名称已存在");
            }
            crafworkStructPlmService.update(crafworkStructPlm);
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
     * 工艺删除
     *
     * @param ids
     */
    @ResponseBody
    @RequestMapping(value = "deCrafwork", method = {RequestMethod.POST, RequestMethod.GET})
    public String deCrafwork(@RequestParam("ids") Long[] ids) {
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("删除成功");
        try {
            for (Long id : ids) {
                //                CrafworkStructPlm crafworkStructPlm = crafworkStructPlmService.get(id);
                ProdCrafworkPathPlm prodCrafworkPathPlm = new ProdCrafworkPathPlm();
                prodCrafworkPathPlm.setCompanyId(UserHolder.getCompanyId());
                prodCrafworkPathPlm.setCrafworkId(id);
                List<ProdCrafworkPathPlm> prodCrafworkPathPlms = prodCrafworkPathPlmService.find(prodCrafworkPathPlm);
                if (!CommonUtils.isEmpty(prodCrafworkPathPlms)) {
                    throw new BusinessException("该工艺使用中");
                }
                crafworkStructPlmService.delete(id);
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
     * 工艺参数设置
     *
     * @param crafworkParamPlm
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editParams", method = {RequestMethod.POST, RequestMethod.GET})
    public String addParams(@Valid @RequestBody List<CrafworkParamPlm> crafworkParamPlm, BindingResult bindingResult) {
        BaseResult<List<CrafworkParamPlm>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("新增成功");
        try {
            //校验参数是否合法
            vaildParamsDefault(baseResult, bindingResult);
            if (CollectionUtils.isEmpty(crafworkParamPlm)) {
                throw new BusinessException("参数名不能为空");
            }
            for (int i = 0; i < crafworkParamPlm.size(); i++) {
                String temp = crafworkParamPlm.get(i).getParam();
                for (int j = i + 1; j < crafworkParamPlm.size(); j++) {
                    if (temp.equals(crafworkParamPlm.get(j).getParam())) {
                        throw new BusinessException("同一工艺下的参数名不能相同");
                    }
                }
            }
            CrafworkParamPlm crafworkParamPlm1 = new CrafworkParamPlm();
            crafworkParamPlm1.setCompanyId(UserHolder.getCompanyId());
            crafworkParamPlm1.setCrafworkId(crafworkParamPlm.get(0).getCrafworkId());
            // 查询该工艺的所有工艺参数
            List<CrafworkParamPlm> crafworkParamPlms = crafworkParamPlmService.find(crafworkParamPlm1);
            if (!CommonUtils.isEmpty(crafworkParamPlms)) {
                // 删除工艺之前的工艺参数
                for (CrafworkParamPlm crafworkParamPlm2 : crafworkParamPlms) {
                    crafworkParamPlmService.delete(crafworkParamPlm2.getId());
                }
            }
            // 增加新的工艺参数
            for (CrafworkParamPlm car : crafworkParamPlm) {
                car.setCompanyId(UserHolder.getCompanyId());
                crafworkParamPlmService.insert(car);
            }
            baseResult.setModel(crafworkParamPlm);
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
     * 添加工艺参数
     *
     * @param crafworkParamPlm
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addParam", method = {RequestMethod.POST, RequestMethod.GET})
    public String addParam(@Valid @RequestBody List<CrafworkParamPlm> crafworkParamPlm, BindingResult bindingResult) {
        BaseResult<List<CrafworkParamPlm>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("新增成功");
        try {
            //校验参数是否合法
            vaildParamsDefault(baseResult, bindingResult);
            List<CrafworkParamPlm> list = crafworkParamPlmService.addParam(crafworkParamPlm);
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
     * 工艺岗位下拉
     */
    @ResponseBody
    @RequestMapping(value = "/crafworkQuarter", method = {RequestMethod.POST, RequestMethod.GET})
    public String crafworkQuarter() {
        BaseResult<List<LabelValue>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("获取成功");
        try {
            List<LabelValue> labelValues = new ArrayList<>();
            QuartersHcm quartersHcm = new QuartersHcm();
            quartersHcm.setCompanyId(UserHolder.getCompanyId());
            List<QuartersHcm> quartersHcms = quartersHcmService.find(quartersHcm);
            if (!CommonUtils.isEmpty(quartersHcms)) {
                for (QuartersHcm quartersHcm1 : quartersHcms) {
                    LabelValue labelValue = new LabelValue();
                    labelValue.setLabel(quartersHcm1.getQuartersDesc());
                    labelValue.setValue(quartersHcm1.getQuartersNo());
                    labelValues.add(labelValue);
                }
            }
            baseResult.setModel(labelValues);
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
     * 工艺参数查询
     */
    @ResponseBody
    @RequestMapping(value = "/paramList", method = {RequestMethod.POST, RequestMethod.GET})
    public String paramList(CrafworkParamPlm crafworkParamPlm) {
        BaseResult<List<CrafworkParamPlm>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("获取成功");
        try {
            // 通过工艺id 查询工艺参数
            crafworkParamPlm.setCompanyId(UserHolder.getCompanyId());
            List<CrafworkParamPlm> crafworkParamPlms = crafworkParamPlmService.find(crafworkParamPlm);
            baseResult.setModel(crafworkParamPlms);
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
     * 设备类型下拉
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/optionDevType", method = {RequestMethod.GET, RequestMethod.POST})
    public Object optionDevType() {
        BaseResult<List<DevInfoJqm>> baseResult = new BaseResult<List<DevInfoJqm>>();
        baseResult.setResult(true);
        baseResult.setMessage("操作成功");
        try {
            DevInfoJqm devInfoJqm = new DevInfoJqm();
            devInfoJqm.setCompanyId(UserHolder.getCompanyId());
            List<DevInfoJqm> list = jqmService.findType(devInfoJqm);
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

}
