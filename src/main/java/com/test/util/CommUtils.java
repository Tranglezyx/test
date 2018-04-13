package com.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Locale;

public class CommUtils {

    private CommUtils(){

    }

    /**
     * Return Opertaion System Name;
     *
     * @return os name.
     */
    public static String getOsName() {
        String os = "";
        os = System.getProperty("os.name");
        return os;
    }

    /**
     * Returns the MAC address of the computer.
     *
     * @return the MAC address
     */
    public static String getMACAddress() {
        String address = "";
        String os = getOsName();
        if (os.startsWith("Windows")) {
            try {
                String command = "cmd.exe /c ipconfig /all";
                Process p = Runtime.getRuntime().exec(command);
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.indexOf("Physical Address") > 0) {
                        int index = line.indexOf(":");
                        index += 2;
                        address = line.substring(index);
                        break;
                    }
                }
                br.close();
                return address.trim();
            } catch (IOException e) {
            }
        } else if (os.startsWith("Linux")) {
            String command = "/bin/sh -c ifconfig -a";
            Process p;
            try {
                p = Runtime.getRuntime().exec(command);
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.indexOf("HWaddr") > 0) {
                        int index = line.indexOf("HWaddr") + "HWaddr".length();
                        address = line.substring(index);
                        break;
                    }
                }
                br.close();
            } catch (IOException e) {
            }
        }
        address = address.trim();
        return address;
    }

    /**
     * 获取Linux下的IP地址
     * @author yunxiang.zhou01@hand-china.com
     * @return IP地址
     * @throws SocketException
     */
    public static String getLinuxLocalIp() throws SocketException {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress().toString();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                ip = ipaddress;
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            ip = "127.0.0.1";
            ex.printStackTrace();
        }
        return ip;
    }

    /**
     * 当前主机对象
     */
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
