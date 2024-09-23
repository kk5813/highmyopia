package com.ly.highmyopia.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.highmyopia.common.dto.LoginDto;
import com.ly.highmyopia.common.lang.Result;
import com.ly.highmyopia.entity.User;
import com.ly.highmyopia.service.UserService;
import com.ly.highmyopia.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class AccountController {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;


    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        log.info("用户登录");
        User user = userService.getOne(new QueryWrapper<User>().eq("user_login_name", loginDto.getUserLoginName()));
        Assert.notNull(user, "用户不存在");


        log.info(user.getUserPassword());
        log.info(loginDto.getUserPassword());
        if (!user.getUserPassword().equals(SecureUtil.md5(user.getSalt() + loginDto.getUserPassword())))
            return Result.fail("密码错误！");
        String jwt = jwtUtils.generateToken(user.getUserId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        log.info("jwt:" + jwt);
        // 用户可以另一个接口
        return Result.succ(MapUtil.builder()
                .put("userId", user.getUserId())
                .put("userLoginName", user.getUserLoginName())
                .put("userName", user.getUserName())
                .map()
        );
    }

    // 退出
    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
