package com.swjtu.mybatis.utils.busi;

import com.swjtu.mybatis.utils.constant.BusiConst;

public class CommonUtils {
    /**
     * 指定运行环境
     * @param env
     */
    public final static void setRunEnv(Env env) {
        System.getProperties().setProperty(BusiConst.ENV_KEY, env.name().toLowerCase());
    }
}
