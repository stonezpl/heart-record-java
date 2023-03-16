package com.stonezpl.hr.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.stonezpl.hr.config.WechatProperties;
import com.stonezpl.hr.constants.RedisConstants;
import com.stonezpl.hr.controller.vo.WxLoginInfoVO;
import com.stonezpl.hr.service.IWechatService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 微信小程序公共接口交互
 *
 * @author stonezpl
 */
@Service
@Slf4j
public class WechatServiceImpl implements IWechatService {

    @Resource
    private WechatProperties wechatProperties;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public WxLoginInfoVO login(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", wechatProperties.getAppId());
        paramMap.put("secret", wechatProperties.getAppSecret());
        paramMap.put("js_code", code);
        paramMap.put("grant_type", "authorization_code");

        String result = HttpUtil.get(url, paramMap);
        JSONObject jo = JSONUtil.parseObj(result);

        WxLoginInfoVO wxLoginInfoVO = new WxLoginInfoVO();
        String openId = jo.getStr("openid");
        if (StrUtil.isBlank(openId)) {
            log.warn("获取openId为空,result:{}", result);
        } else {
            String unionId = jo.getStr("unionid");
            String sessionKey = jo.getStr("session_key");
            wxLoginInfoVO.setOpenId(openId);
            wxLoginInfoVO.setUnionId(unionId);
            wxLoginInfoVO.setSessionKey(sessionKey);
        }
        return wxLoginInfoVO;
    }

    @Override
    public String getAccessToken() {
        String redisKey = String.format(RedisConstants.ACCESS_TOKEN_KEY,wechatProperties.getAppId());
        String accessToken = stringRedisTemplate.opsForValue().get(redisKey);
        if (StrUtil.isBlank(accessToken)) {

            String url = "https://api.weixin.qq.com/cgi-bin/token";

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("appid", wechatProperties.getAppId());
            paramMap.put("secret", wechatProperties.getAppSecret());
            paramMap.put("grant_type", "client_credential");

            String result = HttpUtil.get(url, paramMap);
            JSONObject jo = JSONUtil.parseObj(result);

            accessToken = jo.getStr("access_token");
            //设置超时，微信平台7200s，我们呢设置3600吧
            stringRedisTemplate.opsForValue().set(redisKey,accessToken,3600, TimeUnit.SECONDS);
        }
        return accessToken;
    }

    @Override
    public void sendSubscribeMessage(String accessToken,
                                     String templateId,
                                     String openId,
                                     Map<String, Map<String, Object>> param) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("template_id", templateId);
        paramMap.put("touser", openId);
        paramMap.put("data", param);
        paramMap.put("miniprogram_state", "developer");
        paramMap.put("lang", "zh_CN");
        String result = HttpUtil.post(url,JSONUtil.toJsonStr(paramMap));
        log.info(result);

    }
}
