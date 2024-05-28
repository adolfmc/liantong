package com.baidu.rtc.logreport;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.SystemClock;
import com.webrtc.Logging;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CpuMonitor {
    private static final int CPU_STAT_LOG_PERIOD_MS = 6000;
    private static final int CPU_STAT_SAMPLE_PERIOD_MS = 2000;
    private static final int MOVING_AVERAGE_SAMPLES = 5;
    private static final String TAG = "CpuMonitor";
    private int actualCpusPresent;
    private final Context appContext;
    private long[] cpuFreqMax;
    private boolean cpuOveruse;
    private int cpusPresent;
    private double[] curFreqScales;
    private String[] curPath;
    private ScheduledExecutorService executor;
    private final MovingAverage frequencyScale;
    private boolean initialized;
    private ProcStat lastProcStat;
    private long lastStatLogTimeMs;
    private String[] maxPath;
    private final MovingAverage systemCpuUsage;
    private final MovingAverage totalCpuUsage;
    private final MovingAverage userCpuUsage;

    private int doubleToPercent(double d) {
        return (int) ((d * 100.0d) + 0.5d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class ProcStat {
        final long idleTime;
        final long systemTime;
        final long userTime;

        ProcStat(long j, long j2, long j3) {
            this.userTime = j;
            this.systemTime = j2;
            this.idleTime = j3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class MovingAverage {
        private double[] circBuffer;
        private int circBufferIndex;
        private double currentValue;
        private final int size;
        private double sum;

        public MovingAverage(int i) {
            if (i <= 0) {
                throw new AssertionError("Size value in MovingAverage ctor should be positive.");
            }
            this.size = i;
            this.circBuffer = new double[i];
        }

        public void reset() {
            Arrays.fill(this.circBuffer, 0.0d);
            this.circBufferIndex = 0;
            this.sum = 0.0d;
            this.currentValue = 0.0d;
        }

        public void addValue(double d) {
            double d2 = this.sum;
            double[] dArr = this.circBuffer;
            int i = this.circBufferIndex;
            this.sum = d2 - dArr[i];
            this.circBufferIndex = i + 1;
            dArr[i] = d;
            this.currentValue = d;
            this.sum += d;
            if (this.circBufferIndex >= this.size) {
                this.circBufferIndex = 0;
            }
        }

        public double getCurrent() {
            return this.currentValue;
        }

        public double getAverage() {
            return this.sum / this.size;
        }
    }

    public CpuMonitor(Context context) {
        Logging.m5305d("CpuMonitor", "CpuMonitor ctor.");
        this.appContext = context.getApplicationContext();
        this.userCpuUsage = new MovingAverage(5);
        this.systemCpuUsage = new MovingAverage(5);
        this.totalCpuUsage = new MovingAverage(5);
        this.frequencyScale = new MovingAverage(5);
        this.lastStatLogTimeMs = SystemClock.elapsedRealtime();
        scheduleCpuUtilizationTask();
    }

    public void pause() {
        if (this.executor != null) {
            Logging.m5305d("CpuMonitor", "pause");
            this.executor.shutdownNow();
            this.executor = null;
        }
    }

    public void resume() {
        Logging.m5305d("CpuMonitor", "resume");
        resetStat();
        scheduleCpuUtilizationTask();
    }

    public synchronized void reset() {
        if (this.executor != null) {
            Logging.m5305d("CpuMonitor", "reset");
            resetStat();
            this.cpuOveruse = false;
        }
    }

    public synchronized int getCpuUsageCurrent() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT <= 24) {
            return doubleToPercent(this.userCpuUsage.getCurrent() + this.systemCpuUsage.getCurrent());
        }
        return -1;
    }

    public synchronized int getCpuUsageAverage() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT <= 24) {
            return doubleToPercent(this.userCpuUsage.getAverage() + this.systemCpuUsage.getAverage());
        }
        return -1;
    }

    public synchronized int getFrequencyScaleAverage() {
        return doubleToPercent(this.frequencyScale.getAverage());
    }

    private void scheduleCpuUtilizationTask() {
        ScheduledExecutorService scheduledExecutorService = this.executor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.executor = null;
        }
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.executor.scheduleAtFixedRate(new Runnable() { // from class: com.baidu.rtc.logreport.CpuMonitor.1
            @Override // java.lang.Runnable
            public void run() {
                CpuMonitor.this.cpuUtilizationTask();
            }
        }, 0L, 2000L, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cpuUtilizationTask() {
        if (!sampleCpuUtilization() || SystemClock.elapsedRealtime() - this.lastStatLogTimeMs < 6000) {
            return;
        }
        this.lastStatLogTimeMs = SystemClock.elapsedRealtime();
        Logging.m5305d("CpuMonitor", getStatString());
    }

    private void init() {
        try {
            FileReader fileReader = new FileReader("/sys/devices/system/cpu/present");
            try {
                Scanner useDelimiter = new Scanner(new BufferedReader(fileReader)).useDelimiter("[-\n]");
                useDelimiter.nextInt();
                this.cpusPresent = useDelimiter.nextInt() + 1;
                useDelimiter.close();
            } catch (Exception unused) {
                Logging.m5304e("CpuMonitor", "Cannot do CPU stats due to /sys/devices/system/cpu/present parsing problem");
            }
            fileReader.close();
        } catch (FileNotFoundException unused2) {
            Logging.m5304e("CpuMonitor", "Cannot do CPU stats since /sys/devices/system/cpu/present is missing");
        } catch (IOException unused3) {
            Logging.m5304e("CpuMonitor", "Error closing file");
        }
        int i = this.cpusPresent;
        this.cpuFreqMax = new long[i];
        this.maxPath = new String[i];
        this.curPath = new String[i];
        this.curFreqScales = new double[i];
        for (int i2 = 0; i2 < this.cpusPresent; i2++) {
            this.cpuFreqMax[i2] = 0;
            this.curFreqScales[i2] = 0.0d;
            String[] strArr = this.maxPath;
            strArr[i2] = "/sys/devices/system/cpu/cpu" + i2 + "/cpufreq/cpuinfo_max_freq";
            String[] strArr2 = this.curPath;
            strArr2[i2] = "/sys/devices/system/cpu/cpu" + i2 + "/cpufreq/scaling_cur_freq";
        }
        this.lastProcStat = new ProcStat(0L, 0L, 0L);
        resetStat();
        this.initialized = true;
    }

    private synchronized void resetStat() {
        this.userCpuUsage.reset();
        this.systemCpuUsage.reset();
        this.totalCpuUsage.reset();
        this.frequencyScale.reset();
        this.lastStatLogTimeMs = SystemClock.elapsedRealtime();
    }

    private int getBatteryLevel() {
        Intent registerReceiver = this.appContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = registerReceiver.getIntExtra("scale", 100);
        if (intExtra > 0) {
            return (int) ((registerReceiver.getIntExtra("level", 0) * 100.0f) / intExtra);
        }
        return 0;
    }

    private synchronized boolean sampleCpuUtilization() {
        if (!this.initialized) {
            init();
        }
        if (this.cpusPresent == 0) {
            return false;
        }
        this.actualCpusPresent = 0;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        for (int i = 0; i < this.cpusPresent; i++) {
            this.curFreqScales[i] = 0.0d;
            if (this.cpuFreqMax[i] == 0) {
                long readFreqFromFile = readFreqFromFile(this.maxPath[i]);
                if (readFreqFromFile > 0) {
                    Logging.m5305d("CpuMonitor", "Core " + i + ". Max frequency: " + readFreqFromFile);
                    this.cpuFreqMax[i] = readFreqFromFile;
                    this.maxPath[i] = null;
                    j3 = readFreqFromFile;
                }
            } else {
                j3 = this.cpuFreqMax[i];
            }
            long readFreqFromFile2 = readFreqFromFile(this.curPath[i]);
            int i2 = (readFreqFromFile2 > 0L ? 1 : (readFreqFromFile2 == 0L ? 0 : -1));
            if (i2 != 0 || j3 != 0) {
                if (i2 > 0) {
                    this.actualCpusPresent++;
                }
                j += readFreqFromFile2;
                j2 += j3;
                if (j3 > 0) {
                    this.curFreqScales[i] = readFreqFromFile2 / j3;
                }
            }
        }
        if (j != 0 && j2 != 0) {
            double d = j / j2;
            if (this.frequencyScale.getCurrent() > 0.0d) {
                d = 0.5d * (this.frequencyScale.getCurrent() + d);
            }
            this.frequencyScale.addValue(d);
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT <= 24) {
                ProcStat readProcStat = readProcStat();
                if (readProcStat == null) {
                    return false;
                }
                long j4 = readProcStat.userTime - this.lastProcStat.userTime;
                long j5 = readProcStat.systemTime - this.lastProcStat.systemTime;
                long j6 = j4 + j5 + (readProcStat.idleTime - this.lastProcStat.idleTime);
                if (d != 0.0d && j6 != 0) {
                    double d2 = j6;
                    double d3 = j4 / d2;
                    this.userCpuUsage.addValue(d3);
                    double d4 = j5 / d2;
                    this.systemCpuUsage.addValue(d4);
                    this.totalCpuUsage.addValue((d3 + d4) * d);
                    this.lastProcStat = readProcStat;
                    return true;
                }
                return false;
            }
            return true;
        }
        Logging.m5304e("CpuMonitor", "Could not read max or current frequency for any CPU");
        return false;
    }

    private synchronized String getStatString() {
        StringBuilder sb;
        sb = new StringBuilder();
        sb.append("CPU User: ");
        sb.append(doubleToPercent(this.userCpuUsage.getCurrent()));
        sb.append("/");
        sb.append(doubleToPercent(this.userCpuUsage.getAverage()));
        sb.append(". System: ");
        sb.append(doubleToPercent(this.systemCpuUsage.getCurrent()));
        sb.append("/");
        sb.append(doubleToPercent(this.systemCpuUsage.getAverage()));
        sb.append(". Freq: ");
        sb.append(doubleToPercent(this.frequencyScale.getCurrent()));
        sb.append("/");
        sb.append(doubleToPercent(this.frequencyScale.getAverage()));
        sb.append(". Total usage: ");
        sb.append(doubleToPercent(this.totalCpuUsage.getCurrent()));
        sb.append("/");
        sb.append(doubleToPercent(this.totalCpuUsage.getAverage()));
        sb.append(". Cores: ");
        sb.append(this.actualCpusPresent);
        sb.append("( ");
        for (int i = 0; i < this.cpusPresent; i++) {
            sb.append(doubleToPercent(this.curFreqScales[i]));
            sb.append(" ");
        }
        sb.append("). Battery: ");
        sb.append(getBatteryLevel());
        if (this.cpuOveruse) {
            sb.append(". Overuse.");
        }
        return sb.toString();
    }

    private long readFreqFromFile(String str) {
        long j = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            j = parseLong(bufferedReader.readLine());
            bufferedReader.close();
        } catch (FileNotFoundException | IOException unused) {
        }
        return j;
    }

    private static long parseLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            Logging.m5303e("CpuMonitor", "parseLong error.", e);
            return 0L;
        }
    }

    private ProcStat readProcStat() {
        long j;
        long j2;
        long j3;
        long j4;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/stat"));
            try {
                String[] split = bufferedReader.readLine().split("\\s+");
                int length = split.length;
                long j5 = 0;
                if (length >= 5) {
                    j5 = parseLong(split[1]) + parseLong(split[2]);
                    j = parseLong(split[3]);
                    j2 = parseLong(split[4]);
                } else {
                    j = 0;
                    j2 = 0;
                }
                if (length >= 8) {
                    j3 = j5 + parseLong(split[5]);
                    j4 = j + parseLong(split[6]) + parseLong(split[7]);
                } else {
                    j3 = j5;
                    j4 = j;
                }
                bufferedReader.close();
                return new ProcStat(j3, j4, j2);
            } catch (Exception e) {
                Logging.m5303e("CpuMonitor", "Problems parsing /proc/stat", e);
                bufferedReader.close();
                return null;
            }
        } catch (FileNotFoundException e2) {
            Logging.m5303e("CpuMonitor", "Cannot open /proc/stat for reading", e2);
            return null;
        } catch (IOException e3) {
            Logging.m5303e("CpuMonitor", "Problems reading /proc/stat", e3);
            return null;
        }
    }
}
