package com.ly.highmyopia.controller;


import cn.hutool.core.bean.BeanUtil;
import com.ly.highmyopia.common.lang.Result;
import com.ly.highmyopia.entity.User;
import com.ly.highmyopia.service.TestService;
import com.ly.highmyopia.shiro.AccountProfile;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangyue
 * @since 2021-05-16
 */
@RestController
@RequestMapping("/test")
public class TestController {
}
