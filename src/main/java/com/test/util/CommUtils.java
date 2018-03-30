package com.test.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Locale;

public class CommUtils {

    private CommUtils(){

    }

    private static InetAddress ADDRESS;

    /**
     * 得到当前主机的mac地址
     * @return
     * @throws SocketException
     * @throws UnknownHostException
     */
    public static String getMac() throws SocketException, UnknownHostException {
        InetAddress address = getAddress();
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
        byte[] mac = ni.getHardwareAddress();
        String sMAC = "";
        Formatter formatter = new Formatter();
        for (int i = 0; i < mac.length; i++) {
            sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
                    (i < mac.length - 1) ? "-" : "").toString();
        }
        return sMAC;
    }

    /**
     * 得到当前主机的ip
     * @return
     * @throws UnknownHostException
     * @throws SocketException
     */
    public static String getIp() throws UnknownHostException, SocketException {
        InetAddress address = getAddress();
        return address.getHostAddress();
    }

    /**
     * 获得当前地址对象
     * @return
     * @throws UnknownHostException
     */
    private synchronized static InetAddress getAddress() throws UnknownHostException {
        if(ADDRESS == null){
            ADDRESS = InetAddress.getLocalHost();
        }
        return ADDRESS;
    }

    private static long SEQ = 888888888L;

    /**
     * 线程安全的获取全局唯一的字符串
     * @author yunxiang.zhou01@hand-china.com
     * @param userName 用来获得当前用户名
     * @param source 来源
     * @return 唯一性的全局字符串
     */
    public synchronized static String getUniqueString(String userName,String source){
        StringBuilder uniqueStr = new StringBuilder("");
        uniqueStr.append(source)
                .append(userName)
                .append(System.currentTimeMillis())
                .append("00000000");
        int length = new String(SEQ + "").length();
        SEQ++;
        if(length > 8){
            return uniqueStr.substring(0,uniqueStr.length() - 8) + SEQ;
        }else{
            return uniqueStr.substring(0,uniqueStr.length() - length) + SEQ;
        }
    }
}
