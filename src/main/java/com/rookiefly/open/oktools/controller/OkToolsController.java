package com.rookiefly.open.oktools.controller;

import com.rookiefly.open.oktools.service.OkToolsService;
import com.rookiefly.open.oktools.util.IpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Classname OkToolsController
 * @Description TODO
 * @Date 2021/3/22 4:01 下午
 * @Created by rookiefly
 */
@Controller
public class OkToolsController {

    @Resource
    private OkToolsService okToolsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("tools", okToolsService.queryToolsList());
        return "index";
    }

    @RequestMapping(value = "/color", method = RequestMethod.GET)
    public String color() {
        return "color";
    }

    @RequestMapping(value = "/base64", method = RequestMethod.GET)
    public String base64() {
        return "base64";
    }

    @RequestMapping(value = "/image2base64", method = RequestMethod.GET)
    public String image2base64() {
        return "image2base64";
    }

    @RequestMapping(value = "/tinyimg", method = RequestMethod.GET)
    public String tinyimg() {
        return "tinyimg";
    }

    @RequestMapping(value = "/hash", method = RequestMethod.GET)
    public String hash() {
        return "hash";
    }

    @RequestMapping(value = "/file-hash", method = RequestMethod.GET)
    public String filehash() {
        return "file_hash";
    }

    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public String ip(HttpServletRequest request, Model model) {
        model.addAttribute("ip", IpUtil.getIpAddr(request));
        return "ip";
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public String json() {
        return "json";
    }

    @RequestMapping(value = "/number", method = RequestMethod.GET)
    public String number() {
        return "number";
    }

    @RequestMapping(value = "/placeholder", method = RequestMethod.GET)
    public String placeholder() {
        return "placeholder";
    }

    @RequestMapping(value = "/qrcode", method = RequestMethod.GET)
    public String qrcode() {
        return "qrcode";
    }

    @RequestMapping(value = "/regex", method = RequestMethod.GET)
    public String regex() {
        return "regex";
    }

    @RequestMapping(value = "/timestamp", method = RequestMethod.GET)
    public String timestamp() {
        return "timestamp";
    }

    @RequestMapping(value = "/websocket", method = RequestMethod.GET)
    public String websocket() {
        return "websocket";
    }

    @RequestMapping(value = "/aes", method = RequestMethod.GET)
    public String aes() {
        return "aes";
    }

    @RequestMapping(value = "/des", method = RequestMethod.GET)
    public String des() {
        return "des";
    }

    @RequestMapping(value = "/rsa", method = RequestMethod.GET)
    public String rsa() {
        return "rsa";
    }

    @RequestMapping(value = "/morse", method = RequestMethod.GET)
    public String morse() {
        return "morse";
    }

    @RequestMapping(value = "/url", method = RequestMethod.GET)
    public String url() {
        return "url";
    }

    @RequestMapping(value = "/unicode", method = RequestMethod.GET)
    public String unicode() {
        return "unicode";
    }

    @RequestMapping(value = "/json2go", method = RequestMethod.GET)
    public String json2go() {
        return "json2go";
    }

    @RequestMapping(value = "/json2xml", method = RequestMethod.GET)
    public String json2xml() {
        return "json2xml";
    }

    @RequestMapping(value = "/json2yaml", method = RequestMethod.GET)
    public String json2yaml() {
        return "json2yaml";
    }

    @RequestMapping(value = "/pdf2img", method = RequestMethod.GET)
    public String pdf2img() {
        return "pdf2img";
    }
}
