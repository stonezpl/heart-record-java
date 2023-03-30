package com.stonezpl.hr.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author stonezpl
 * @Description ai模型请求参数
 * @date 2023/3/29 13:44
 */
@Schema(description = "备忘录列表 Request VO")
@Data
public class OpenAIReqVO {

    @Schema(description = "prompt", example = "hello world")
    private String prompt;
}
