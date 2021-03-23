package com.rookiefly.open.oktools.controller;

import com.rookiefly.open.oktools.common.CommonResponse;
import com.rookiefly.open.oktools.service.OkToolsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname OkToolsOpenApiController
 * @Description TODO
 * @Date 2021/3/23 4:01 下午
 * @Created by rookiefly
 */
@RestController
public class OkToolsOpenApiController {

    @Resource
    private OkToolsService okToolsService;

    @GetMapping(value = "/api/ip/{ip}")
    public CommonResponse queryIpInfo(@PathVariable String ip) {
        return CommonResponse.newSuccessResponse(okToolsService.queryInInfo(ip));
    }
}
