package com.stonezpl.hr.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.stonezpl.hr.common.pojo.CommonResult;
import com.stonezpl.hr.controller.vo.OpenAIReqVO;
import com.stonezpl.hr.service.SensitiveWordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author stonezpl
 * @Description 聊天相关接口
 * @date 2023/3/11 14:09
 */
@Tag(name = "AI交互")
@RestController
@RequestMapping("/open/ai")
public class OpenAIController {

    /**
     * 聊天问答
     * I apologize for the confusion. You are correct that the parameter promptId is not currently listed in the
     * official OpenAI API documentation.
     * <p>
     * However, in previous versions of the API, the promptId parameter was included as part of the request payload
     * for the Completion endpoint. This parameter allowed you to provide a unique identifier for the prompt, which
     * could be used to track the conversation history and ensure continuity between requests.
     * <p>
     * It's possible that promptId is still supported by the API, but just not documented at the moment. However, I
     * would recommend contacting OpenAI support to confirm this, as undocumented parameters may not be reliable or
     * supported in the long term.
     * <p>
     * Alternatively, you can use another approach to maintain continuity between requests, such as appending the
     * previous response text to the next request prompt. This way, the API can use the context of the previous
     * conversation to generate a more coherent response.
     */
    @PostMapping("/chat")
    public CommonResult<String> chat(@RequestBody OpenAIReqVO openAIReqVO) {
        Map<String, Object> params = new HashMap<>();
        params.put("model", "text-davinci-003");
        params.put("prompt", openAIReqVO.getPrompt());
        params.put("max_tokens", 256);
        params.put("temperature", 0);


        String result = HttpRequest.post("https://api.openai.com/v1/completions")
                .setHttpProxy("127.0.0.1", 7890)
                .header("Authorization", "Bearer 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")
                .body(JSONUtil.toJsonStr(params))
                .execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String text = jsonObject.getJSONArray("choices").getJSONObject(0).getStr("text");
        List<String> sensitiveWords = SpringUtil.getBean(SensitiveWordService.class).validateText(text);
        if (sensitiveWords.size() > 0) {
            for (String sensitiveWord : sensitiveWords) {
                text = StrUtil.replace(text, sensitiveWord, "****");
            }
        }
        return CommonResult.success(text);

    }

}
