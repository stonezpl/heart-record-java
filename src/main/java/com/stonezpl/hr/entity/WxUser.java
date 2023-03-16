package com.stonezpl.hr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("wx_user")
@Schema(description = "用户信息表")
@Data
public class WxUser implements Serializable {

    private static final long serialVersionUID = 1L;

   @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

   @Schema(description = "昵称")
    private String nickName;

   @Schema(description = "头像地址")
    private String avatarUrl;

   @Schema(description = "openId")
    private String openId;

   @Schema(description = "邮箱")
    private String email;

   @Schema(description = "创建时间")
    private LocalDateTime createDate;

   @Schema(description = "更新时间")
    private LocalDateTime updateDate;
}
