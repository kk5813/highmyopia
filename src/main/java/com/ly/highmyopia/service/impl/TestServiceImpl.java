package com.ly.highmyopia.service.impl;

import com.ly.highmyopia.entity.Test;
import com.ly.highmyopia.mapper.TestMapper;
import com.ly.highmyopia.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangyue
 * @since 2021-05-16
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}
