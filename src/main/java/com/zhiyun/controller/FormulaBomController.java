package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.dto.ProdBomPlmDto;
import com.zhiyun.entity.FormulaBomPlm;
import com.zhiyun.service.FormulaBomPlmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 配方bomcontroller
 *
 * @author 邓艺
 * @version v1.0
 * @date 2018-10-11 10:56
 */
@Slf4j
@Controller
@RequestMapping("formulaBom")
public class FormulaBomController extends BaseController {
    @Autowired
    private FormulaBomPlmService formulaBomPlmService;

    /**
     * 新增或者更新配方
     *
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/11 11:29
     */
    @RequestMapping(value = "addOrUpdateFormula", method = RequestMethod.POST)
    @ResponseBody
    public String addOrUpdateFormula(@Valid FormulaBomPlm formulaBomPlm, BindingResult bindingResult) {
        BaseResult<ProdBomPlmDto> baseResult = new BaseResult<>();
        try {
            vaildParamsDefault(baseResult, bindingResult);
            formulaBomPlmService.addOrUpdateFormula(formulaBomPlm);
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
     * 删除配方
     *
     * @param ids id数组
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/11 11:29
     */
    @RequestMapping(value = "deleteFormulaItems", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFormulaItems(Long[] ids) {
        BaseResult<ProdBomPlmDto> baseResult = new BaseResult<>();
        try {
            formulaBomPlmService.deleteByIds(ids);
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
