package com.ly.highmyopia.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.ly.highmyopia.common.lang.Result;
import com.ly.highmyopia.entity.Patient;
import com.ly.highmyopia.entity.Shortinfo;
import com.ly.highmyopia.mapper.PatientMapper;
import com.ly.highmyopia.mapper.ShortinfoMapper;
import com.ly.highmyopia.service.PatientService;
import com.ly.highmyopia.service.ShortinfoService;
import com.ly.highmyopia.shiro.AccountProfile;
import com.ly.highmyopia.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangyue
 * @since 2021-02-05
 */
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;
    @Autowired
    ShortinfoService shortinfoService;
    @Autowired
    PatientMapper patientMapper;
    @Autowired
    ShortinfoMapper shortinfoMapper;

    // 测试用
    @GetMapping("/index")
    public Object index() {
        Patient patient = patientService.getById(1L);
        return Result.succ(patient);
    }

    //     患者列表
    @GetMapping("/list")
    @RequiresAuthentication
    public Result list() {
        List<Patient> patientList = patientService.list();
        return Result.succ(patientList);
    }

    //          编辑患者
    @PostMapping("/edit")
    @RequiresAuthentication
    public Result editUser( @RequestBody Patient patient) {
        patientService.saveOrUpdate(patient);
        return Result.succ(null);
    }

    //          根据ID查询患者简要病情        注意：是id不是patientId
    @GetMapping("/shortinfo/{id}")
    @RequiresAuthentication
    public Result patientInfo(@PathVariable(name = "id") Long id) {
        Shortinfo shortinfo = shortinfoService.getById(id);
        if(shortinfo == null) {
            return Result.fail("无数据");
        }
        return Result.succ(shortinfo);
    }

    //          根据patient_id查询患者简要病情
    @GetMapping("/shortinfoByPid/{id}")
    @RequiresAuthentication
    public Result patientInfoByPid(@PathVariable(name = "id") String id) {
        Shortinfo shortinfo = shortinfoMapper.selectShortinfoByPId(id);
        if(shortinfo == null) {
            return Result.fail("无数据");
        }
        return Result.succ(shortinfo);
    }


    //          编辑简要病情
    @PostMapping("/editshortinfo")
    @RequiresAuthentication
    public Result editPatientInfo(@RequestBody Shortinfo shortinfo) {
        shortinfoService.saveOrUpdate(shortinfo);
        return Result.succ(null);
    }

    //      根据patientId查询患者基本信息
    @GetMapping("/patientIndex/{id}")
    @RequiresAuthentication
    public Result patientByPatientId(@PathVariable(name = "id") String id) {
        return Result.succ(patientMapper.selectPatientByPId(id));
    }


}
