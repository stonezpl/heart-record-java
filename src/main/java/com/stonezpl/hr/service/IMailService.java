package com.stonezpl.hr.service;

/**
 * @author stonezpl
 * @Description 邮件操作类
 * @date 2023/3/20 15:11
 */
public interface IMailService {

    void sendMailMessage(String toEmail,String title,String content);
}
