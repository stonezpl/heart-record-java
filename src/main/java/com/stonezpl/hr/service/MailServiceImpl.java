package com.stonezpl.hr.service;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.stonezpl.hr.config.MailProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhangpeilei
 * @Description 邮箱实现类
 * @date 2023/3/20 15:18
 */
@Slf4j
@Service
public class MailServiceImpl implements IMailService {

    @Resource
    MailProperties mailProperties;

    @Override
    public void sendMailMessage(String toEmail,String title, String content) {
        MailAccount mailAccount = new MailAccount()
                .setFrom(mailProperties.getFrom())
                .setHost(mailProperties.getHost())
                .setPort(mailProperties.getPort())
                .setSslEnable(mailProperties.isSslEnable())
                .setAuth(mailProperties.isAuth())
                .setUser(mailProperties.getFrom())
                .setPass(mailProperties.getPassword()); // 登录账号密码
        String messageId = MailUtil.send(mailAccount, toEmail, title, content, false);
        log.info("发送邮件成功,messageId:{}",messageId);
    }
}
