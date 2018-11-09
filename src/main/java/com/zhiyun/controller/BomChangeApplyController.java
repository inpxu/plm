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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 产品bom变更申请controller
 *
 * @author 邓艺
 * @version v1.0
 * @date 2018-10-12 10:41
 */
@Slf4j
@Controller
@RequestMapping("/bomChangeApply")
public class BomChangeApplyController {
    @Autowired
    private ProdBomPlmService prodBomPlmService;

    /**
     * 分页查询
     *
     * @param prodBomPlmDto 查询条件
     * @param pager 分页对象
     * @return java.lang.String json串
     * @author 邓艺
     * @date 2018/10/12 10:45
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Object page(ProdBomPlmDto prodBomPlmDto, Pager pager) {
        if (StringUtils.isBlank(prodBomPlmDto.getVoucherNo())) {
            prodBomPlmDto.setVoucherNo(null);
        }
        BaseResult<DataGrid<ProdBomPlmDto>> baseResult = new BaseResult<>();
        try {
            prodBomPlmDto.setCompanyId(UserHolder.getCompanyId());
            DataGrid<ProdBomPlmDto> entity = prodBomPlmService.customPage(Params.create().add("entity", prodBomPlmDto), pager);
            for (ProdBomPlmDto o : entity.getItems()) {
                String mattersName = o.getMattersName();
                String productName = o.getProductName();
                if (mattersName == null || mattersName == "") {
                    o.setMattersName("/");
                }
                if (productName == null || productName == "") {
                    o.setProductName("/");
                }
            }
            baseResult.setResult(true);
            baseResult.setMessage("查询成功");
            baseResult.setModel(entity);
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
     * 审批
     *
     * @param voucherNo
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/12 10:45
     */
    @RequestMapping(value = "approve", method = RequestMethod.POST)
    @ResponseBody
    public String approve(String voucherNo) {
        BaseResult<DataGrid<Object>> baseResult = new BaseResult<>();
        try {
            prodBomPlmService.approve(voucherNo);
            baseResult.setResult(true);
            baseResult.setMessage("审批成功");
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
