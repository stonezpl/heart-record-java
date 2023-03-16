package com.stonezpl.hr.controller.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "微信小程序登录返回实体类")
@Data
public class WxLoginInfoVO {

    @Schema(description = "用户唯一标识")
    private String openId;
    @Schema(description = "会话密钥")
    private String sessionKey;
    @Schema(description = "用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回")
    private String unionId;
    @Schema(description = "错误码")
    private int errcode;
    @Schema(description = "错误信息，详见https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/user-login/code2Session.html")
    private String errMsg;
}
