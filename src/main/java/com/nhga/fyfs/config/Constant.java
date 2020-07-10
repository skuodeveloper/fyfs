package com.nhga.fyfs.config;

/**
 * 项目中的常量定义类
 */
public class Constant {
    /**
     * 企业corpid, 需要修改成开发者所在企业
     */
    public static final String CORP_ID = "dingc8fa8f519086d77d35c2f4657eb6378f";
    /**
     * 应用的AppKey，登录开发者后台，点击应用管理，进入应用详情可见
     */
    public static final String APPKEY = "ding344mjaxquwa6wpgc";
    /**
     * 应用的AppSecret，登录开发者后台，点击应用管理，进入应用详情可见
     */
    public static final String APPSECRET = "je-m5WNnUpq1gj_oDFU9PKoalVy6Xyg-DnJKXU73HXHBz7timhjd0UH23q0uswGo";

    /**
     * 数据加密密钥。用于回调数据的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取,您可以随机生成
     */
    public static final String ENCODING_AES_KEY = "abcdefghijabcdefghijabcdefghijabcdefghij123";

    /**
     * 加解密需要用到的token，企业可以随机填写。如 "12345"
     */
    public static final String TOKEN = "12345";

    /**
     * 应用的agentdId，登录开发者后台可查看
     */
    public static final Long AGENTID = 284251672L;

    /**
     * 审批模板唯一标识，可以在审批管理后台找到[发一防十]
     */
    public static final String PROCESS_CODE = "PROC-FCCC5EAC-8545-4DF4-AA3F-793FB6C23BAC";

    /**
     * 回调host
     */
    public static final String CALLBACK_URL_HOST = "http://abcde.vaiwan.com";
}
