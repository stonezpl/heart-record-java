package com.stonezpl.hr.controller;

import com.stonezpl.hr.common.pojo.CommonResult;
import com.stonezpl.hr.controller.vo.WxLoginInfoVO;
import com.stonezpl.hr.service.WechatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@Tag(name = "小程序开发平台交互")
@RequestMapping("/wechat")
@RestController
public class WechatController {

    @Resource
    private WechatService wechatService;

    @GetMapping("/getWxLoginInfo")
    public CommonResult<WxLoginInfoVO> getWxLoginInfo(@RequestParam("code") String code){
        return CommonResult.success(wechatService.login(code));
    }
}
