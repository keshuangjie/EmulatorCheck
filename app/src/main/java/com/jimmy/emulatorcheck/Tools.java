package com.jimmy.emulatorcheck;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * Created by Jimmy on 2017/9/5.
 */
public class Tools {

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
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

    public static String canResolveTelephoneIntent() {
        String url = "tel:" + "123456";
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));
        intent.setAction(Intent.ACTION_DIAL);
        // 是否可以处理跳转到拨号的 Intent
        boolean canResolveIntent = intent.resolveActivity(MyApplication.getContext().getPackageManager()) != null;
        return "canResolveTelephoneIntent: " + canResolveIntent;
    }

    public static String getNetworkOperatorName() {
        return "getNetworkOperatorName: " + ((TelephonyManager) MyApplication.getContext().getSystemService(Context
                .TELEPHONY_SERVICE))
                .getNetworkOperatorName();
    }

    public static boolean isEmulatorOnSystem() {
        return  SystemProperties.get("ro.kernel.qemu").equals("1");
    }

    /**
     * 夜神模拟器显示TRUE
     * @return
     */
    public static String supportBluetooth() {
        return "supportBluetooth: " + (BluetoothAdapter.getDefaultAdapter() != null);
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
                canResolveTelephoneIntent(),
                getNetworkOperatorName(),
                supportBluetooth()
        };
    }

}
