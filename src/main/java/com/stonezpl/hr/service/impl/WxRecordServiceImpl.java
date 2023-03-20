package com.stonezpl.hr.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stonezpl.hr.constants.RedisTaskEnum;
import com.stonezpl.hr.entity.WxRecord;
import com.stonezpl.hr.entity.WxUser;
import com.stonezpl.hr.mapper.WxRecordMapper;
import com.stonezpl.hr.service.IMessageService;
import com.stonezpl.hr.service.IWxRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stonezpl.hr.service.IWxUserService;
import com.stonezpl.hr.task.MailTask;
import com.stonezpl.hr.task.SubscribeTask;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

/**
 * <p>
 * 记录表 服务实现类
 * </p>
 *
 * @author 张珮磊
 * @since 2023-03-08
 */
@Service
public class WxRecordServiceImpl extends ServiceImpl<WxRecordMapper, WxRecord> implements IWxRecordService {

    @Resource
    private IMessageService messageService;

    @Resource
    private IWxUserService userService;

    @Override
    public boolean saveRecord(WxRecord wxRecord) {
        boolean result = this.save(wxRecord);
        WxUser wxUser = userService.getOne(new QueryWrapper<WxUser>().eq("open_id",wxRecord.getOpenId()));
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("openId",wxRecord.getOpenId());
        jsonObject.set("title",wxRecord.getRecordTitle());
        jsonObject.set("content",wxRecord.getRecordContent());
        //订阅消息
        SubscribeTask subscribeTask = new SubscribeTask(IdUtil.fastUUID(),
                jsonObject.toString(),
                RedisTaskEnum.subscribe.getName(),
                wxRecord.getRemindTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
       messageService.sendDelayMessage(subscribeTask);
       //邮件
        if (StrUtil.isNotBlank(wxUser.getEmail())){
            MailTask mailTask = new MailTask(IdUtil.fastUUID(),
                    jsonObject.toString(),
                    RedisTaskEnum.mail.getName(),
                    wxRecord.getRemindTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            messageService.sendDelayMessage(mailTask);
        }
        return result;
    }
}
