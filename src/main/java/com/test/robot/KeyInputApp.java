package com.test.robot;

import java.awt.*;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.User32Util;
import com.sun.jna.platform.win32.WinDef;

public class KeyInputApp {

    public static final Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            boolean desktopSupported = Desktop.isDesktopSupported();
            System.out.println(desktopSupported);
//            WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "Google Chrome");
//            System.out.println(hwnd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
