package com.rookiefly.open.oktools.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 接口响应数据
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String SUCCESS_CODE = "200";
    public static final String SUCCESS_MSG = "success";
    public static final String ERROR_CODE = "-1";
    public static final String ERROR_MSG = "error";

    private String code;

    private String msg;

    private Object data;

    public static CommonResponse newSuccessResponse(Object data) {
        return new CommonResponse(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static CommonResponse newErrorResponse() {
        return newErrorResponse(ERROR_CODE, ERROR_MSG);
    }

    public static CommonResponse newErrorResponse(String errorCode, String errorMsg) {
        return new CommonResponse(errorCode, errorMsg, null);
    }
}