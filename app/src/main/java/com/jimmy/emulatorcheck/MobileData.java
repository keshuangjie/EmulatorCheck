package com.jimmy.emulatorcheck;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * mobile properties
 *
 * Created by Jimmy on 2017/9/5.
 */
public class MobileData {

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
        return "canResolveTelephoneIntent: " + EmulatorCheckTool.canResolveTelephoneIntent();
    }

    public static String getNetworkOperatorName() {
        return "getNetworkOperatorName: " + ((TelephonyManager) MyApplication.getContext().getSystemService(Context
                .TELEPHONY_SERVICE))
                .getNetworkOperatorName();
    }

    public static String getEmulatorOnSystem() {
        return  "Build.IS_EMULATOR: " + EmulatorCheckTool.isEmulatorOnSystem();
    }


    public static String getCpuAbi() {
        return "Build.CPU_ABI: " + Build.CPU_ABI;
    }

    public static String getCpuAbi2() {
        return "Build.CPU_ABI2: " + Build.CPU_ABI2;
    }

    public static String getCpuAbis() {
        String abis = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            for (String abi : Build.SUPPORTED_ABIS) {
                abis += abi;
            }
        }
        return "Build.CPU_ABI2: " + abis;
    }

    /**
     * 夜神模拟器显示TRUE
     * @return
     */
    public static String supportBluetooth() {
        return "supportBluetooth: " + EmulatorCheckTool.supportBluetooth();
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
                getCpuAbis(),
                resolveTelephoneIntent(),
                getNetworkOperatorName(),
                supportBluetooth(),
                getEmulatorOnSystem(),
                "isTableDevice: " + EmulatorCheckTool.isTabletDevice()
        };
    }

}
