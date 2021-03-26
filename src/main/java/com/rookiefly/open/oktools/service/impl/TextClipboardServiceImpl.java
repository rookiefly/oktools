package com.rookiefly.open.oktools.service.impl;

import com.google.common.hash.Hashing;
import com.rookiefly.open.oktools.mapper.TextClipboardMapper;
import com.rookiefly.open.oktools.service.TextClipboardService;
import com.rookiefly.open.oktools.util.ConversionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import java.nio.charset.Charset;

@Slf4j
@Service
public class TextClipboardServiceImpl implements TextClipboardService {

    private static Charset UTF8 = Charset.forName("utf-8");

    @Resource
    private TextClipboardMapper textClipboardMapper;

    @Override
    public String saveText(String text) {
        String encodeText = Base64Utils.encodeToUrlSafeString(text.getBytes(UTF8));
        String hash = Hashing.md5().hashString(encodeText, UTF8).toString();
        String dbText = textClipboardMapper.queryTextByHash(hash);
        if (dbText == null) {
            long key = textClipboardMapper.insert(hash, encodeText);
            if (key > 0) {
                return ConversionUtil.encode(key);
            }
        }
        return null;
    }

    @Override
    @Cacheable(value = "clipboardCache", key = "targetClass + methodName + #textId")
    public String queryText(String textId) {
        long id = ConversionUtil.decode(textId);
        String encodeText = textClipboardMapper.queryTextById(id);
        if (StringUtils.isNotBlank(encodeText)) {
            return new String(Base64Utils.decodeFromUrlSafeString(encodeText));
        }
        return null;
    }
}
