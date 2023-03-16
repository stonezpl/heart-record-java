package com.stonezpl.hr.service;

import com.stonezpl.hr.entity.WxRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 记录表 服务类
 * </p>
 *
 * @author 张珮磊
 * @since 2023-03-08
 */
public interface IWxRecordService extends IService<WxRecord> {

    boolean saveRecord(WxRecord wxRecord);

}
