package com.rookiefly.open.oktools.model;

import lombok.Data;

/**
 * @Classname Tool
 * @Description TODO
 * @Date 2021/3/22 4:06 下午
 * @Created by rookiefly
 */
@Data
public class Tool {

    private String path;

    private String title;

    private String icon;

    private Integer category;

    private Integer usageCount;
}
