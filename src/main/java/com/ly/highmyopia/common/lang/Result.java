package com.ly.highmyopia.common.lang;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private Object data;
    public static Result succ(Object data) {
        return succ("操作成功",data);
    }
    public static Result succ(String mess, Object data) {
        Result m = new Result();
        m.setCode(200);
        m.setData(data);
        m.setMsg(mess);
        return m;
    }
    public static Result fail(String mess) {
        return fail(400, mess, null);
    }
    public static Result fail(String mess, Object data) {
        return fail(400, mess, data);
    }
    public static Result fail(int code, String mess, Object data) {
        Result m = new Result();
        m.setCode(code);
        m.setData(data);
        m.setMsg(mess);
        return m;
    }
}
