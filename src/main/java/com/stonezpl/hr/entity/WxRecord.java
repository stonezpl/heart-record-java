package com.stonezpl.hr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 记录表
 * </p>
 *
 * @author 张珮磊
 * @since 2023-03-08
 */
@TableName("wx_record")
@Schema(description = "记录表")
@Data
public class WxRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "标题")
    private String recordTitle;

    @Schema(description = "内容")
    private String recordContent;

    @Schema(description = "提醒时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime remindTime;

    @Schema(description = "创建者的openId")
    private String openId;

    @Schema(description = "创建时间")
    private LocalDateTime createDate;

    @Schema(description = "修改时间")
    private LocalDateTime updateTime;
}
