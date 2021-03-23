package com.rookiefly.open.oktools.service.impl;

import com.rookiefly.open.oktools.mapper.ToolMapper;
import com.rookiefly.open.oktools.model.Tool;
import com.rookiefly.open.oktools.service.OkToolsService;
import com.rookiefly.open.oktools.vo.IpInfo;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Resource
    private DbSearcher dbSearcher;

    @Override
    @Cacheable(value = "toolsCache", key = "targetClass + methodName")
    public List<Tool> queryToolsList() {
        return toolMapper.queryToolsList();
    }

    @Override
    @Cacheable(value = "ipInfoCache", key = "targetClass + methodName + #ip")
    public IpInfo queryInInfo(String ip) {
        try {
            DataBlock dataBlock = dbSearcher.btreeSearch(ip);
            if (dataBlock == null || !StringUtils.hasText(dataBlock.getRegion())) {
                return null;
            }
            String ipInfo = dataBlock.getRegion();
            String[] splitIpInfo = ipInfo.split("\\|");
            IpInfo ipInfoVO = new IpInfo();
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
