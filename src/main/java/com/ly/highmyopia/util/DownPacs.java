package com.ly.highmyopia.util;
import com.ly.highmyopia.common.dto.ListFollowup;
import com.ly.highmyopia.entity.Caselist;
import com.ly.highmyopia.entity.Examdetail;
import com.ly.highmyopia.mapper.CaselistMapper;
import com.ly.highmyopia.mapper.ExamdetailMapper;
import com.ly.highmyopia.mapper.FollowupMapper;
import com.ly.highmyopia.mapper.PatientMapper;
import com.ly.highmyopia.service.CaselistService;
import com.ly.highmyopia.service.ExamdetailService;
import com.ly.highmyopia.service.FollowupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 *
 * 第十步  DownPacs 下载图片
 * 下一步  DownPacs
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Slf4j
public class DownPacs {
    @Autowired
    ExamdetailMapper examdetailMapper;
    @Autowired
    ExamdetailService examdetailService;
    @Autowired
    CaselistMapper caselistMapper;
    @Autowired
    CaselistService caselistService;


    //3.添加定时任务
//    @Scheduled(cron = "0 40 16 * * * ?")
    //或直接指定时间间隔，例如：5秒
    private void downloadPacs() {
                System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
                List<Examdetail> examdetails = examdetailMapper.getDownloadFileList();
                if (examdetails != null && !examdetails.isEmpty()) {
                    for (Examdetail item : examdetails) {
                        if ("opt".equals(item.getDev())) {
                            System.out.println(item.getExamId() + " " + item.getLocalpath());
                            String down_url_path = "http://10.12.5.55/pacs/getDetailFile?detail_id=" + item.getId();
                            String filename = item.getPath().split("\\\\")[item.getPath().split("\\\\").length - 1];
                            //      注意下载到本地的路径
                            DownFile.downloadFile(down_url_path, "C:/Users/Administrator/Desktop/nginx-1.18.0/html/img/OPT/", filename);
                            Examdetail temp = examdetailService.getById(item.getId());
                            temp.setDownfile(1);
                            temp.setLocalpath("/img/OPT/" + filename);
                            examdetailService.saveOrUpdate(temp);
                        } else if ("iol".equals(item.getDev())) {
                            String down_url_path = "http://10.12.5.55/pacs/getDetailFile?detail_id=" + item.getId();
                            String filename = item.getPath().split("\\\\")[item.getPath().split("\\\\").length - 1];
                            //      注意下载到本地的路径
                            DownFile.downloadFile(down_url_path, "C:/Users/Administrator/Desktop/nginx-1.18.0/html/img/IOL/", filename);
                            System.out.println(filename);
                            Examdetail temp = examdetailService.getById(item.getId());
                            //      自动调用PDF识别
                            String path = "C:/Users/Administrator/Desktop/nginx-1.18.0/html/img/IOL/" + filename;
                            System.out.println(path);
                            System.out.println(item.getExamId());
                            PdfRead pdfRead = new PdfRead();
                            String[] str = new String[2];
                            try {
                                str = pdfRead.iolRead(path, item.getExamId());
                                for (Caselist cl : caselistMapper.selectCaseByIOLExamId(item.getExamId())) {
                                    cl.setALOD(str[0]);
                                    cl.setALOS(str[1]);
                                    System.out.println(str[0]);
                                    System.out.println(str[1]);
                                    caselistService.saveOrUpdate(cl);
                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            //      调用结束
                            temp.setDownfile(1);
                            temp.setLocalpath("/img/IOL/" + filename);
                            examdetailService.saveOrUpdate(temp);
                        } else if("oct".equals(item.getDev())) {
                            String down_url_path = "http://10.12.5.36/pacs/getDetailFile?detail_id=" + item.getId();
                            String filename = item.getPath().split("\\\\")[item.getPath().split("\\\\").length - 1];
                            //      注意下载到本地的路径
                            DownFile.downloadFile(down_url_path, "C:/Users/Administrator/Desktop/nginx-1.18.0/html/img/OCT/", filename);
                            Examdetail temp = examdetailService.getById(item.getId());
                            temp.setDownfile(1);
                            temp.setLocalpath("/img/OCT/" + filename);
                            examdetailService.saveOrUpdate(temp);
                        }
                    }
                }
            }
}
