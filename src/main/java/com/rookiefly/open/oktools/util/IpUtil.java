package com.rookiefly.open.oktools.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname IpUtil
 * @Description ip地址工具类
 * @Date 2021/3/22 6:15 下午
 * @Created by rookiefly
 */
public class IpUtil {

    private static final String pattern = "(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})(\\.(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})){3}";

    private static final Pattern IP_PATTERN = Pattern.compile(pattern);

    private static final Pattern CHINAZ_IP_PATTERN = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");

    private static boolean checkIPV4(String ip) {
        return IP_PATTERN.matcher(ip).matches();
    }

    /**
     * 获取本机的内网ip地址
     *
     * @return
     * @throws SocketException
     */
    public static String getInnerIp() throws SocketException {
        // 本地IP，如果没有配置外网IP则返回它
        String localip = null;
        // 外网IP
        String netip = null;
        Enumeration<NetworkInterface> netInterfaces;
        netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip;
        // 是否找到外网IP
        boolean finded = false;
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress()
                        && !ip.isLoopbackAddress()
                        && !ip.getHostAddress().contains(":")) {
                    // 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress()
                        && !ip.isLoopbackAddress()
                        && !ip.getHostAddress().contains(":")) {
                    // 内网IP
                    localip = ip.getHostAddress();
                }
            }
        }
        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }

    /**
     * 获取本机的外网ip地址
     *
     * @return
     */
    public static String getV4IP() {
        String chinaz = "http://ip.chinaz.com/?wscckey=9dd470f3cc727ec4_1616481449";

        StringBuilder inputLine = new StringBuilder();
        BufferedReader in = null;
        try {
            URL url = new URL(chinaz);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String read;
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Matcher m = CHINAZ_IP_PATTERN.matcher(inputLine.toString());
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

    /**
     * 测试方法
     * 获取本机的内网ip，外网ip和指定ip的地址
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        System.out.println(IpUtil.checkIPV4("183.134.211.52"));

        String ip1 = IpUtil.getInnerIp();
        System.out.println("内网ip:" + ip1);
        String ip2 = IpUtil.getV4IP();
        System.out.println("外网ip:" + ip2);
    }
}
