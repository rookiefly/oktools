package com.rookiefly.open.oktools.service.impl;

import com.rookiefly.open.oktools.mapper.ToolMapper;
import com.rookiefly.open.oktools.model.Tool;
import com.rookiefly.open.oktools.service.OkToolsService;
import com.rookiefly.open.oktools.util.IpUtil;
import com.rookiefly.open.oktools.vo.IpInfoVO;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "toolsCache", key = "targetClass + methodName")
    public List<Tool> queryToolsList() {
        return toolMapper.queryToolsList();
    }

    @Override
    @Cacheable(value = "ipInfoCache", key = "targetClass + methodName + #ip")
    public IpInfoVO queryInInfo(String ip) {
        try {
            String ipInfo = IpUtil.getIpInfo(ip);
            String[] splitIpInfo = ipInfo.split("\\|");
            IpInfoVO ipInfoVO = new IpInfoVO();
            ipInfoVO.setIp(ip);
            ipInfoVO.setProvince(splitIpInfo[2]);
            ipInfoVO.setCity(splitIpInfo[3]);
            ipInfoVO.setCarrier(splitIpInfo[4]);
            return ipInfoVO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
