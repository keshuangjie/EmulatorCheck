package com.jimmy.emulatorcheck;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;

import java.lang.reflect.Method;

/**
 * 模拟器检查工具类
 *
 * Created by Jimmy on 2017/9/7.
 */
public class EmulatorCheckTool {

    /**
     * 判断该手机是否是模拟器
     *
     * @return
     */
    public static boolean isEmulator() {
        boolean isEmulator = false;
        try {
            isEmulator =  Build.FINGERPRINT.startsWith("generic")
                    || Build.FINGERPRINT.startsWith("unknown")
                    || "unKnown".equals(Build.SERIAL)
                    || Build.MODEL.contains("google_sdk")
                    || Build.MODEL.contains("Emulator")
                    || Build.MODEL.contains("Android SDK built for x86")
                    || Build.MANUFACTURER.contains("Genymotion")
                    || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                    || "google_sdk".equals(Build.PRODUCT)
                    || isEmulatorOnSystem();
        } catch (Exception e) {
        }
        return isEmulator;
    }

    /**
     * 是否是模拟器 通过系统Bulid api判断，由于是隐藏的属性需要通过反射获取
     *
     * @return
     */
    public static boolean isEmulatorOnSystem() {
        return  getSystemProperties(MyApplication.getContext(), "ro.kernel.qemu").equals("1");
    }

    /**
     * 获取cpu 架构，模拟器一般是x86架构
     *
     * @return
     */
    public static String getCpuAbi() {
        return Build.CPU_ABI;
    }

    /**
     * 是否是平板设备
     *
     * @return true:平板,false:手机
     */
    public static boolean isTabletDevice() {
        boolean isTablet = false;
        if (MyApplication.getContext() != null) {
            isTablet = (MyApplication.getContext().getResources().getConfiguration().screenLayout
                    & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        }
        return isTablet;
    }

    /**
     * 是否支持蓝牙，夜神、天天模拟器显示TRUE
     *
     * @return
     */
    public static boolean supportBluetooth() {
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    /**
     * 是否支持拨打电话，夜神、天天模拟器该项返回FALSE
     *
     * @return
     */
    public static boolean canResolveTelephoneIntent() {
        boolean canResolveIntent = true;
        try {
            String url = "tel:" + "123456";
            Intent intent = new Intent();
            intent.setData(Uri.parse(url));
            intent.setAction(Intent.ACTION_DIAL);
            // 是否可以处理跳转到拨号的 Intent
            canResolveIntent = intent.resolveActivity(MyApplication.getContext().getPackageManager()) != null;
        } catch (Exception e) {
        }
        return canResolveIntent;
    }

    /**
     * 根据给定Key获取值.
     *
     * @return 如果不存在该key则返回空字符串
     */
    public static String getSystemProperties(Context context, String key) {
        String ret = "";
        try {
            ClassLoader cl = context.getClassLoader();
            Class SystemProperties = cl.loadClass("android.os.SystemProperties");
            //参数类型
            Class[] paramTypes = new Class[1];
            paramTypes[0] = String.class;
            Method get = SystemProperties.getMethod("get", paramTypes);
            //参数
            Object[] params = new Object[1];
            params[0] = new String(key);
            ret = (String) get.invoke(SystemProperties, params);
        } catch (Exception e) {
        }
        return ret;
    }

}
