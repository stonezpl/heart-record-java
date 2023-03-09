package com.stonezpl.hr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

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
    private LocalDateTime remindTime;

    @Schema(description = "创建者的openId")
    private String openId;

    @Schema(description = "创建时间")
    private LocalDateTime createDate;

    @Schema(description = "修改时间")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordTitle() {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        this.recordTitle = recordTitle;
    }

    public String getRecordContent() {
        return recordContent;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }

    public LocalDateTime getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(LocalDateTime remindTime) {
        this.remindTime = remindTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "WxRecord{" +
            "id = " + id +
            ", recordTitle = " + recordTitle +
            ", recordContent = " + recordContent +
            ", remindTime = " + remindTime +
            ", openId = " + openId +
            ", createDate = " + createDate +
            ", updateTime = " + updateTime +
        "}";
    }
}
