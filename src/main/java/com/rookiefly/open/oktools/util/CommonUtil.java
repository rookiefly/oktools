package com.rookiefly.open.oktools.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname CommonUtil
 * @Description 工具类
 * @Date 2021/4/1 5:05 下午
 * @Created by rookiefly
 */
public class CommonUtil {

    public static String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getHeader("X-Forwarded-Proto") == null ? request.getScheme() : request.getHeader("X-Forwarded-Proto");
        return scheme + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
