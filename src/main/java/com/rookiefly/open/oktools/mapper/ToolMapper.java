package com.rookiefly.open.oktools.mapper;

import com.rookiefly.open.oktools.model.Tool;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ToolMapper {

    /**
     * 查询所有工具列表
     *
     * @return
     */
    List<Tool> queryToolsList();
}
