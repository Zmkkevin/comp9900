package com.project.comp9900.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.comp9900.base.constants.MessageConstant;
import com.project.comp9900.dao.UserMapper;
import com.project.comp9900.domain.entity.User;
import com.project.comp9900.domain.param.ChangeQuery;
import com.project.comp9900.enums.HttpStatusEnum;
import com.project.comp9900.utils.JsonData;
import com.project.comp9900.utils.token.TokenHelper;
import com.project.comp9900.utils.token.TokenModel;
import io.netty.util.internal.ObjectUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
@Transactional
public class AccountService {

    @Resource
    private TokenHelper tokenHelper;

    @Resource
    UserMapper userMapper;

    private final CacheManager cacheManager;

    /**
     * 登录
     */
    public Object userLogin(User logInUser) {
        User user = userMapper.findByName(logInUser.getUsername());
        if(user == null || !user.getPassword().equals(logInUser.getPassword())) {
            return JsonData.buildError(HttpStatusEnum.NOT_FOUND.getCode(), MessageConstant.USERNAME_OR_PASSWORD_ERROR);
        }
        TokenModel model = tokenHelper.create(user.getId());
        return JsonData.buildSuccess(model);
    }

    /**
     * 修改密码
     */
    public Object changePassword(ChangeQuery changeQuery) {
        User user = userMapper.findByName(changeQuery.getUserName());
        if (changeQuery.getOldPassword().equals(user.getPassword())) {
            userMapper.update(User.builder().password(changeQuery.getNewPassword()).build(),
                    new QueryWrapper<User>().in("username",changeQuery.getUserName()));
            return JsonData.buildSuccess("更新密码成功");
        } else {
            return JsonData.buildError(HttpStatusEnum.NOT_FOUND.getCode(), MessageConstant.PASSWORD_ERROR);
        }
    }

    /**
     * 检查用户是否存在
     */
//    private void checkUser(User user) {
//        if (user == null) {
//            throw new RuntimeException("用户不存在");
//        }
//        if (Constant.UserStatus.disabled.name().equals(user.getStatus())) {
//            throw new MyException("用户已被禁用");
//        }
//    }

    /**
     * 注册账户
     */
    public Object userLogon(User newUser) {
        //检查用户是否存在
        User user = userMapper.findByName(newUser.getUsername());
        if(!ObjectUtils.isEmpty(user)) {
            //注册用户名已经存在：
            return JsonData.buildError(HttpStatusEnum.NOT_FOUND.getCode(), MessageConstant.USER_ERROR);
        }else{
            //开始进行注册：数据入库
            userMapper.insert(newUser);
        }
        return JsonData.buildSuccess("LogOn success");
    }
}
