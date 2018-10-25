package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.dto.BomPlmDto;
import com.zhiyun.dto.MattersStoreDto;
import com.zhiyun.dto.ProdBomPlmDto;
import com.zhiyun.dto.ProdDto;
import com.zhiyun.entity.ProdBomPlm;
import com.zhiyun.service.ProdBomPlmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 如果为空，则新增物料编码
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
     * 新增bom编码，新增的时候必须保证bom编码的唯一
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
            //新增bom编码，新增以后将新增的数据查出来供前端显示
            ProdBomPlmDto prodBomPlmDto = prodBomPlmService.addBomNo(prodBomPlm);
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
    @RequestMapping(value = "upGradeCommonBom", method = RequestMethod.POST)
    @ResponseBody
    public String upGradeCommonBom(@RequestBody ProdBomPlmDto prodBomPlmDto) {
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

    /**
     *  修改产品bom保存
     * @param prodDto
     * @return
     */
    @RequestMapping(value = "upCommonBom", method = RequestMethod.POST)
    @ResponseBody
    public String upCommonBom(@RequestBody ProdDto prodDto) {
        BaseResult<String> baseResult = new BaseResult<>();
        try {
            prodBomPlmService.upCommonBom(prodDto);
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

    /**
     * 升级版本前查询
     *
     * @param prodBomPlmDto
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/7 14:51
     */
    @RequestMapping(value = "beforeUpDateBom", method = RequestMethod.POST)
    @ResponseBody
    public String beforeUpDateBom(@RequestBody ProdBomPlmDto prodBomPlmDto) {
        BaseResult<ProdBomPlmDto> baseResult = new BaseResult<>();
        try {
            ProdBomPlmDto returnProdBomPlmDto = prodBomPlmService.beforeUpdateBom(prodBomPlmDto);
            baseResult.setModel(returnProdBomPlmDto);
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
     * 根据公用组件编码以及版本信息查询其下所有物料编码和公用组件
     * 这里是已经添加了版本查询
     *
     * @param mattersStoreDto
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/7 14:51
     */
    @RequestMapping(value = "findAllChildByMattersNo", method = RequestMethod.POST)
    @ResponseBody
    public String findAllChildByMattersNo(MattersStoreDto mattersStoreDto) {
        BaseResult<List<MattersStoreDto>> baseResult = new BaseResult<>();
        try {
            List<MattersStoreDto> mattersStoreDtoList = prodBomPlmService.findAllChildByMattersNo(mattersStoreDto);
            baseResult.setModel(mattersStoreDtoList);
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

}
