package com.chinaunicon.jtwifilib.jtcommon.ping.manage;

import android.text.TextUtils;
import android.util.Log;
import com.chinaunicon.jtwifilib.jtcommon.ping.bean.PingItemBean;
import com.chinaunicon.jtwifilib.jtcommon.ping.bean.PingResBean;
import com.chinaunicon.jtwifilib.jtcommon.ping.interfaces.PingInterf;
import com.chinaunicon.jtwifilib.jtcommon.util.FloatUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PingManage implements PingInterf {
    private static PingManage ping;
    public static String pingContent;
    private boolean isStop = false;

    public static PingManage getIntance() {
        if (ping == null) {
            ping = new PingManage();
        }
        return ping;
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.ping.interfaces.PingInterf
    public PingResBean pingForResult(String str, int i) throws Exception {
        pingContent = "";
        String ping2 = ping(str, i);
        if (ping2 != null) {
            return parsePingResult(ping2);
        }
        return null;
    }

    public String ping(String str, int i) throws Exception {
        this.isStop = false;
        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec("ping -c " + i + " -i0.2 -w 10 " + str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        do {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuffer.append(readLine);
            } else {
                String stringBuffer2 = stringBuffer.toString();
                pingContent = stringBuffer2;
                int waitFor = exec.waitFor();
                Log.i("Ping", "pingResult=" + stringBuffer2);
                Log.i("Ping", "status=" + exec.waitFor());
                if (waitFor == 0) {
                    return stringBuffer2;
                }
                return null;
            }
        } while (!this.isStop);
        throw new Exception("ping停止");
    }

    public void setStop(boolean z) {
        this.isStop = z;
    }

    private PingResBean parsePingResult(String str) throws Exception {
        PingResBean pingResBean = new PingResBean();
        String substring = str.substring(str.indexOf("data") + 5);
        String substring2 = substring.substring(0, substring.indexOf("-"));
        String substring3 = substring.substring(substring.lastIndexOf("-") + 1);
        if (str == null || str == "") {
            return null;
        }
        pingResBean.setIp(getIP(str));
        pingResBean.setItems(getPingItems(substring2));
        parseEndStrToBean(pingResBean, substring3);
        return pingResBean;
    }

    private String getIP(String str) throws Exception {
        int indexOf;
        if (str == null || str == "") {
            return "";
        }
        if (str.contains("From") || str.contains("from")) {
            String substring = str.substring(str.indexOf("From") + 5);
            if (substring.contains("(")) {
                return substring.substring(substring.indexOf("(") + 1, substring.indexOf(")"));
            }
            String substring2 = substring.substring(0, substring.indexOf("\n"));
            if (substring2.contains(":")) {
                indexOf = substring2.indexOf(":");
            } else {
                indexOf = substring2.indexOf(" ");
            }
            return substring2.substring(0, indexOf);
        }
        return str.substring(str.indexOf("(") + 1, str.indexOf(")"));
    }

    private List<PingItemBean> getPingItems(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split("ms")) {
            PingItemBean pingItemBean = new PingItemBean();
            String[] split = str2.split(": ");
            if (split.length == 2) {
                String[] split2 = split[0].split(" ");
                String[] split3 = split[1].split(" ");
                pingItemBean.setBytes(split2[0]);
                pingItemBean.setSeq(split3[0].split("=")[1]);
                pingItemBean.setTtl(split3[1].split("=")[1]);
                pingItemBean.setTime(split3[2].split("=")[1]);
            }
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
                pingResBean.setLossPacket(TextUtils.isEmpty(split2[2].split(" ")[0]) ? "" : split2[2].split(" ")[0].replace("%", ""));
                pingResBean.setTime(split2[3].split(" ")[1].split("ms")[0]);
            }
            if (split3.length == 7) {
                pingResBean.setMinTime(FloatUtil.changeFloatTwo(Float.valueOf(split3[3].split(" ")[2]).floatValue()) + "");
                pingResBean.setAvgTime(FloatUtil.changeFloatTwo(Float.valueOf(split3[4]).floatValue()) + "");
                pingResBean.setMaxTime(FloatUtil.changeFloatTwo(Float.valueOf(split3[5]).floatValue()) + "");
                pingResBean.setMdev(FloatUtil.changeFloatTwo(Float.valueOf(split3[6].split(" ")[0]).floatValue()) + "");
            }
        }
    }
}
