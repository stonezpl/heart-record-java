package com.stonezpl.hr.service;

import com.stonezpl.hr.controller.vo.WxLoginInfoVO;

import java.util.Map;

/**
 * 微信小程序公共接口交互
 *
 * @author stonezpl
 */
public interface IWechatService {

    /**
     * 登录接口，获取用户open_id
     * @param code wx.login返回的code
     * @return
     */
    WxLoginInfoVO login(String code);

    /**
     * 获取接口调用凭证
     * @return
     */
    String getAccessToken();

    /**
     * 发送订阅消息
     * @param accessToken
     * @param templateId
     * @param openId
     * @param param
     */
    void sendSubscribeMessage(String accessToken,
                              String templateId,
                              String openId,
                              Map<String, Map<String, Object>> param);
}
