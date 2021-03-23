package com.rookiefly.open.oktools.service;

import com.rookiefly.open.oktools.model.Tool;
import com.rookiefly.open.oktools.vo.IpInfoVO;

import java.util.List;

/**
 * @Classname OkToolsService
 * @Description TODO
 * @Date 2021/3/22 4:41 下午
 * @Created by rookiefly
 */
public interface OkToolsService {

    List<Tool> queryToolsList();

    IpInfoVO queryInInfo(String ip);
}
