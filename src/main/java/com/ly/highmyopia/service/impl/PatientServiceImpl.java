package com.ly.highmyopia.service.impl;

import com.ly.highmyopia.entity.Patient;
import com.ly.highmyopia.mapper.PatientMapper;
import com.ly.highmyopia.service.PatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangyue
 * @since 2021-02-05
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {

}
