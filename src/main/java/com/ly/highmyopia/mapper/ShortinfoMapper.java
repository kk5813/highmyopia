package com.ly.highmyopia.mapper;

import com.ly.highmyopia.entity.Patient;
import com.ly.highmyopia.entity.Shortinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangyue
 * @since 2021-02-07
 */
@Mapper
@Component
public interface ShortinfoMapper extends BaseMapper<Shortinfo> {

    @Select("select * from shortinfo where patient_id = #{id}")
    Shortinfo selectShortinfoByPId(@Param("id") String id);
}
