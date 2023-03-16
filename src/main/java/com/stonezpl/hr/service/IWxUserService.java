package com.stonezpl.hr.service;

import com.stonezpl.hr.controller.vo.UserReqVO;
import com.stonezpl.hr.entity.WxUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author stonezpl
 * @since 2023-03-11
 */
public interface IWxUserService extends IService<WxUser> {

    /**
     * 保存或者更新用户信息
     * @param userReqVO
     * @return
     */
    Boolean saveOrUpdateUser(UserReqVO userReqVO);

}
