package com.ly.highmyopia.mapper;

import com.ly.highmyopia.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangyue
 * @since 2021-02-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user")
    public List<User> list();
}
