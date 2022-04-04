package com.project.comp9900.controller;

import com.project.comp9900.base.constants.MessageConstant;
import com.project.comp9900.dao.UserMapper;
import com.project.comp9900.domain.entity.User;
import com.project.comp9900.domain.param.ChangeQuery;
import com.project.comp9900.enums.HttpStatusEnum;
import com.project.comp9900.service.AccountService;
import com.project.comp9900.utils.JsonData;
import com.project.comp9900.utils.token.TokenHelper;
import com.project.comp9900.utils.token.TokenModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AccountController {

    @Resource
    AccountService accountService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private TokenHelper tokenHelper;

    /**
     * 登录验证
     */
    @GetMapping("/login")
    public Object login(@RequestBody User newuser) {
        return accountService.userLogin(newuser);
    }

    /**
     * 注册账户
     *
     * return 生成的特定tokenID 以便于之后用户验证
     */
    @GetMapping("/logon")
    public Object logon(@RequestBody User user) {
        return accountService.userLogon(user);
    }

    /**
     * 老用户修改密码功能
     */
    @ApiOperation("修改密码")
    @GetMapping("/changePassword")
    public void changePassword(@RequestBody ChangeQuery changeQuery) {
        accountService.changePassword(changeQuery);
    }
}
