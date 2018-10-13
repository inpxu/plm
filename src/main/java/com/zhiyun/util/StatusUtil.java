package com.zhiyun.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sun
 * @version v1.0
 * @date 2018-09-07 09:22
 */
public class StatusUtil {
    /**
     * 物料库状态
     *
     * @author wqh
     */
    public interface matterStatus {

        Map<String, String> map = new HashMap<String, String>() {
            private static final long serialVersionUID = 1L;

            {
                put("0", "缺货");
                put("1", "正常供货");
                put("2", "关闭");
            }
        };
    }

    /**
     * 物料库物料类型
     */
    public interface matterType {

        Map<String, String> map = new HashMap<String, String>() {
            private static final long serialVersionUID = 1L;

            {
                put("0", "原料");
                put("1", "公用组件");
            }
        };
    }

    /**
     * 物料库物料类型
     */
    public interface matterType2 {

        Map<String, String> map = new HashMap<String, String>() {
            private static final long serialVersionUID = 1L;

            {
                put("0", "原料");
                put("1", "公用组件");
                put("2", "半成品");
            }
        };
    }

}
