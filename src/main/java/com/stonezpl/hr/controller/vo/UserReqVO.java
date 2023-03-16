package com.stonezpl.hr.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author 张珮磊
 * @since 2023-03-11
 */
@Schema(description = "用户信息表 Request VO")
@Data
public class UserReqVO {

    @Schema(description = "昵称", example = "xxx")
    private String nickName;

    @Schema(description = "头像地址", example = "xxx")
    private String avatarUrl;

    @Schema(description = "openId", example = "xxx")
    private String openId;

    @Schema(description = "邮箱", example = "xxx")
    private String email;

    @Schema(description = "openai的token", example = "xxx")
    private String openaiToken;
}
