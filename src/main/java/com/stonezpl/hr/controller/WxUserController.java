package com.stonezpl.hr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stonezpl.hr.common.pojo.CommonResult;
import com.stonezpl.hr.controller.vo.UserReqVO;
import com.stonezpl.hr.entity.WxRecord;
import com.stonezpl.hr.entity.WxUser;
import com.stonezpl.hr.service.IWxUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author stonezpl
 * @since 2023-03-11
 */
@Tag(name = "用户信息")
@RestController
@RequestMapping("/user")
public class WxUserController {

    @Resource
    private IWxUserService wxUserService;

    @Operation(summary = "保存或更新用户信息")
    @PostMapping("/saveOrUpdate")
    public CommonResult<Boolean> saveRecord(@RequestBody UserReqVO userReqVO) {
        return CommonResult.success(wxUserService.saveOrUpdateUser(userReqVO));
    }

    @Operation(summary = "获取用户信息")
    @PostMapping("/getUserInfo")
    public CommonResult<WxUser> getUserInfo(@RequestBody UserReqVO userReqVO) {
        WxUser wxUser = wxUserService.getOne(new QueryWrapper<WxUser>()
                .eq("open_id",userReqVO.getOpenId()));
        return CommonResult.success(wxUser);
    }

}
