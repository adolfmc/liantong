package com.baidu.location.p137b;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Base64;
import com.baidu.location.BDLocation;
import com.baidu.location.p140e.C2735k;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2631c {

    /* renamed from: a */
    private SharedPreferences f5176a = null;

    /* renamed from: b */
    private boolean f5177b = false;

    /* renamed from: c */
    private Deque<String> f5178c = new LinkedList();

    /* renamed from: d */
    private Deque<String> f5179d = new LinkedList();

    /* renamed from: e */
    private Deque<String> f5180e = new LinkedList();

    /* renamed from: f */
    private int f5181f = 5;

    /* renamed from: g */
    private int f5182g = 5;

    /* renamed from: h */
    private int f5183h = 1;

    /* renamed from: i */
    private int f5184i = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2632a {

        /* renamed from: a */
        private static C2631c f5185a = new C2631c();
    }

    /* renamed from: a */
    public static C2631c m19525a() {
        return C2632a.f5185a;
    }

    /* renamed from: a */
    private String m19524a(int i, int i2) {
        String str = "";
        if (i == 1) {
            String m19514c = m19514c(this.f5180e);
            if (!"".equals(m19514c)) {
                str = "&ll_pre=" + m19514c;
            }
        }
        if (i2 == 1) {
            String m19516b = m19516b(this.f5178c);
            if (!"".equals(m19516b)) {
                str = str + "&cl_pre=" + m19516b;
            }
            String m19516b2 = m19516b(this.f5179d);
            if ("".equals(m19516b2)) {
                return str;
            }
            return str + "&wf_pre=" + m19516b2;
        }
        return str;
    }

    /* renamed from: a */
    private void m19521a(String str, Deque<String> deque) {
        if (str == null || "".equals(str)) {
            return;
        }
        deque.addAll(Arrays.asList(new String(Base64.decode(str.getBytes(), 0)).split("\\|")));
    }

    /* renamed from: a */
    private void m19520a(Deque<String> deque) {
        if (deque == null || deque.isEmpty()) {
            return;
        }
        while (deque.size() > this.f5182g) {
            deque.pollLast();
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : deque) {
            sb.append(str);
            if (i != deque.size() - 1) {
                sb.append("|");
            }
            i++;
        }
        try {
            String str2 = new String(Base64.encode(sb.toString().getBytes(), 0));
            SharedPreferences.Editor edit = this.f5176a.edit();
            edit.putString("ll_pre", str2);
            edit.commit();
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    private void m19519a(Deque<String> deque, String str) {
        if (deque == null || deque.isEmpty()) {
            return;
        }
        while (deque.size() > this.f5181f) {
            deque.pollLast();
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str2 : deque) {
            sb.append(str2);
            if (i != deque.size() - 1) {
                sb.append("|");
            }
            i++;
        }
        try {
            String str3 = new String(Base64.encode(sb.toString().getBytes(), 0));
            SharedPreferences.Editor edit = this.f5176a.edit();
            edit.putString(str + "_pre", str3);
            edit.commit();
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    private String m19516b(Deque<String> deque) {
        if (deque == null || deque.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String peekFirst = deque.peekFirst();
        if (peekFirst != null) {
            String[] split = peekFirst.split(",");
            int i = 0;
            for (String str : deque) {
                if (split.length != 3) {
                    break;
                }
                String[] split2 = str.split(",");
                if (i == 0) {
                    sb.append(peekFirst);
                } else if (split2.length != 3) {
                    i++;
                } else {
                    try {
                        sb.append((int) ((Double.parseDouble(split[0]) - Double.parseDouble(split2[0])) * Math.pow(10.0d, 6.0d)));
                        sb.append(",");
                        sb.append((int) ((Double.parseDouble(split[1]) - Double.parseDouble(split2[1])) * Math.pow(10.0d, 6.0d)));
                        sb.append(",");
                        sb.append(Long.parseLong(split[2]) - Long.parseLong(split2[2]));
                    } catch (Exception unused) {
                    }
                }
                if (i != deque.size() - 1) {
                    sb.append("|");
                }
                i++;
            }
        }
        return sb.toString();
    }

    /* renamed from: b */
    private void m19517b(String str, Deque<String> deque) {
        if (str == null || "".equals(str)) {
            return;
        }
        deque.addAll(Arrays.asList(new String(Base64.decode(str.getBytes(), 0)).split("\\|")));
    }

    /* renamed from: c */
    private String m19514c(Deque<String> deque) {
        if (deque == null || deque.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String peekFirst = deque.peekFirst();
        if (peekFirst != null) {
            String[] split = peekFirst.split(",");
            int i = 0;
            for (String str : deque) {
                if (split.length != 7) {
                    break;
                }
                String[] split2 = str.split(",");
                if (i == 0) {
                    sb.append(peekFirst);
                } else if (split2.length != 7) {
                    i++;
                } else {
                    try {
                        sb.append(Integer.parseInt(split[0]) - Integer.parseInt(split2[0]));
                        sb.append(",");
                        sb.append(Integer.parseInt(split[1]) - Integer.parseInt(split2[1]));
                        sb.append(",");
                        sb.append(new BigDecimal(split[2]).subtract(new BigDecimal(split2[2])));
                        sb.append(",");
                        sb.append(Long.parseLong(split[3]) - Long.parseLong(split2[3]));
                        sb.append(",");
                        sb.append(new BigDecimal(split[4]).subtract(new BigDecimal(split2[4])));
                        sb.append(",");
                        double doubleValue = new BigDecimal(split[5]).subtract(new BigDecimal(split2[5])).doubleValue();
                        double doubleValue2 = new BigDecimal(split[6]).subtract(new BigDecimal(split2[6])).doubleValue();
                        sb.append((int) (doubleValue * Math.pow(10.0d, 6.0d)));
                        sb.append(",");
                        sb.append((int) (doubleValue2 * Math.pow(10.0d, 6.0d)));
                    } catch (Exception unused) {
                    }
                }
                if (i != deque.size() - 1) {
                    sb.append("|");
                }
                i++;
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public void m19523a(Context context) {
        if (this.f5176a == null) {
            this.f5176a = C2670r.m19351a().m19350a(context);
        }
        SharedPreferences sharedPreferences = this.f5176a;
        if (sharedPreferences == null || this.f5177b) {
            return;
        }
        try {
            String string = sharedPreferences.getString("cl_pre", "");
            String string2 = this.f5176a.getString("wf_pre", "");
            String string3 = this.f5176a.getString("ll_pre", "");
            m19521a(string, this.f5178c);
            m19521a(string2, this.f5179d);
            m19517b(string3, this.f5180e);
        } catch (Exception unused) {
        }
        this.f5177b = true;
    }

    /* renamed from: a */
    public synchronized void m19522a(BDLocation bDLocation, String str, Location location) {
        Deque<String> deque;
        String str2;
        if (bDLocation != null) {
            if ("gcj02".equals(str)) {
                String networkLocationType = bDLocation.getNetworkLocationType();
                int locType = bDLocation.getLocType();
                if (locType == 61 || locType == 161) {
                    if (networkLocationType == null) {
                        networkLocationType = "null";
                    }
                    if (networkLocationType.contains("wf") && this.f5183h == 1) {
                        this.f5179d.offerFirst(String.format(Locale.CHINA, "%f,%f,%d", Double.valueOf(bDLocation.getLongitude()), Double.valueOf(bDLocation.getLatitude()), Long.valueOf(C2735k.m19046d(bDLocation.getTime()))));
                        deque = this.f5179d;
                        str2 = "wf";
                    } else if (!networkLocationType.contains("cl") || this.f5183h != 1) {
                        if (locType == 61 && this.f5184i == 1 && location != null) {
                            this.f5180e.offerFirst(String.format(Locale.CANADA, "%d,%d,%.2f,%d,%.1f,%.6f,%.6f", Integer.valueOf(bDLocation.getSatelliteNumber()), Integer.valueOf((int) bDLocation.getRadius()), Double.valueOf(bDLocation.getAltitude()), Long.valueOf(C2735k.m19046d(bDLocation.getTime())), Float.valueOf(bDLocation.getSpeed()), Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude())));
                            m19520a(this.f5180e);
                        }
                    } else {
                        this.f5178c.offerFirst(String.format(Locale.CHINA, "%f,%f,%d", Double.valueOf(bDLocation.getLongitude()), Double.valueOf(bDLocation.getLatitude()), Long.valueOf(C2735k.m19046d(bDLocation.getTime()))));
                        deque = this.f5178c;
                        str2 = "cl";
                    }
                    m19519a(deque, str2);
                }
            }
        }
    }

    /* renamed from: b */
    public String m19518b() {
        return m19524a(this.f5184i, this.f5183h);
    }

    /* renamed from: c */
    public synchronized String m19515c() {
        return m19524a(this.f5183h, this.f5184i);
    }
}
