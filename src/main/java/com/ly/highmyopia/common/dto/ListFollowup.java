package com.ly.highmyopia.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/***
 * 随访需要的类
 *
 *
 */
@Data
public class ListFollowup {
    private Long id;

    private Integer caseId;

    private String patientId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime planVisitDate;

    private String visitPlan;

    private Boolean visitResult;

    private String visitContent;

    private String visitRemark;

    private LocalDateTime visitDate;

    private String patientName;

    private String gender;

    private String telephone;

    private LocalDateTime nextVisitDate;
}
