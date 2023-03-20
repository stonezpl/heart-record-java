package com.stonezpl.hr.task;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.ImmutableMap;
import com.stonezpl.hr.entity.WxUser;
import com.stonezpl.hr.service.IMailService;
import com.stonezpl.hr.service.IWechatService;
import com.stonezpl.hr.service.IWxUserService;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangpeilei
 * @Description 邮件提醒
 * @date 2023/3/14 18:13
 */
@Slf4j
public class MailTask extends AbstractRedisDelayTask {

    public MailTask(String id, String value, String taskType, long delayTime) {
        super(id, value, taskType, delayTime);
    }

    @Override
    public void execute() {
        IMailService mailService = SpringUtil.getBean(IMailService.class);
        IWxUserService wxUserService = SpringUtil.getBean(IWxUserService.class);
        JSONObject jsonObject = JSONUtil.parseObj(this.getValue());
        String openId = jsonObject.getStr("openId");
        WxUser wxUser = wxUserService.getOne(new QueryWrapper<WxUser>().eq("open_id",openId));
        if (StrUtil.isNotBlank(wxUser.getEmail())) {
            String title = jsonObject.getStr("title");
            String content = jsonObject.getStr("content");
            StringBuilder sb = new StringBuilder();
            sb.append("标题: ").append(title).append("\n");
            sb.append("内容: ").append(content).append("\n");

            mailService.sendMailMessage(wxUser.getEmail(),"备忘录提醒",sb.toString());
        }

    }
}
