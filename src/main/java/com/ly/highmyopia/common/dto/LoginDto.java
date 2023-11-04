package com.ly.highmyopia.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {

    @NotBlank(message = "登录名不能为空")
    private String userLoginName;

    private String userPassword;
}
