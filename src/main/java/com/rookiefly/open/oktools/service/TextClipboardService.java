package com.rookiefly.open.oktools.service;

public interface TextClipboardService {

    /**
     * 保存文本到数据库
     *
     * @param text
     * @return
     */
    String saveText(String text);

    /**
     * 根据文本ID查询文本内容
     *
     * @param textId
     * @return
     */
    String queryTextById(String textId);

    /**
     * 根据文本hash查询文本内容
     *
     * @param hash
     * @return
     */
    String queryTextByHash(String hash);
}
