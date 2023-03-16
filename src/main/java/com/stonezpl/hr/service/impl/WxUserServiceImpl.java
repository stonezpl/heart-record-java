package com.stonezpl.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.stonezpl.hr.controller.vo.UserReqVO;
import com.stonezpl.hr.entity.WxUser;
import com.stonezpl.hr.mapper.WxUserMapper;
import com.stonezpl.hr.service.IWxUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author stonezpl
 * @since 2023-03-11
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements IWxUserService {

    @Override
    public Boolean saveOrUpdateUser(UserReqVO userReqVO) {
        WxUser wxUser = this.getOne(new QueryWrapper<WxUser>()
                .eq("open_id", userReqVO.getOpenId()));
        if (wxUser == null) {
            wxUser = new WxUser();
        } else {
            wxUser.setUpdateDate(LocalDateTime.now());
        }
        wxUser.setEmail(userReqVO.getEmail());
        wxUser.setAvatarUrl(userReqVO.getAvatarUrl());
        wxUser.setNickName(userReqVO.getNickName());
        wxUser.setOpenId(userReqVO.getOpenId());
        return this.saveOrUpdate(wxUser);
    }
}
