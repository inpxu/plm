package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.MattersStoreDto;
import com.zhiyun.dto.ProdBomPlmDto;
import com.zhiyun.entity.ProdBomPlm;
import com.zhiyun.service.ProdBomPlmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author 邓艺
 * @version v1.0
 * @date 2018-10-10 11:27
 */
@Slf4j
@Controller
@RequestMapping("commonBom")
public class CommonBomController {
    @Autowired
    private ProdBomPlmService prodBomPlmService;

    /**
     * 通过物料编码公用组件bom
     *
     * @param mattersNo bom编码
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/7 13:29
     */
    @RequestMapping(value = "findCommonBom", method = RequestMethod.POST)
    @ResponseBody
    public String findCommonBom(String mattersNo) {
        BaseResult<ProdBomPlmDto> baseResult = new BaseResult<>();
        try {
            ProdBomPlmDto prodBomPlmDto = prodBomPlmService.findCommonBom(mattersNo);
            baseResult.setResult(true);
            baseResult.setMessage("查询成功");
            baseResult.setModel(prodBomPlmDto);
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
        BaseResult<ProdBomPlmDto> baseResult = new BaseResult<>();
        try {
            //bom编码唯一性校验
            prodBomPlmService.uniqueBomNo(prodBomPlm.getBomNo());
            prodBomPlm.setCompanyId(UserHolder.getCompanyId());
            prodBomPlm.setMakeEmp(UserHolder.getUserName());
            prodBomPlm.setMakeDate(new Date());
            prodBomPlm.setBomStatus("启用中");
            prodBomPlmService.addBomNo(prodBomPlm);
            //新增bom成功以后再次查询返回前段供显示
            ProdBomPlmDto prodBomPlmDto = prodBomPlmService.findCommonBomByProdNo(prodBomPlm.getProdNo());
            baseResult.setModel(prodBomPlmDto);
            baseResult.setResult(true);
            baseResult.setMessage("新增成功");
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

    /**
     * 下拉查询组件
     *
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/8 14:40
     */
    @RequestMapping(value = "optionComponent", method = RequestMethod.GET)
    @ResponseBody
    public String optionComponent() {
        BaseResult<List<MattersStoreDto>> baseResult = new BaseResult<>();
        try {
            List<MattersStoreDto> mattersStoreDtos = prodBomPlmService.optionComponent();
            baseResult.setResult(true);
            baseResult.setModel(mattersStoreDtos);
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

    /**
     * 升级公共组件bom版本及参数
     * 保存历史记录
     *
     * @param prodBomPlmDto
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/17 16:46
     */
    @RequestMapping(value = "upGradeCommonBom", method = RequestMethod.GET)
    @ResponseBody
    public String upGradeCommonBom(ProdBomPlmDto prodBomPlmDto) {
        BaseResult<String> baseResult = new BaseResult<>();
        try {
            prodBomPlmService.upGradeCommonBom(prodBomPlmDto);
            baseResult.setResult(true);
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
