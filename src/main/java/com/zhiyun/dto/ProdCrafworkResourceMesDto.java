package com.zhiyun.dto;

import com.zhiyun.entity.ProdCrafworkResourceMes;

import java.util.List;

public class ProdCrafworkResourceMesDto extends ProdCrafworkResourceMes {
    private static final long serialVersionUID = 890848280849700964L;
    // 图片路径数组
    private List<String> images;

    private Long[] ids;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}