package com.stonezpl.hr.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "备忘录列表 Request VO")
@Data
public class RecordListReqVO {

    @Schema(description = "openId", example = "xxx")
    private String openId;

}
