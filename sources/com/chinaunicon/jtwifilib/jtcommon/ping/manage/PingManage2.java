package com.chinaunicon.jtwifilib.jtcommon.ping.manage;

import android.text.TextUtils;
import android.util.Log;
import com.chinaunicon.jtwifilib.jtcommon.ping.bean.PingItemBean;
import com.chinaunicon.jtwifilib.jtcommon.ping.bean.PingResBean;
import com.chinaunicon.jtwifilib.jtcommon.ping.interfaces.PingInterf;
import com.chinaunicon.jtwifilib.jtcommon.util.FloatUtil;
import com.chinaunicon.jtwifilib.jtcommon.util.IpUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PingManage2 implements PingInterf {
    public static final int TIME_OUT = 6;
    public static String pingContent;
    private static PingManage2 pingManage;
    private int compensateTimes = 0;

    public static PingManage2 getIntance() {
        if (pingManage == null) {
            pingManage = new PingManage2();
        }
        return pingManage;
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.ping.interfaces.PingInterf
    public PingResBean pingForResult(String str, int i) throws Exception {
        pingContent = "";
        String ping = ping(str, i, 6);
        if (TextUtils.isEmpty(ping) || "-1000".equals(ping)) {
            return null;
        }
        return parsePingResult(ping);
    }

    public String ping(String str, int i, int i2) throws Exception {
        if (IpUtil.resolveHost(str, 80, i2 * 1000)) {
            Process exec = Runtime.getRuntime().exec("ping -c " + i + " -w " + i2 + " " + str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
            }
            String stringBuffer2 = stringBuffer.toString();
            Log.i("ping", "ping: pingResult=" + stringBuffer2);
            pingContent = stringBuffer2;
            int waitFor = exec.waitFor();
            Log.i("ping", "ping: status=" + waitFor);
            this.compensateTimes = 0;
            if (waitFor == 0 || waitFor == 1) {
                return stringBuffer2;
            }
            return null;
        }
        this.compensateTimes++;
        if (this.compensateTimes <= 2) {
            Thread.sleep(1500L);
            ping(str, i, i2);
            return "-1000";
        }
        this.compensateTimes = 0;
        return "-1000";
    }

    private PingResBean parsePingResult(String str) throws Exception {
        Log.i("ping", "parsePingResult: pingResult=" + str);
        PingResBean pingResBean = new PingResBean();
        String substring = str.substring(str.indexOf("data") + 5);
        String substring2 = substring.substring(0, substring.indexOf("---"));
        String substring3 = substring.substring(substring.lastIndexOf("-") + 1);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String parseIp = parseIp(str);
        pingResBean.setIp(parseIp);
        if (substring2.length() > 10) {
            pingResBean.setItems(parseItems(substring2, parseIp));
        } else {
            pingResBean.setItems(null);
        }
        parseEndStrToBean(pingResBean, substring3);
        return pingResBean;
    }

    public static String parseIp(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.contains("(")) {
            String substring = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
            return IpUtil.isIpAdress(substring) ? substring : "";
        } else if (str.contains("from") || str.contains("From")) {
            String str2 = null;
            if (str.contains("from")) {
                str2 = str.substring(str.indexOf("from") + 5);
            } else if (str.contains("From")) {
                str2 = str.substring(str.indexOf("From") + 5);
            }
            if (TextUtils.isEmpty(str2)) {
                return "";
            }
            if (str2.contains("(")) {
                String substring2 = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
                return IpUtil.isIpAdress(substring2) ? substring2 : "";
            }
            String str3 = str2.split(":")[0];
            return IpUtil.isIpAdress(str3) ? str3 : "";
        } else {
            return "";
        }
    }

    private List<PingItemBean> parseItems(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        for (String str3 : str.split(" ms")) {
            PingItemBean pingItemBean = new PingItemBean();
            pingItemBean.setIp(str2);
            String[] split = str3.split(": ");
            pingItemBean.setBytes(split[0].split(" ")[0]);
            String[] split2 = split[1].split(" ");
            pingItemBean.setSeq(split2[0].split("=")[1]);
            pingItemBean.setTtl(split2[1].split("=")[1]);
            pingItemBean.setTime(split2[2].split("=")[1]);
            arrayList.add(pingItemBean);
        }
        return arrayList;
    }

    private void parseEndStrToBean(PingResBean pingResBean, String str) {
        String[] split = str.split("ms");
        if (split.length == 2) {
            String[] split2 = split[0].split(", ");
            String[] split3 = split[1].split("/");
            if (split2.length == 4) {
                pingResBean.setTransmittedNum(split2[0].split(" ")[0]);
                pingResBean.setReceivedNum(split2[1].split(" ")[0]);
                pingResBean.setLossPacket(split2[2].split(" ")[0]);
                pingResBean.setTime(split2[3].split(" ")[1]);
            }
            if (split3.length == 7) {
                pingResBean.setMinTime(FloatUtil.changeFloatTwo(Float.valueOf(split3[3].split(" ")[2]).floatValue()) + "");
                pingResBean.setAvgTime(FloatUtil.changeFloatTwo(Float.valueOf(split3[4]).floatValue()) + "");
                pingResBean.setMaxTime(FloatUtil.changeFloatTwo(Float.valueOf(split3[5]).floatValue()) + "");
                pingResBean.setMdev(FloatUtil.changeFloatTwo(Float.valueOf(split3[6].split(" ")[0]).floatValue()) + "");
            }
        } else if (split.length == 1) {
            String[] split4 = split[0].split(", ");
            if (split4.length == 4) {
                pingResBean.setTransmittedNum(split4[0].split(" ")[0]);
                pingResBean.setReceivedNum(split4[1].split(" ")[0]);
                pingResBean.setLossPacket(split4[2].split(" ")[0]);
                pingResBean.setTime(split4[3].split(" ")[1]);
            }
        }
    }
}
