package com.rookiefly.open.oktools.controller;

import com.rookiefly.open.oktools.service.TextClipboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextClipboardController {

    private static Logger logger = LoggerFactory.getLogger(TextClipboardController.class);

    @Autowired
    private TextClipboardService textClipboardService;

    @PostMapping("/clipboard")
    public String saveText(@RequestParam String data) {
        logger.info("save input text: {}", data);
        String hash = textClipboardService.saveText(data);
        return hash;
    }

    @GetMapping("/clipboard/{textId}")
    public String queryText(@PathVariable("textId") String textId) {
        logger.info("query text id: {}", textId);
        String content = textClipboardService.queryText(textId);
        return content;
    }
}
