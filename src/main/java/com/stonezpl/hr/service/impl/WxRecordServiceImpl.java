package com.stonezpl.hr.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import com.stonezpl.hr.constants.RedisTaskEnum;
import com.stonezpl.hr.entity.WxRecord;
import com.stonezpl.hr.mapper.WxRecordMapper;
import com.stonezpl.hr.service.IMessageService;
import com.stonezpl.hr.service.IWxRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public boolean saveRecord(WxRecord wxRecord) {
        boolean result = this.save(wxRecord);
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("openId",wxRecord.getOpenId());
        jsonObject.set("title",wxRecord.getRecordTitle());
        jsonObject.set("content",wxRecord.getRecordContent());
        SubscribeTask subscribeTask = new SubscribeTask(IdUtil.fastUUID(),
                jsonObject.toString(),
                RedisTaskEnum.subscribe.getName(),
                wxRecord.getRemindTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
       messageService.sendDelayMessage(subscribeTask);
        return result;
    }
}
