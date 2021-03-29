package com.rookiefly.open.oktools.controller;

import com.rookiefly.open.oktools.service.TextClipboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TextClipboardController {

    private static Logger logger = LoggerFactory.getLogger(TextClipboardController.class);

    @Autowired
    private TextClipboardService textClipboardService;

    @GetMapping("/clipboard/share")
    public String saveText(@RequestParam String text, HttpServletRequest request) {
        logger.info("save input text: {}", text);
        String hash = textClipboardService.saveText(text);
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        return String.format("%s/clipboard/%s", baseUrl, hash);
    }

    @GetMapping("/clipboard/{hash}")
    public String queryText(@PathVariable("hash") String hash) {
        logger.info("query hash: {}", hash);
        String content = textClipboardService.queryTextByHash(hash);
        return content;
    }
}
