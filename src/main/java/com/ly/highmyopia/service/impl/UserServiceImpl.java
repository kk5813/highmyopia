package com.ly.highmyopia.service.impl;

import com.ly.highmyopia.entity.User;
import com.ly.highmyopia.mapper.UserMapper;
import com.ly.highmyopia.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangyue
 * @since 2021-02-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
