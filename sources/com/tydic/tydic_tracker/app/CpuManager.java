package com.tydic.tydic_tracker.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class CpuManager {
    public static String getMaxCpuFreq() {
        byte[] bArr;
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq").start().getInputStream();
            while (inputStream.read(new byte[24]) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            str = "N/A";
        }
        return str.trim();
    }

    public static String getMinCpuFreq() {
        byte[] bArr;
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq").start().getInputStream();
            while (inputStream.read(new byte[24]) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            str = "N/A";
        }
        return str.trim();
    }

    public static String getCurCpuFreq() {
        try {
            return new BufferedReader(new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq")).readLine().trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "N/A";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "N/A";
        }
    }

    public static String getCpuPercent() {
        float f;
        try {
            String curCpuFreq = getCurCpuFreq();
            String maxCpuFreq = getMaxCpuFreq();
            String minCpuFreq = getMinCpuFreq();
            f = (Integer.parseInt(curCpuFreq) - Integer.parseInt(minCpuFreq)) / (Integer.parseInt(maxCpuFreq) - Integer.parseInt(minCpuFreq));
        } catch (Exception e) {
            e.printStackTrace();
            f = 0.0f;
        }
        return String.valueOf(f);
    }

    public static String getCpuName() {
        try {
            String[] split = new BufferedReader(new FileReader("/proc/cpuinfo")).readLine().split(":\\s+", 2);
            for (int i = 0; i < split.length; i++) {
            }
            return split[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
