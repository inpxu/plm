package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.LabelValue;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.MattersStoreDto;
import com.zhiyun.entity.MattersStoreIos;
import com.zhiyun.entity.MattersTypeIos;
import com.zhiyun.service.MattersStoreIosService;
import com.zhiyun.service.MattersTypeIosService;
import com.zhiyun.util.StatusUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 物料模块
 *
 * @author wqh
 */
@Controller
@RequestMapping("/matters")
public class MattersStoreController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(MattersStoreController.class);

    @Resource
    private MattersTypeIosService mattersTypeIosService;
    @Resource
    private MattersStoreIosService mattersStoreIosService;

    /**
     * 物料分类新增
     *
     * @param mattersTypeIos 物料实体
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public Object insert(@Valid MattersTypeIos mattersTypeIos, BindingResult bindingResult) {
        BaseResult<MattersTypeIos> baseResult = new BaseResult<MattersTypeIos>();
        baseResult.setResult(true);
        baseResult.setMessage("新增成功");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            this.mattersTypeIosService.insertMatterType(mattersTypeIos);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料分类编辑
     *
     * @param mattersTypeIos 物料实体
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Object update(@Valid MattersTypeIos mattersTypeIos, BindingResult bindingResult) {
        BaseResult<MattersTypeIos> baseResult = new BaseResult<MattersTypeIos>();
        baseResult.setResult(true);
        baseResult.setMessage("编辑成功");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            this.mattersTypeIosService.updateMatterType(mattersTypeIos);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料分类删除
     *
     * @param ids 物料分类id
     * @return
     */
    @RequestMapping("/deleted")
    @ResponseBody
    public Object deleted(Long[] ids) {
        BaseResult<MattersTypeIos> baseResult = new BaseResult<MattersTypeIos>();
        baseResult.setResult(true);
        baseResult.setMessage("删除成功");
        try {
            this.mattersTypeIosService.deletedMatterType(ids);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料分类查询
     *
     * @return
     */
    @RequestMapping("/select")
    @ResponseBody
    public Object select(MattersTypeIos mattersTypeIos, Pager pager) {
        BaseResult<DataGrid<MattersTypeIos>> baseResult = new BaseResult<DataGrid<MattersTypeIos>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功");
        try {
            DataGrid<MattersTypeIos> dataGrid = this.mattersTypeIosService.select(Params.create().add("entity", mattersTypeIos), pager);
            baseResult.setModel(dataGrid);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料分类下拉列表
     *
     * @return
     */
    @RequestMapping("/option")
    @ResponseBody
    public Object option() {
        BaseResult<List<LabelValue>> baseResult = new BaseResult<List<LabelValue>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功");
        try {
            List<LabelValue> labelValueList = this.mattersTypeIosService.mattersOptions();
            baseResult.setModel(labelValueList);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料库新增
     *
     * @return
     */
    @RequestMapping("/insetStore")
    @ResponseBody
    public Object insetStore(MattersStoreIos mattersStoreIos) {
        BaseResult<String> baseResult = new BaseResult<String>();
        baseResult.setResult(true);
        baseResult.setMessage("新增成功");
        try {
            this.mattersStoreIosService.insertStore(mattersStoreIos);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料库编辑
     *
     * @return
     */
    @RequestMapping("/updateStore")
    @ResponseBody
    public Object updateStore(MattersStoreIos mattersStoreIos) {
        BaseResult<String> baseResult = new BaseResult<String>();
        baseResult.setResult(true);
        baseResult.setMessage("编辑成功");
        try {
            this.mattersStoreIosService.updateStore(mattersStoreIos);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料库删除
     *
     * @return
     */
    @RequestMapping("/deletedStore")
    @ResponseBody
    public Object deletedStore(Long[] ids) {
        BaseResult<String> baseResult = new BaseResult<String>();
        baseResult.setResult(true);
        baseResult.setMessage("删除成功");
        try {
            this.mattersStoreIosService.deletedStore(ids);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料库状态下拉
     *
     * @return
     */
    @RequestMapping("/statusOption")
    @ResponseBody
    public Object statusOption() {
        BaseResult<Map<String, String>> baseResult = new BaseResult<Map<String, String>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功");
        try {
            Map<String, String> map = StatusUtil.matterStatus.map;
            baseResult.setModel(map);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料库物料类型下拉
     *
     * @return
     */
    @RequestMapping("/typeOption")
    @ResponseBody
    public Object typeOption() {
        BaseResult<Map<String, String>> baseResult = new BaseResult<Map<String, String>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功");
        try {
            Map<String, String> map = StatusUtil.matterType.map;
            baseResult.setModel(map);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料库物料类型下拉
     *
     * @return
     */
    @RequestMapping("/typeOption2")
    @ResponseBody
    public Object typeOption2() {
        BaseResult<Map<String, String>> baseResult = new BaseResult<Map<String, String>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功");
        try {
            Map<String, String> map = StatusUtil.matterType2.map;
            baseResult.setModel(map);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料库查询
     *
     * @param mattersStoreDto
     * @param pager
     * @return
     */
    @RequestMapping("/selectStore")
    @ResponseBody
    public Object selectStore(MattersStoreDto mattersStoreDto, Pager pager) {
        BaseResult<DataGrid<MattersStoreDto>> baseResult = new BaseResult<DataGrid<MattersStoreDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功");
        try {
            DataGrid<MattersStoreDto> dataGrid = this.mattersStoreIosService.select(Params.create().add("entity", mattersStoreDto), pager);
            baseResult.setModel(dataGrid);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料库状态编辑
     *
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public Object updateStatus(MattersStoreIos mattersStoreDto) {
        BaseResult<DataGrid<MattersStoreDto>> baseResult = new BaseResult<DataGrid<MattersStoreDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("修改成功");
        try {
            if (mattersStoreDto != null && mattersStoreDto.getId() != null) {
                MattersStoreIos ios = new MattersStoreIos();
                ios.setId(mattersStoreDto.getId());
                ios.setStatus(mattersStoreDto.getStatus());
                this.mattersStoreIosService.update(ios);
            } else {
                throw new BusinessException("请选择对象");
            }
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料查询
     *
     * @return
     */
    @RequestMapping("/getMatter")
    @ResponseBody
    public Object getMatter(MattersStoreIos mattersStore, BindingResult bindingResult) {
        BaseResult<List<MattersStoreDto>> baseResult = new BaseResult<List<MattersStoreDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            mattersStore.setCompanyId(UserHolder.getCompanyId());
            List<MattersStoreDto> list = mattersStoreIosService.getMatter(mattersStore);
            for (MattersStoreDto dto : list) {
                dto.setAllInfo(dto.getNorms() + "/" + dto.getParam() + "/" + dto.getModelDesc());
            }
            baseResult.setModel(list);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 物料下拉查询
     * 查询出所有物料id,编码，物料名供下拉使用
     *
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/14 19:47
     */
    @RequestMapping("/mattersOption")
    @ResponseBody
    public String mattersOption() {
        BaseResult<List<MattersStoreIos>> baseResult = new BaseResult<>();
        try {
            List<MattersStoreIos> matters = mattersStoreIosService.mattersOption();
            baseResult.setResult(true);
            baseResult.setMessage("查询成功");
            baseResult.setModel(matters);
        } catch (BusinessException be) {
            logger.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            logger.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

}
