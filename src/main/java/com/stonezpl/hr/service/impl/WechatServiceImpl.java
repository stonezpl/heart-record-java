package com.stonezpl.hr.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.stonezpl.hr.config.WechatProperties;
import com.stonezpl.hr.controller.vo.WxLoginInfoVO;
import com.stonezpl.hr.service.WechatService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序公共接口交互
 *
 * @author stonezpl
 */
@Service
@Slf4j
public class WechatServiceImpl implements WechatService {

    @Resource
    private WechatProperties wechatProperties;

    @Override
    public WxLoginInfoVO login(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", wechatProperties.getAppId());
        paramMap.put("secret", wechatProperties.getAppSecret());
        paramMap.put("js_code", code);
        paramMap.put("grant_type", wechatProperties.getGrantType());

        String result = HttpUtil.get(url, paramMap);
        JSONObject jo = JSONUtil.parseObj(result);

        WxLoginInfoVO wxLoginInfoVO = new WxLoginInfoVO();
        String openId = jo.getStr("openid");
        if (StrUtil.isBlank(openId)) {
            log.warn("获取openId为空,result:{}",result);
        }else {
            String unionId = jo.getStr("unionid");
            String sessionKey = jo.getStr("session_key");
            wxLoginInfoVO.setOpenId(openId);
            wxLoginInfoVO.setUnionId(unionId);
            wxLoginInfoVO.setSessionKey(sessionKey);
        }
        return wxLoginInfoVO;
    }
}
