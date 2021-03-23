package com.rookiefly.open.oktools.service.impl;

import com.rookiefly.open.oktools.mapper.ToolMapper;
import com.rookiefly.open.oktools.model.Tool;
import com.rookiefly.open.oktools.service.OkToolsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname OkToolsServiceImpl
 * @Description TODO
 * @Date 2021/3/22 4:44 下午
 * @Created by rookiefly
 */
@Service
public class OkToolsServiceImpl implements OkToolsService {

    @Resource
    private ToolMapper toolMapper;

    @Override
    public List<Tool> queryToolsList() {
        return toolMapper.queryToolsList();
    }
}
