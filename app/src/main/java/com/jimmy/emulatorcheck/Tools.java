package com.jimmy.emulatorcheck;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * Created by Jimmy on 2017/9/5.
 */
public class Tools {

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
            isEmulator = true;
        }
        return isEmulator;
//                || (!canResolveTelephoneIntent() && "x86".equals(Build.CPU_ABI));
    }

    public static String getFingerprint() {
        return "Build.FINGERPRINT: " + Build.FINGERPRINT;
    }

    public static String getModel() {
        return "Build.MODEL: " + Build.MODEL;
    }

    public static String getSerial() {
        return "Build.SERIAL: " + Build.SERIAL;
    }

    public static String getManufaturer() {
        return "Build.MANUFACTURER: " + Build.MANUFACTURER;
    }

    public static String getBrand() {
        return "Build.BRAND: " + Build.BRAND;
    }

    public static String getDevice() {
        return "Build.DEVICE: " + Build.DEVICE;
    }

    public static String getProduct() {
        return "Build.PRODUCT: " + Build.PRODUCT;
    }

    public static String getHardware() {
        return "Build.HARDWARE: " + Build.HARDWARE;
    }

    public static String resolveTelephoneIntent() {
        String url = "tel:" + "123456";
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));
        intent.setAction(Intent.ACTION_DIAL);
        // 是否可以处理跳转到拨号的 Intent
        boolean canResolveIntent = intent.resolveActivity(MyApplication.getContext().getPackageManager()) != null;
        return "canResolveTelephoneIntent: " + canResolveIntent;
    }

    /**
     * 没有卡的正常手机也可能返回FALSE
     * @return
     */
    public static boolean canResolveTelephoneIntent() {
        String url = "tel:" + "123456";
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));
        intent.setAction(Intent.ACTION_DIAL);
        // 是否可以处理跳转到拨号的 Intent
        boolean canResolveIntent = intent.resolveActivity(MyApplication.getContext().getPackageManager()) != null;
        return canResolveIntent;
    }

    public static String getNetworkOperatorName() {
        return "getNetworkOperatorName: " + ((TelephonyManager) MyApplication.getContext().getSystemService(Context
                .TELEPHONY_SERVICE))
                .getNetworkOperatorName();
    }

    public static String getEmulatorOnSystem() {
        return  "Build.IS_EMULATOR: " + SystemPropertiesProxy.get(MyApplication.getContext(), "ro.kernel.qemu").equals
                ("1");
    }

    public static boolean isEmulatorOnSystem() {
        return  SystemPropertiesProxy.get(MyApplication.getContext(), "ro.kernel.qemu").equals
                ("1");
    }

    public static String getCpuAbi() {
        return "Build.CPU_ABI: " + Build.CPU_ABI;
    }

    public static String getCpuAbi2() {
        return "Build.CPU_ABI2: " + Build.CPU_ABI2;
    }

    /**
     * 夜神模拟器显示TRUE
     * @return
     */
    public static String supportBluetooth() {
        return "supportBluetooth: " + (BluetoothAdapter.getDefaultAdapter() != null);
    }

    /**
     * 判断是否平板设备
     * @return true:平板,false:手机
     */
    private static boolean isTabletDevice() {
        return (MyApplication.getContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static String[] getInfos() {
        return new String[] {
                getFingerprint(),
                getModel(),
                getSerial(),
                getManufaturer(),
                getBrand(),
                getDevice(),
                getProduct(),
                getHardware(),
                getCpuAbi(),
                getCpuAbi2(),
                resolveTelephoneIntent(),
                getNetworkOperatorName(),
                supportBluetooth(),
                getEmulatorOnSystem(),
                "isTableDevice: " + isTabletDevice()
        };
    }

}
