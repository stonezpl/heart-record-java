package com.stonezpl.hr.controller;

import com.stonezpl.hr.common.pojo.CommonResult;
import com.stonezpl.hr.controller.vo.WxLoginInfoVO;
import com.stonezpl.hr.service.IWechatService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@Tag(name = "小程序开发平台交互")
@RequestMapping("/wechat")
@RestController
public class WechatController {

    @Resource
    private IWechatService wechatService;

    @GetMapping("/getWxLoginInfo")
    public CommonResult<WxLoginInfoVO> getWxLoginInfo(@Schema(description = "wx.login返回的code",example = "xxx")@RequestParam("code") String code){
        return CommonResult.success(wechatService.login(code));
    }
}
