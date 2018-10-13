/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.util;

/**
 * 工具类 创建时从0开始依次增加
 *
 * @author sun
 * @version v1.0
 * @date 2018-09-07 08:54
 */
public class ConstansUtil {

    public enum Sources {

        SELF_PRODUCTION(0, "自产"),
        EXTERNAL_ASSISTANCE(1, "外协"),
        EXTERNAL_PURCHASE(2, "外购");

        private int id;

        private String desc;

        private Sources(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        public static String getSourceDesc(int id) {
            for (Sources property : Sources.values()) {
                if (property.id == id) {
                    return property.desc;
                }
            }
            return null;
        }
    }

    /**
     * 设备班制
     */
    public enum RunTotal {

        ONE(1, "单班"),
        TWO(2, "两班倒"),
        THR(3, "三班倒");

        private int id;

        private String desc;

        private RunTotal(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        public static String getRunTotalDesc(int id) {
            for (RunTotal property : RunTotal.values()) {
                if (property.id == id) {
                    return property.desc;
                }
            }
            return null;
        }
    }

}
