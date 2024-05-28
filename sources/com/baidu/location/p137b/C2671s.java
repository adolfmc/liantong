package com.baidu.location.p137b;

import android.annotation.TargetApi;
import android.location.GnssNavigationMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.s */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2671s {

    /* renamed from: a */
    private static final double[] f5401a = {1999.0d, 8.0d, 22.0d, 0.0d, 0.0d, 0.0d};

    /* renamed from: b */
    private HashMap<String, C2674c> f5402b = new HashMap<>();

    /* renamed from: c */
    private HashMap<String, String> f5403c = new HashMap<>();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.s$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2672a {

        /* renamed from: a */
        private static C2671s f5404a = new C2671s();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.s$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2673b {

        /* renamed from: a */
        int f5405a = 0;

        /* renamed from: b */
        double f5406b = 0.0d;

        public C2673b() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.s$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2674c {

        /* renamed from: b */
        private boolean f5409b;

        /* renamed from: c */
        private boolean f5410c;

        /* renamed from: d */
        private long f5411d;

        /* renamed from: e */
        private int f5412e;

        /* renamed from: f */
        private int f5413f;

        /* renamed from: g */
        private ArrayList<String> f5414g;

        /* renamed from: h */
        private ArrayList<Integer> f5415h;

        /* renamed from: i */
        private int f5416i;

        /* renamed from: j */
        private double f5417j;

        /* renamed from: k */
        private double f5418k;

        /* renamed from: l */
        private double f5419l;

        /* renamed from: m */
        private int f5420m;

        /* renamed from: n */
        private int f5421n;

        /* renamed from: o */
        private C2673b f5422o;

        public C2674c(int i, int i2) {
            m19342a(i, i2);
            this.f5416i = 0;
            this.f5417j = 0.0d;
            this.f5418k = 0.0d;
            this.f5419l = 0.0d;
            this.f5420m = 0;
            this.f5421n = 0;
            this.f5422o = new C2673b();
        }

        /* renamed from: a */
        private C2673b m19343a(int i, double d) {
            C2673b m19336a = m19336a(C2671s.f5401a);
            d = (d < -1.0E9d || 1.0E9d < d) ? 0.0d : 0.0d;
            int i2 = (int) d;
            m19336a.f5405a += (i * 604800) + i2;
            m19336a.f5406b = d - i2;
            return m19336a;
        }

        /* renamed from: a */
        private C2673b m19336a(double[] dArr) {
            int[] iArr = {1, 32, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
            C2673b c2673b = new C2673b();
            int i = 0;
            int i2 = (int) dArr[0];
            int i3 = (int) dArr[1];
            int i4 = (int) dArr[2];
            if (i2 >= 1970 && 2099 >= i2 && i3 >= 1 && 12 >= i3) {
                int i5 = (((((i2 - 1970) * 365) + ((i2 - 1969) / 4)) + iArr[i3 - 1]) + i4) - 2;
                if (i2 % 4 == 0 && i3 >= 3) {
                    i = 1;
                }
                int floor = (int) Math.floor(dArr[5]);
                c2673b.f5405a = ((i5 + i) * 86400) + (((int) dArr[3]) * 3600) + (((int) dArr[4]) * 60) + floor;
                c2673b.f5406b = dArr[5] - floor;
            }
            return c2673b;
        }

        @TargetApi(24)
        /* renamed from: a */
        private String m19341a(GnssNavigationMessage gnssNavigationMessage) {
            StringBuilder sb = new StringBuilder();
            byte[] data = gnssNavigationMessage.getData();
            int length = data.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%8s", Integer.toBinaryString(data[i] & 255)).replace(' ', '0'));
            }
            return sb.toString();
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x002c  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void m19344a() {
            /*
                r5 = this;
                java.util.ArrayList<java.lang.String> r0 = r5.f5414g
                int r0 = r0.size()
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L29
                r0 = r2
            Lb:
                java.util.ArrayList<java.lang.String> r3 = r5.f5414g
                int r3 = r3.size()
                if (r0 >= r3) goto L27
                java.util.ArrayList<java.lang.String> r3 = r5.f5414g
                java.lang.Object r3 = r3.get(r0)
                java.lang.String r3 = (java.lang.String) r3
                java.lang.String r4 = "None"
                boolean r3 = r3.contains(r4)
                if (r3 == 0) goto L24
                goto L29
            L24:
                int r0 = r0 + 1
                goto Lb
            L27:
                r0 = r1
                goto L2a
            L29:
                r0 = r2
            L2a:
                if (r0 == 0) goto L2f
                r5.f5410c = r1
                goto L31
            L2f:
                r5.f5410c = r2
            L31:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2671s.C2674c.m19344a():void");
        }

        /* renamed from: a */
        private void m19342a(int i, int i2) {
            int i3 = this.f5413f;
            int i4 = 5;
            if (i3 != 257 && i3 != 769) {
                if (i3 != 1537) {
                    switch (i3) {
                        case 1281:
                            i4 = 3;
                            break;
                        case 1282:
                            i4 = 10;
                            break;
                        default:
                            i4 = 0;
                            break;
                    }
                } else {
                    i4 = 6;
                }
            }
            ArrayList<String> arrayList = this.f5414g;
            if (arrayList != null) {
                arrayList.clear();
            } else {
                this.f5414g = new ArrayList<>();
            }
            ArrayList<Integer> arrayList2 = this.f5415h;
            if (arrayList2 != null) {
                arrayList2.clear();
            } else {
                this.f5415h = new ArrayList<>();
            }
            for (int i5 = 0; i5 < i4; i5++) {
                this.f5414g.add("None");
            }
            this.f5412e = i;
            this.f5413f = i2;
            this.f5409b = false;
            this.f5410c = false;
            this.f5411d = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @TargetApi(24)
        /* renamed from: a */
        public void m19340a(GnssNavigationMessage gnssNavigationMessage, long j) {
            int type = gnssNavigationMessage.getType();
            int svid = gnssNavigationMessage.getSvid();
            int submessageId = gnssNavigationMessage.getSubmessageId();
            byte[] data = gnssNavigationMessage.getData();
            if (j - this.f5411d > 1200 || this.f5409b || this.f5414g.size() == 0 || type != this.f5413f || svid != this.f5412e) {
                m19342a(svid, type);
            }
            if ((type == 1282 || type == 1281) && !m19335b()) {
                m19342a(svid, type);
            }
            if (this.f5414g.size() == 0) {
                return;
            }
            int i = this.f5413f == 1537 ? 0 : 1;
            if (this.f5413f == 1282) {
                if (submessageId != 1) {
                    return;
                }
                m19328e(m19334b(gnssNavigationMessage));
                submessageId = this.f5420m;
            }
            int i2 = submessageId - i;
            if (i2 >= this.f5414g.size()) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            boolean z = true;
            for (byte b : data) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                sb.append((int) b);
            }
            this.f5414g.set(i2, sb.toString());
            if (type == 1281 || type == 1282) {
                this.f5415h.add(Integer.valueOf(i2));
            }
            if (this.f5413f == 1537) {
                m19337a(m19341a(gnssNavigationMessage));
            }
            m19344a();
            this.f5411d = j;
        }

        /* renamed from: a */
        private void m19337a(String str) {
            StringBuilder sb;
            int i;
            char charAt = str.charAt(0);
            char charAt2 = str.charAt(120);
            if (charAt == '1' && charAt2 == '0') {
                sb = new StringBuilder();
                sb.append(str.substring(2, 18));
                i = 234;
            } else if (charAt != '0' || charAt2 != '1') {
                return;
            } else {
                sb = new StringBuilder();
                sb.append(str.substring(2, 114));
                i = 138;
            }
            sb.append(str.substring(122, i));
            String sb2 = sb.toString();
            int parseInt = Integer.parseInt(sb2.substring(0, 6), 2);
            if (parseInt == 0) {
                m19333b(sb2);
            } else if (parseInt == 1) {
                m19331c(sb2);
            } else if (parseInt == 4) {
                m19329d(sb2);
            }
        }

        @TargetApi(24)
        /* renamed from: b */
        private String m19334b(GnssNavigationMessage gnssNavigationMessage) {
            StringBuilder sb = new StringBuilder();
            byte[] data = gnssNavigationMessage.getData();
            int length = data.length;
            for (int i = 0; i < length; i++) {
                String replace = String.format("%8s", Integer.toBinaryString(data[i] & 255)).replace(' ', '0');
                if (i % 4 == 0) {
                    replace = replace.substring(2, 8);
                }
                sb.append(replace);
            }
            return sb.toString();
        }

        /* renamed from: b */
        private void m19333b(String str) {
            this.f5416i = Integer.parseInt(str.substring(96, 108), 2);
            this.f5417j = Long.parseLong(str.substring(108, 128), 2);
        }

        /* renamed from: b */
        private boolean m19335b() {
            if (this.f5415h == null) {
                return false;
            }
            for (int i = 0; i < this.f5415h.size(); i++) {
                if (this.f5415h.get(i).intValue() != i) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public String m19332c() {
            StringBuilder sb = new StringBuilder();
            if (this.f5410c) {
                if (this.f5413f == 1537) {
                    m19330d();
                }
                sb.append(this.f5422o.f5405a);
                sb.append('|');
                boolean z = true;
                for (int i = 0; i < this.f5414g.size(); i++) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(',');
                    }
                    sb.append(this.f5414g.get(i));
                }
                this.f5409b = true;
                return sb.toString();
            }
            return sb.toString();
        }

        /* renamed from: c */
        private void m19331c(String str) {
            this.f5418k = Long.parseLong(str.substring(16, 30), 2) * 60.0d;
        }

        /* renamed from: d */
        private void m19330d() {
            C2673b m19343a;
            int i;
            C2673b m19343a2 = m19343a(this.f5416i, this.f5417j);
            double d = ((m19343a.f5405a - m19343a2.f5405a) + m19343a(this.f5416i, this.f5418k).f5406b) - m19343a2.f5406b;
            if (d <= 302400.0d) {
                if (d < -302400.0d) {
                    i = this.f5416i + 1;
                }
                this.f5422o = m19343a(this.f5416i, this.f5419l);
                this.f5421n = this.f5416i + 1024;
            }
            i = this.f5416i - 1;
            this.f5416i = i;
            this.f5422o = m19343a(this.f5416i, this.f5419l);
            this.f5421n = this.f5416i + 1024;
        }

        /* renamed from: d */
        private void m19329d(String str) {
            this.f5419l = Long.parseLong(str.substring(54, 68), 2) * 60;
        }

        /* renamed from: e */
        private void m19328e(String str) {
            this.f5420m = Integer.parseInt(str.substring(42, 46), 2);
        }
    }

    /* renamed from: a */
    public static C2671s m19349a() {
        return C2672a.f5404a;
    }

    @TargetApi(24)
    /* renamed from: a */
    public void m19348a(GnssNavigationMessage gnssNavigationMessage, long j) {
        String str;
        HashMap<String, C2674c> hashMap;
        int svid = gnssNavigationMessage.getSvid();
        int type = gnssNavigationMessage.getType();
        if (type == 257) {
            str = "G";
        } else if (type == 769) {
            str = "R";
        } else if (type != 1537) {
            switch (type) {
                case 1281:
                    str = "CO";
                    break;
                case 1282:
                    str = "CT";
                    break;
                default:
                    str = "none";
                    break;
            }
        } else {
            str = "E";
        }
        String str2 = str + svid;
        if (str2.contains("none") || (hashMap = this.f5402b) == null) {
            return;
        }
        if (!hashMap.containsKey(str2)) {
            this.f5402b.put(str2, new C2674c(svid, type));
        }
        this.f5402b.get(str2).m19340a(gnssNavigationMessage, j);
    }

    /* renamed from: b */
    public ArrayList<String> m19347b() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Map.Entry<String, C2674c> entry : this.f5402b.entrySet()) {
            String key = entry.getKey();
            String m19332c = entry.getValue().m19332c();
            if (m19332c != null && m19332c.length() != 0) {
                if (!this.f5403c.containsKey(key)) {
                    this.f5403c.put(key, m19332c);
                } else if (m19332c.substring(0, 100).equals(this.f5403c.get(key).substring(0, 100))) {
                }
                arrayList.add(key + '|' + m19332c);
            }
        }
        return arrayList;
    }
}
