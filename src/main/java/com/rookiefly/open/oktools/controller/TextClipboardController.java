package com.rookiefly.open.oktools.controller;

import com.rookiefly.open.oktools.service.TextClipboardService;
import com.rookiefly.open.oktools.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class TextClipboardController {

    private static Logger logger = LoggerFactory.getLogger(TextClipboardController.class);

    @Resource
    private TextClipboardService textClipboardService;

    @GetMapping("/clipboard/share")
    public String saveText(@RequestParam String text, HttpServletRequest request) {
        String hash = textClipboardService.saveText(text);
        String baseUrl = CommonUtil.getBaseUrl(request);
        return String.format("%s/clipboard/%s", baseUrl, hash);
    }

    @GetMapping("/clipboard/{hash}")
    public String queryText(@PathVariable("hash") String hash) {
        String content = textClipboardService.queryTextByHash(hash);
        return content;
    }
}
