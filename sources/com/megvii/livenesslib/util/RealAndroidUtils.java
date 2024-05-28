package com.megvii.livenesslib.util;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.SPUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class RealAndroidUtils {
    public static final String TAG = "RealAndroidUtils";
    private static final String[] known_pipes = {"/data/youwave_id", "/dev/vboxguest", "/dev/vboxuser", "/mnt/prebundledapps/bluestacks.prop.orig", "/mnt/prebundledapps/propfiles/ics.bluestacks.prop.note", "/mnt/prebundledapps/propfiles/ics.bluestacks.prop.s2", "/mnt/prebundledapps/propfiles/ics.bluestacks.prop.s3", "/mnt/sdcard/bstfolder/InputMapper/com.bluestacks.appmart.cfg", "/mnt/sdcard/buildroid-gapps-ics-20120317-signed.tgz", "/mnt/sdcard/windows/InputMapper/com.bluestacks.appmart.cfg", "/proc/irq/9/vboxguest", "/sys/bus/pci/drivers/vboxguest", "/sys/bus/pci/drivers/vboxguest/0000:00:04.0", "/sys/bus/pci/drivers/vboxguest/bind", "/sys/bus/pci/drivers/vboxguest/module", "/sys/bus/pci/drivers/vboxguest/new_id", "/sys/bus/pci/drivers/vboxguest/remove_id", "/sys/bus/pci/drivers/vboxguest/uevent", "/sys/bus/pci/drivers/vboxguest/unbind", "/sys/bus/platform/drivers/qemu_pipe", "/sys/bus/platform/drivers/qemu_trace", "/sys/class/bdi/vboxsf-c", "/sys/class/misc/vboxguest", "/sys/class/misc/vboxuser", "/sys/devices/virtual/bdi/vboxsf-c", "/sys/devices/virtual/misc/vboxguest", "/sys/devices/virtual/misc/vboxguest/dev", "/sys/devices/virtual/misc/vboxguest/power", "/sys/devices/virtual/misc/vboxguest/subsystem", "/sys/devices/virtual/misc/vboxguest/uevent", "/sys/devices/virtual/misc/vboxuser", "/sys/devices/virtual/misc/vboxuser/dev", "/sys/devices/virtual/misc/vboxuser/power", "/sys/devices/virtual/misc/vboxuser/subsystem", "/sys/devices/virtual/misc/vboxuser/uevent", "/sys/module/vboxguest", "/sys/module/vboxguest/coresize", "/sys/module/vboxguest/drivers", "/sys/module/vboxguest/drivers/pci:vboxguest", "/sys/module/vboxguest/holders", "/sys/module/vboxguest/holders/vboxsf", "/sys/module/vboxguest/initsize", "/sys/module/vboxguest/initstate", "/sys/module/vboxguest/notes", "/sys/module/vboxguest/notes/.note.gnu.build-id", "/sys/module/vboxguest/parameters", "/sys/module/vboxguest/parameters/log", "/sys/module/vboxguest/parameters/log_dest", "/sys/module/vboxguest/parameters/log_flags", "/sys/module/vboxguest/refcnt", "/sys/module/vboxguest/sections", "/sys/module/vboxguest/sections/.altinstructions", "/sys/module/vboxguest/sections/.altinstr_replacement", "/sys/module/vboxguest/sections/.bss", "/sys/module/vboxguest/sections/.data", "/sys/module/vboxguest/sections/.devinit.data", "/sys/module/vboxguest/sections/.exit.text", "/sys/module/vboxguest/sections/.fixup", "/sys/module/vboxguest/sections/.gnu.linkonce.this_module", "/sys/module/vboxguest/sections/.init.text", "/sys/module/vboxguest/sections/.note.gnu.build-id", "/sys/module/vboxguest/sections/.rodata", "/sys/module/vboxguest/sections/.rodata.str1.1", "/sys/module/vboxguest/sections/.smp_locks", "/sys/module/vboxguest/sections/.strtab", "/sys/module/vboxguest/sections/.symtab", "/sys/module/vboxguest/sections/.text", "/sys/module/vboxguest/sections/__ex_table", "/sys/module/vboxguest/sections/__ksymtab", "/sys/module/vboxguest/sections/__ksymtab_strings", "/sys/module/vboxguest/sections/__param", "/sys/module/vboxguest/srcversion", "/sys/module/vboxguest/taint", "/sys/module/vboxguest/uevent", "/sys/module/vboxguest/version", "/sys/module/vboxsf", "/sys/module/vboxsf/coresize", "/sys/module/vboxsf/holders", "/sys/module/vboxsf/initsize", "/sys/module/vboxsf/initstate", "/sys/module/vboxsf/notes", "/sys/module/vboxsf/notes/.note.gnu.build-id", "/sys/module/vboxsf/refcnt", "/sys/module/vboxsf/sections", "/sys/module/vboxsf/sections/.bss", "/sys/module/vboxsf/sections/.data", "/sys/module/vboxsf/sections/.exit.text", "/sys/module/vboxsf/sections/.gnu.linkonce.this_module", "/sys/module/vboxsf/sections/.init.text", "/sys/module/vboxsf/sections/.note.gnu.build-id", "/sys/module/vboxsf/sections/.rodata", "/sys/module/vboxsf/sections/.rodata.str1.1", "/sys/module/vboxsf/sections/.smp_locks", "/sys/module/vboxsf/sections/.strtab", "/sys/module/vboxsf/sections/.symtab", "/sys/module/vboxsf/sections/.text", "/sys/module/vboxsf/sections/__bug_table", "/sys/module/vboxsf/sections/__param", "/sys/module/vboxsf/srcversion", "/sys/module/vboxsf/taint", "/sys/module/vboxsf/uevent", "/sys/module/vboxsf/version", "/sys/module/vboxvideo", "/sys/module/vboxvideo/coresize", "/sys/module/vboxvideo/holders", "/sys/module/vboxvideo/initsize", "/sys/module/vboxvideo/initstate", "/sys/module/vboxvideo/notes", "/sys/module/vboxvideo/notes/.note.gnu.build-id", "/sys/module/vboxvideo/refcnt", "/sys/module/vboxvideo/sections", "/sys/module/vboxvideo/sections/.data", "/sys/module/vboxvideo/sections/.exit.text", "/sys/module/vboxvideo/sections/.gnu.linkonce.this_module", "/sys/module/vboxvideo/sections/.init.text", "/sys/module/vboxvideo/sections/.note.gnu.build-id", "/sys/module/vboxvideo/sections/.rodata.str1.1", "/sys/module/vboxvideo/sections/.strtab", "/sys/module/vboxvideo/sections/.symtab", "/sys/module/vboxvideo/sections/.text", "/sys/module/vboxvideo/srcversion", "/sys/module/vboxvideo/taint", "/sys/module/vboxvideo/uevent", "/sys/module/vboxvideo/version", "/system/app/bluestacksHome.apk", "/system/bin/androVM-prop", "/system/bin/androVM-vbox-sf", "/system/bin/androVM_setprop", "/system/bin/get_androVM_host", "/system/bin/mount.vboxsf", "/system/etc/init.androVM.sh", "/system/etc/init.buildroid.sh", "/system/lib/hw/audio.primary.vbox86.so", "/system/lib/hw/camera.vbox86.so", "/system/lib/hw/gps.vbox86.so", "/system/lib/hw/gralloc.vbox86.so", "/system/lib/hw/sensors.vbox86.so", "/system/lib/modules/3.0.8-android-x86+/extra/vboxguest", "/system/lib/modules/3.0.8-android-x86+/extra/vboxguest/vboxguest.ko", "/system/lib/modules/3.0.8-android-x86+/extra/vboxsf", "/system/lib/modules/3.0.8-android-x86+/extra/vboxsf/vboxsf.ko", "/system/lib/vboxguest.ko", "/system/lib/vboxsf.ko", "/system/lib/vboxvideo.ko", "/system/usr/idc/androVM_Virtual_Input.idc", "/system/usr/keylayout/androVM_Virtual_Input.kl", "/system/xbin/mount.vboxsf", "/ueventd.android_x86.rc", "/ueventd.vbox86.rc", "/ueventd.goldfish.rc", "/fstab.vbox86", "/init.vbox86.rc", "/init.goldfish.rc", "/sys/module/goldfish_audio", "/sys/module/goldfish_sync", "/data/app/com.bluestacks.appmart-1.apk", "/data/app/com.bluestacks.BstCommandProcessor-1.apk", "/data/app/com.bluestacks.help-1.apk", "/data/app/com.bluestacks.home-1.apk", "/data/app/com.bluestacks.s2p-1.apk", "/data/app/com.bluestacks.searchapp-1.apk", "/data/bluestacks.prop", "/data/data/com.androVM.vmconfig", "/data/data/com.bluestacks.accelerometerui", "/data/data/com.bluestacks.appfinder", "/data/data/com.bluestacks.appmart", "/data/data/com.bluestacks.appsettings", "/data/data/com.bluestacks.BstCommandProcessor", "/data/data/com.bluestacks.bstfolder", "/data/data/com.bluestacks.help", "/data/data/com.bluestacks.home", "/data/data/com.bluestacks.s2p", "/data/data/com.bluestacks.searchapp", "/data/data/com.bluestacks.settings", "/data/data/com.bluestacks.setup", "/data/data/com.bluestacks.spotlight", "/data/data/com.microvirt.download", "/data/data/com.microvirt.guide", "/data/data/com.microvirt.installer", "/data/data/com.microvirt.launcher", "/data/data/com.microvirt.market", "/data/data/com.microvirt.memuime", "/data/data/com.microvirt.tools", "/data/data/com.mumu.launcher", "/data/data/com.mumu.store", "/data/data/com.netease.mumu.cloner"};
    private static final List<List<String>> pakgeList = new ArrayList<List<String>>() { // from class: com.megvii.livenesslib.util.RealAndroidUtils.1
        {
            add(new ArrayList<String>() { // from class: com.megvii.livenesslib.util.RealAndroidUtils.1.1
                {
                    add("com.android.mms");
                    add("com.android.messaging");
                    add("com.coloros.mms");
                    add("com.huawei.mms");
                    add("com.zui.mms");
                    add("com.asus.cnmessage");
                    add("com.bbk.mms");
                    add("com.hihonor.mms");
                    add("com.oneplus.mms");
                    add("cn.nubia.mms");
                    add("com.htc.sense.mms");
                    add("com.samsung.android.messaging");
                    add("com.google.android.apps.messaging");
                }
            });
            add(new ArrayList<String>() { // from class: com.megvii.livenesslib.util.RealAndroidUtils.1.2
                {
                    add("com.android.settings");
                    add("com.coloros.settings");
                    add("com.huawei.settings");
                    add("com.bbk.settings");
                    add("com.hihonor.settings");
                    add("com.oneplus.settings");
                }
            });
            add(new ArrayList<String>() { // from class: com.megvii.livenesslib.util.RealAndroidUtils.1.3
                {
                    add("com.android.calendar");
                    add("com.coloros.calendar");
                    add("com.huawei.calendar");
                    add("com.bbk.calendar");
                    add("com.hihonor.calendar");
                    add("com.htc.calendar");
                    add("cn.nubia.calendar.preset");
                    add("com.google.android.calendar");
                    add("com.samsung.android.calendar");
                    add("com.oneplus.calendar");
                    add("com.zui.browser");
                    add("com.asus.smartcalendar");
                }
            });
        }
    };
    public static String realMsg = "";

    private static boolean notHasBlueTooth() {
        try {
            realMsg = "蓝牙无效";
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null) {
                return true;
            }
            return TextUtils.isEmpty(defaultAdapter.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Boolean notHasLightSensorManager(Context context) {
        try {
            realMsg = "不存在光感";
            if (((SensorManager) context.getSystemService("sensor")).getDefaultSensor(5) == null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isHaveGeneric() {
        try {
            return Build.FINGERPRINT.startsWith("generic");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isHaveVBox() {
        try {
            return Build.FINGERPRINT.toLowerCase().contains("vbox");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isHaveTestKeys() {
        try {
            return Build.FINGERPRINT.toLowerCase().contains("test-keys");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isHaveGoogleSdk() {
        try {
            return Build.MODEL.contains("google_sdk");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isHaveEmulator() {
        try {
            return Build.MODEL.contains("Emulator");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isHaveX86() {
        try {
            return Build.MODEL.contains("Android SDK built for x86");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isHaveGenymotion() {
        try {
            return Build.MANUFACTURER.contains("Genymotion");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isHaveGenerics() {
        try {
            if (Build.BRAND.startsWith("generic")) {
                return Build.DEVICE.startsWith("generic");
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isHaveGooleSdks() {
        try {
            return "google_sdk".equals(Build.PRODUCT);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean checkIsNotRealPhone() {
        try {
            realMsg = "CPU判断不符合要求";
            String readCpuInfo = readCpuInfo();
            if (readCpuInfo.contains("intel")) {
                return true;
            }
            return readCpuInfo.contains("amd");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String readCpuInfo() {
        try {
            Process start = new ProcessBuilder("/system/bin/cat", "/proc/cpuinfo").start();
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream(), "utf-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString().toLowerCase();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static boolean checkPipes() {
        try {
            realMsg = "存在模拟器特有文件";
            for (int i = 0; i < known_pipes.length; i++) {
                if (new File(known_pipes[i]).exists()) {
                    Log.v("Result:", "Find pipes!");
                    return true;
                }
            }
            Log.i("Result:", "Not Find pipes!");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x007b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007c A[Catch: Exception -> 0x0081, TRY_LEAVE, TryCatch #0 {Exception -> 0x0081, blocks: (B:3:0x0001, B:5:0x0011, B:7:0x0020, B:9:0x002f, B:11:0x0039, B:13:0x0043, B:15:0x004d, B:17:0x0057, B:19:0x0061, B:21:0x006b, B:28:0x007c), top: B:33:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean isRealEmulator(android.content.Context r4) {
        /*
            r0 = 0
            java.lang.String r1 = "设备信息特征不符合"
            com.megvii.livenesslib.util.RealAndroidUtils.realMsg = r1     // Catch: java.lang.Exception -> L81
            java.lang.String r1 = android.os.Build.FINGERPRINT     // Catch: java.lang.Exception -> L81
            java.lang.String r2 = "generic"
            boolean r1 = r1.startsWith(r2)     // Catch: java.lang.Exception -> L81
            r2 = 1
            if (r1 != 0) goto L78
            java.lang.String r1 = android.os.Build.FINGERPRINT     // Catch: java.lang.Exception -> L81
            java.lang.String r1 = r1.toLowerCase()     // Catch: java.lang.Exception -> L81
            java.lang.String r3 = "vbox"
            boolean r1 = r1.contains(r3)     // Catch: java.lang.Exception -> L81
            if (r1 != 0) goto L78
            java.lang.String r1 = android.os.Build.FINGERPRINT     // Catch: java.lang.Exception -> L81
            java.lang.String r1 = r1.toLowerCase()     // Catch: java.lang.Exception -> L81
            java.lang.String r3 = "test-keys"
            boolean r1 = r1.contains(r3)     // Catch: java.lang.Exception -> L81
            if (r1 != 0) goto L78
            java.lang.String r1 = android.os.Build.MODEL     // Catch: java.lang.Exception -> L81
            java.lang.String r3 = "google_sdk"
            boolean r1 = r1.contains(r3)     // Catch: java.lang.Exception -> L81
            if (r1 != 0) goto L78
            java.lang.String r1 = android.os.Build.MODEL     // Catch: java.lang.Exception -> L81
            java.lang.String r3 = "Emulator"
            boolean r1 = r1.contains(r3)     // Catch: java.lang.Exception -> L81
            if (r1 != 0) goto L78
            java.lang.String r1 = android.os.Build.MODEL     // Catch: java.lang.Exception -> L81
            java.lang.String r3 = "Android SDK built for x86"
            boolean r1 = r1.contains(r3)     // Catch: java.lang.Exception -> L81
            if (r1 != 0) goto L78
            java.lang.String r1 = android.os.Build.MANUFACTURER     // Catch: java.lang.Exception -> L81
            java.lang.String r3 = "Genymotion"
            boolean r1 = r1.contains(r3)     // Catch: java.lang.Exception -> L81
            if (r1 != 0) goto L78
            java.lang.String r1 = android.os.Build.BRAND     // Catch: java.lang.Exception -> L81
            java.lang.String r3 = "generic"
            boolean r1 = r1.startsWith(r3)     // Catch: java.lang.Exception -> L81
            if (r1 == 0) goto L6b
            java.lang.String r1 = android.os.Build.DEVICE     // Catch: java.lang.Exception -> L81
            java.lang.String r3 = "generic"
            boolean r1 = r1.startsWith(r3)     // Catch: java.lang.Exception -> L81
            if (r1 != 0) goto L78
        L6b:
            java.lang.String r1 = "google_sdk"
            java.lang.String r3 = android.os.Build.PRODUCT     // Catch: java.lang.Exception -> L81
            boolean r1 = r1.equals(r3)     // Catch: java.lang.Exception -> L81
            if (r1 == 0) goto L76
            goto L78
        L76:
            r1 = r0
            goto L79
        L78:
            r1 = r2
        L79:
            if (r1 == 0) goto L7c
            return r2
        L7c:
            boolean r4 = checkDial(r4)     // Catch: java.lang.Exception -> L81
            return r4
        L81:
            r4 = move-exception
            r4.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.livenesslib.util.RealAndroidUtils.isRealEmulator(android.content.Context):boolean");
    }

    private static boolean checkDial(Context context) {
        String str;
        String networkOperatorName;
        try {
            realMsg = "设备不可打电话判定为模拟器";
            str = "";
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null && (networkOperatorName = telephonyManager.getNetworkOperatorName()) != null) {
                str = networkOperatorName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (str.toLowerCase().equals("android")) {
            return true;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel:123456"));
        intent.setAction("android.intent.action.DIAL");
        return intent.resolveActivity(context.getPackageManager()) == null;
    }

    public static boolean isEmulator(Context context) {
        String string;
        try {
            string = SPUtils.getInstance().getString("REALANDROID_DATA");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(string)) {
            return TextUtils.equals(string, "true");
        }
        if (isMobile()) {
            SPUtils.getInstance().put("REALANDROID_DATA", "false");
            return false;
        }
        if (isRealEmulator(context) || notHasBlueTooth() || notHasLightSensorManager(context).booleanValue() || checkIsNotRealPhone() || checkPipes()) {
            SPUtils.getInstance().put("REALANDROID_DATA", "true");
            return true;
        }
        SPUtils.getInstance().put("REALANDROID_DATA", "false");
        return false;
    }

    public static String getSimulatorMark(Context context) {
        String string;
        String str = "";
        try {
            string = SPUtils.getInstance().getString("REALANDROID_RESULT", "");
        } catch (Exception e) {
            Log.e("RealAndroidUtils", "getSimulatorMark: ", e);
        }
        if (TextUtils.isEmpty(string)) {
            int i = isHaveEmulator() ? 1 : 0;
            if (isHaveGeneric()) {
                i++;
            }
            if (isHaveGenerics()) {
                i++;
            }
            if (isHaveGenymotion()) {
                i++;
            }
            if (isHaveGoogleSdk()) {
                i++;
            }
            if (isHaveGooleSdks()) {
                i++;
            }
            if (isHaveTestKeys()) {
                i++;
            }
            if (isHaveVBox()) {
                i++;
            }
            if (isHaveX86()) {
                i++;
            }
            if (notHasBlueTooth()) {
                i++;
            }
            if (notHasLightSensorManager(context).booleanValue()) {
                i++;
            }
            if (checkIsNotRealPhone()) {
                i++;
            }
            if (checkPipes()) {
                i++;
            }
            if (checkDial(context)) {
                i++;
            }
            if (isNotHaveSysApp(context)) {
                i++;
            }
            if (RootUtil.isDeviceRooted()) {
                i++;
            }
            str = new DecimalFormat("0").format((1.0d - (i / 16.0d)) * 100.0d);
            SPUtils.getInstance().put("REALANDROID_RESULT", str);
            return TextUtils.isEmpty(str) ? "100" : str;
        }
        return string;
    }

    private static boolean isMobile() {
        try {
            if (AndroidRomUtil.isLg() || AndroidRomUtil.isSamsung() || AndroidRomUtil.isMeizu() || AndroidRomUtil.isHuawei() || AndroidRomUtil.isVivo() || AndroidRomUtil.is360() || AndroidRomUtil.isCoolpad() || AndroidRomUtil.isGionee() || AndroidRomUtil.isGoogle() || AndroidRomUtil.isHtc() || AndroidRomUtil.isLeeco() || AndroidRomUtil.isLenovo() || AndroidRomUtil.isMotorola() || AndroidRomUtil.isNubia() || AndroidRomUtil.isOneplus() || AndroidRomUtil.isOppo() || AndroidRomUtil.isSmartisan() || AndroidRomUtil.isSony() || AndroidRomUtil.isXiaomi()) {
                return true;
            }
            return AndroidRomUtil.isZte();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isNotHaveSysApp(Context context) {
        boolean z;
        boolean z2;
        boolean z3;
        try {
            z = false;
            for (List<String> list : pakgeList) {
                try {
                } catch (Exception e) {
                    e = e;
                }
                try {
                    Iterator<String> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z2 = true;
                            z3 = false;
                            break;
                        }
                        try {
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (context.getPackageManager().getPackageInfo(it.next(), 0) != null) {
                            z3 = true;
                            z2 = false;
                            break;
                        }
                    }
                    if (!z3) {
                        return z2;
                    }
                    z = z2;
                } catch (Exception e3) {
                    e = e3;
                    z = true;
                    e.printStackTrace();
                    return z;
                }
            }
            return z;
        } catch (Exception e4) {
            e = e4;
            z = false;
        }
    }
}
