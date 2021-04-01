package com.rookiefly.open.oktools.controller;

import com.rookiefly.open.oktools.service.OkToolsService;
import com.rookiefly.open.oktools.util.CommonUtil;
import com.rookiefly.open.oktools.util.IpUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Classname OkToolsController
 * @Description TODO
 * @Date 2021/3/22 4:01 下午
 * @Created by rookiefly
 */
@Controller
public class OkToolsController {

    public static final String IMAGE_SVG_XML = "image/svg+xml";

    public static final String PH_SVG_FORMAT = "<svg xmlns='http://www.w3.org/2000/svg' width='%d' height='%d'><rect x='0' y='0' width='%d' height='%d' fill='#%s'/><text x='50%%' y='50%%' style='dominant-baseline:middle;text-anchor:middle;font-size:%dpx' fill='#%s'>%s</text></svg>";

    @Resource
    private OkToolsService okToolsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("tools", okToolsService.queryToolsList());
        String baseUrl = CommonUtil.getBaseUrl(request);
        model.addAttribute("baseUrl", baseUrl);
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
    public String placeholder(HttpServletRequest request, Model model) {
        String baseUrl = CommonUtil.getBaseUrl(request);
        model.addAttribute("baseUrl", baseUrl);
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

    @RequestMapping(value = "/clipboard", method = RequestMethod.GET)
    public String textClipboard() {
        return "clipboard";
    }

    @RequestMapping(value = "/ph/{size}", method = RequestMethod.GET, produces = IMAGE_SVG_XML)
    public void getSVG(@PathVariable String size, @RequestParam(required = false, name = "t") String text,
                       @RequestParam(required = false) String bg, @RequestParam(required = false) String fg,
                       HttpServletResponse response) throws IOException {
        String defaultBackground = "CCC";
        String defaultForeground = "FFF";
        int defaultWidth = 800;
        int defaultHeight = 200;
        int width = defaultWidth;
        int height = defaultHeight;
        String[] sizeArr = size.split("x");
        if (sizeArr.length == 2) {
            width = Integer.parseInt(sizeArr[0]);
            height = Integer.parseInt(sizeArr[1]);
        } else if (sizeArr.length == 1) {
            width = height = Integer.parseInt(sizeArr[0]);
        }
        text = StringUtils.isBlank(text) ? width + "x" + height : text;
        Integer fontSize = (int) Math.min((double) width / (double) text.length(), (double) height);
        String svgText = String.format(PH_SVG_FORMAT,
                width, height, width, height,
                bg == null ? defaultBackground : bg,
                fontSize,
                fg == null ? defaultForeground : fg,
                text);
        response.setContentType(IMAGE_SVG_XML);
        OutputStream outStream = response.getOutputStream();
        IOUtils.write(svgText, outStream, StandardCharsets.UTF_8);
    }
}
