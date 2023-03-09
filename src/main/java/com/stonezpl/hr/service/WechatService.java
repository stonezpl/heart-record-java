package com.stonezpl.hr.service;

import com.stonezpl.hr.controller.vo.WxLoginInfoVO;

/**
 * 微信小程序公共接口交互
 *
 * @author stonezpl
 */
public interface WechatService {

    WxLoginInfoVO login(String code);
}
