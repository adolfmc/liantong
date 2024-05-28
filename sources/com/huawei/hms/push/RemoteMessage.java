package com.huawei.hms.push;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.push.utils.DateUtil;
import com.huawei.hms.push.utils.JsonUtil;
import com.huawei.hms.support.api.push.PushException;
import com.huawei.hms.support.log.HMSLog;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RemoteMessage implements Parcelable {
    public static final Parcelable.Creator<RemoteMessage> CREATOR;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN = 0;

    /* renamed from: c */
    private static final String[] f11580c;

    /* renamed from: d */
    private static final int[] f11581d;

    /* renamed from: e */
    private static final long[] f11582e;

    /* renamed from: f */
    private static final HashMap<String, Object> f11583f;

    /* renamed from: g */
    private static final HashMap<String, Object> f11584g;

    /* renamed from: h */
    private static final HashMap<String, Object> f11585h;

    /* renamed from: i */
    private static final HashMap<String, Object> f11586i;

    /* renamed from: j */
    private static final HashMap<String, Object> f11587j;

    /* renamed from: a */
    private Bundle f11588a;

    /* renamed from: b */
    private Notification f11589b;

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Builder {

        /* renamed from: a */
        private final Bundle f11590a;

        /* renamed from: b */
        private final Map<String, String> f11591b;

        public Builder(String str) {
            Bundle bundle = new Bundle();
            this.f11590a = bundle;
            this.f11591b = new HashMap();
            bundle.putString("to", str);
        }

        public Builder addData(String str, String str2) {
            if (str != null) {
                this.f11591b.put(str, str2);
                return this;
            }
            throw new IllegalArgumentException("add data failed, key is null.");
        }

        public RemoteMessage build() {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            try {
                for (Map.Entry<String, String> entry : this.f11591b.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
                try {
                    String jSONObject2 = jSONObject.toString();
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("collapseKey", this.f11590a.getString("collapseKey"));
                    jSONObject3.put("ttl", this.f11590a.getInt("ttl"));
                    jSONObject3.put("sendMode", this.f11590a.getInt("sendMode"));
                    jSONObject3.put("receiptMode", this.f11590a.getInt("receiptMode"));
                    JSONObject jSONObject4 = new JSONObject();
                    if (jSONObject.length() != 0) {
                        jSONObject4.put("data", jSONObject2);
                    }
                    jSONObject4.put("msgId", this.f11590a.getString("msgId"));
                    jSONObject3.put("msgContent", jSONObject4);
                    bundle.putByteArray("message_body", jSONObject3.toString().getBytes(PushConst.f11631a));
                    bundle.putString("to", this.f11590a.getString("to"));
                    bundle.putString("message_type", this.f11590a.getString("message_type"));
                    return new RemoteMessage(bundle);
                } catch (JSONException unused) {
                    HMSLog.m14109w("RemoteMessage", "JSONException: parse message body failed.");
                    throw new PushException("send message failed");
                }
            } catch (JSONException unused2) {
                HMSLog.m14109w("RemoteMessage", "JSONException: parse data to json failed.");
                throw new PushException("send message failed");
            }
        }

        public Builder clearData() {
            this.f11591b.clear();
            return this;
        }

        public Builder setCollapseKey(String str) {
            this.f11590a.putString("collapseKey", str);
            return this;
        }

        public Builder setData(Map<String, String> map) {
            this.f11591b.clear();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                this.f11591b.put(entry.getKey(), entry.getValue());
            }
            return this;
        }

        public Builder setMessageId(String str) {
            this.f11590a.putString("msgId", str);
            return this;
        }

        public Builder setMessageType(String str) {
            this.f11590a.putString("message_type", str);
            return this;
        }

        public Builder setReceiptMode(int i) {
            if (i != 1 && i != 0) {
                throw new IllegalArgumentException("receipt mode can only be 0 or 1.");
            }
            this.f11590a.putInt("receiptMode", i);
            return this;
        }

        public Builder setSendMode(int i) {
            if (i != 0 && i != 1) {
                throw new IllegalArgumentException("send mode can only be 0 or 1.");
            }
            this.f11590a.putInt("sendMode", i);
            return this;
        }

        public Builder setTtl(int i) {
            if (i >= 1 && i <= 1296000) {
                this.f11590a.putInt("ttl", i);
                return this;
            }
            throw new IllegalArgumentException("ttl must be greater than or equal to 1 and less than or equal to 1296000");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public @interface MessagePriority {
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Notification implements Serializable {

        /* renamed from: A */
        private final long[] f11592A;

        /* renamed from: B */
        private final String f11593B;

        /* renamed from: a */
        private final String f11594a;

        /* renamed from: b */
        private final String f11595b;

        /* renamed from: c */
        private final String[] f11596c;

        /* renamed from: d */
        private final String f11597d;

        /* renamed from: e */
        private final String f11598e;

        /* renamed from: f */
        private final String[] f11599f;

        /* renamed from: g */
        private final String f11600g;

        /* renamed from: h */
        private final String f11601h;

        /* renamed from: i */
        private final String f11602i;

        /* renamed from: j */
        private final String f11603j;

        /* renamed from: k */
        private final String f11604k;

        /* renamed from: l */
        private final String f11605l;

        /* renamed from: m */
        private final String f11606m;

        /* renamed from: n */
        private final Uri f11607n;

        /* renamed from: o */
        private final int f11608o;

        /* renamed from: p */
        private final String f11609p;

        /* renamed from: q */
        private final int f11610q;

        /* renamed from: r */
        private final int f11611r;

        /* renamed from: s */
        private final int f11612s;

        /* renamed from: t */
        private final int[] f11613t;

        /* renamed from: u */
        private final String f11614u;

        /* renamed from: v */
        private final int f11615v;

        /* renamed from: w */
        private final String f11616w;

        /* renamed from: x */
        private final int f11617x;

        /* renamed from: y */
        private final String f11618y;

        /* renamed from: z */
        private final String f11619z;

        /* synthetic */ Notification(Bundle bundle, C5046a c5046a) {
            this(bundle);
        }

        /* renamed from: a */
        private Integer m14303a(String str) {
            if (str != null) {
                try {
                    return Integer.valueOf(str);
                } catch (NumberFormatException unused) {
                    HMSLog.m14109w("RemoteMessage", "NumberFormatException: get " + str + " failed.");
                }
            }
            return null;
        }

        public Integer getBadgeNumber() {
            return m14303a(this.f11616w);
        }

        public String getBody() {
            return this.f11597d;
        }

        public String[] getBodyLocalizationArgs() {
            String[] strArr = this.f11599f;
            return strArr == null ? new String[0] : (String[]) strArr.clone();
        }

        public String getBodyLocalizationKey() {
            return this.f11598e;
        }

        public String getChannelId() {
            return this.f11606m;
        }

        public String getClickAction() {
            return this.f11604k;
        }

        public String getColor() {
            return this.f11603j;
        }

        public String getIcon() {
            return this.f11600g;
        }

        public Uri getImageUrl() {
            String str = this.f11609p;
            if (str == null) {
                return null;
            }
            return Uri.parse(str);
        }

        public Integer getImportance() {
            return m14303a(this.f11618y);
        }

        public String getIntentUri() {
            return this.f11605l;
        }

        public int[] getLightSettings() {
            int[] iArr = this.f11613t;
            return iArr == null ? new int[0] : (int[]) iArr.clone();
        }

        public Uri getLink() {
            return this.f11607n;
        }

        public int getNotifyId() {
            return this.f11608o;
        }

        public String getSound() {
            return this.f11601h;
        }

        public String getTag() {
            return this.f11602i;
        }

        public String getTicker() {
            return this.f11619z;
        }

        public String getTitle() {
            return this.f11594a;
        }

        public String[] getTitleLocalizationArgs() {
            String[] strArr = this.f11596c;
            return strArr == null ? new String[0] : (String[]) strArr.clone();
        }

        public String getTitleLocalizationKey() {
            return this.f11595b;
        }

        public long[] getVibrateConfig() {
            long[] jArr = this.f11592A;
            return jArr == null ? new long[0] : (long[]) jArr.clone();
        }

        public Integer getVisibility() {
            return m14303a(this.f11593B);
        }

        public Long getWhen() {
            if (!TextUtils.isEmpty(this.f11614u)) {
                try {
                    return Long.valueOf(DateUtil.parseUtcToMillisecond(this.f11614u));
                } catch (StringIndexOutOfBoundsException unused) {
                    HMSLog.m14109w("RemoteMessage", "StringIndexOutOfBoundsException: parse when failed.");
                } catch (ParseException unused2) {
                    HMSLog.m14109w("RemoteMessage", "ParseException: parse when failed.");
                }
            }
            return null;
        }

        public boolean isAutoCancel() {
            return this.f11617x == 1;
        }

        public boolean isDefaultLight() {
            return this.f11610q == 1;
        }

        public boolean isDefaultSound() {
            return this.f11611r == 1;
        }

        public boolean isDefaultVibrate() {
            return this.f11612s == 1;
        }

        public boolean isLocalOnly() {
            return this.f11615v == 1;
        }

        private Notification(Bundle bundle) {
            this.f11594a = bundle.getString("notifyTitle");
            this.f11597d = bundle.getString("content");
            this.f11595b = bundle.getString("title_loc_key");
            this.f11598e = bundle.getString("body_loc_key");
            this.f11596c = bundle.getStringArray("title_loc_args");
            this.f11599f = bundle.getStringArray("body_loc_args");
            this.f11600g = bundle.getString("icon");
            this.f11603j = bundle.getString("color");
            this.f11601h = bundle.getString("sound");
            this.f11602i = bundle.getString("tag");
            this.f11606m = bundle.getString("channelId");
            this.f11604k = bundle.getString("acn");
            this.f11605l = bundle.getString("intentUri");
            this.f11608o = bundle.getInt("notifyId");
            String string = bundle.getString("url");
            this.f11607n = !TextUtils.isEmpty(string) ? Uri.parse(string) : null;
            this.f11609p = bundle.getString("notifyIcon");
            this.f11610q = bundle.getInt("defaultLightSettings");
            this.f11611r = bundle.getInt("defaultSound");
            this.f11612s = bundle.getInt("defaultVibrateTimings");
            this.f11613t = bundle.getIntArray("lightSettings");
            this.f11614u = bundle.getString("when");
            this.f11615v = bundle.getInt("localOnly");
            this.f11616w = bundle.getString("badgeSetNum", null);
            this.f11617x = bundle.getInt("autoCancel");
            this.f11618y = bundle.getString("priority", null);
            this.f11619z = bundle.getString("ticker");
            this.f11592A = bundle.getLongArray("vibrateTimings");
            this.f11593B = bundle.getString("visibility", null);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.push.RemoteMessage$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    class C5046a implements Parcelable.Creator<RemoteMessage> {
        C5046a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public RemoteMessage createFromParcel(Parcel parcel) {
            return new RemoteMessage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public RemoteMessage[] newArray(int i) {
            return new RemoteMessage[i];
        }
    }

    static {
        String[] strArr = new String[0];
        f11580c = strArr;
        int[] iArr = new int[0];
        f11581d = iArr;
        long[] jArr = new long[0];
        f11582e = jArr;
        HashMap<String, Object> hashMap = new HashMap<>(8);
        f11583f = hashMap;
        hashMap.put("from", "");
        hashMap.put("collapseKey", "");
        hashMap.put("sendTime", "");
        hashMap.put("ttl", 86400);
        hashMap.put("urgency", 2);
        hashMap.put("oriUrgency", 2);
        hashMap.put("sendMode", 0);
        hashMap.put("receiptMode", 0);
        HashMap<String, Object> hashMap2 = new HashMap<>(8);
        f11584g = hashMap2;
        hashMap2.put("title_loc_key", "");
        hashMap2.put("body_loc_key", "");
        hashMap2.put("notifyIcon", "");
        hashMap2.put("title_loc_args", strArr);
        hashMap2.put("body_loc_args", strArr);
        hashMap2.put("ticker", "");
        hashMap2.put("notifyTitle", "");
        hashMap2.put("content", "");
        HashMap<String, Object> hashMap3 = new HashMap<>(8);
        f11585h = hashMap3;
        hashMap3.put("icon", "");
        hashMap3.put("color", "");
        hashMap3.put("sound", "");
        hashMap3.put("defaultLightSettings", 1);
        hashMap3.put("lightSettings", iArr);
        hashMap3.put("defaultSound", 1);
        hashMap3.put("defaultVibrateTimings", 1);
        hashMap3.put("vibrateTimings", jArr);
        HashMap<String, Object> hashMap4 = new HashMap<>(8);
        f11586i = hashMap4;
        hashMap4.put("tag", "");
        hashMap4.put("when", "");
        hashMap4.put("localOnly", 1);
        hashMap4.put("badgeSetNum", "");
        hashMap4.put("priority", "");
        hashMap4.put("autoCancel", 1);
        hashMap4.put("visibility", "");
        hashMap4.put("channelId", "");
        HashMap<String, Object> hashMap5 = new HashMap<>(3);
        f11587j = hashMap5;
        hashMap5.put("acn", "");
        hashMap5.put("intentUri", "");
        hashMap5.put("url", "");
        CREATOR = new C5046a();
    }

    public RemoteMessage(Bundle bundle) {
        this.f11588a = m14310a(bundle);
    }

    /* renamed from: a */
    private Bundle m14310a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        JSONObject m14307b = m14307b(bundle);
        JSONObject m14309a = m14309a(m14307b);
        String string = JsonUtil.getString(m14309a, "data", null);
        bundle2.putString("analyticInfo", JsonUtil.getString(m14309a, "analyticInfo", null));
        bundle2.putString("device_token", bundle.getString("device_token"));
        JSONObject m14304d = m14304d(m14309a);
        JSONObject m14306b = m14306b(m14304d);
        JSONObject m14305c = m14305c(m14304d);
        if (bundle.getInt("inputType") == 1 && AbstractC5048c.m14294a(m14309a, m14304d, string)) {
            bundle2.putString("data", AbstractC5047a.m14300a(bundle.getByteArray("message_body")));
            return bundle2;
        }
        String string2 = bundle.getString("to");
        String string3 = bundle.getString("message_type");
        String string4 = JsonUtil.getString(m14309a, "msgId", null);
        bundle2.putString("to", string2);
        bundle2.putString("data", string);
        bundle2.putString("msgId", string4);
        bundle2.putString("message_type", string3);
        JsonUtil.transferJsonObjectToBundle(m14307b, bundle2, f11583f);
        bundle2.putBundle("notification", m14308a(m14307b, m14309a, m14304d, m14306b, m14305c));
        return bundle2;
    }

    /* renamed from: b */
    private static JSONObject m14307b(Bundle bundle) {
        try {
            return new JSONObject(AbstractC5047a.m14300a(bundle.getByteArray("message_body")));
        } catch (JSONException unused) {
            HMSLog.m14109w("RemoteMessage", "JSONException:parse message body failed.");
            return null;
        }
    }

    /* renamed from: c */
    private static JSONObject m14305c(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject("param");
        }
        return null;
    }

    /* renamed from: d */
    private static JSONObject m14304d(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject("psContent");
        }
        return null;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public String getAnalyticInfo() {
        return this.f11588a.getString("analyticInfo");
    }

    public Map<String, String> getAnalyticInfoMap() {
        HashMap hashMap = new HashMap();
        String string = this.f11588a.getString("analyticInfo");
        if (string != null && !string.trim().isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String valueOf = String.valueOf(keys.next());
                    hashMap.put(valueOf, String.valueOf(jSONObject.get(valueOf)));
                }
            } catch (JSONException unused) {
                HMSLog.m14109w("RemoteMessage", "JSONException: get analyticInfo from map failed.");
            }
        }
        return hashMap;
    }

    public String getCollapseKey() {
        return this.f11588a.getString("collapseKey");
    }

    public String getData() {
        return this.f11588a.getString("data");
    }

    public Map<String, String> getDataOfMap() {
        HashMap hashMap = new HashMap();
        String string = this.f11588a.getString("data");
        if (string != null && !string.trim().isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String valueOf = String.valueOf(keys.next());
                    hashMap.put(valueOf, String.valueOf(jSONObject.get(valueOf)));
                }
            } catch (JSONException unused) {
                HMSLog.m14109w("RemoteMessage", "JSONException: get data from map failed");
            }
        }
        return hashMap;
    }

    public String getFrom() {
        return this.f11588a.getString("from");
    }

    public String getMessageId() {
        return this.f11588a.getString("msgId");
    }

    public String getMessageType() {
        return this.f11588a.getString("message_type");
    }

    public Notification getNotification() {
        Bundle bundle = this.f11588a.getBundle("notification");
        if (this.f11589b == null && bundle != null) {
            this.f11589b = new Notification(bundle, null);
        }
        if (this.f11589b == null) {
            this.f11589b = new Notification(new Bundle(), null);
        }
        return this.f11589b;
    }

    public int getOriginalUrgency() {
        int i = this.f11588a.getInt("oriUrgency");
        if (i == 1 || i == 2) {
            return i;
        }
        return 0;
    }

    public int getReceiptMode() {
        return this.f11588a.getInt("receiptMode");
    }

    public int getSendMode() {
        return this.f11588a.getInt("sendMode");
    }

    public long getSentTime() {
        try {
            String string = this.f11588a.getString("sendTime");
            if (TextUtils.isEmpty(string)) {
                return 0L;
            }
            return Long.parseLong(string);
        } catch (NumberFormatException unused) {
            HMSLog.m14109w("RemoteMessage", "NumberFormatException: get sendTime error.");
            return 0L;
        }
    }

    public String getTo() {
        return this.f11588a.getString("to");
    }

    public String getToken() {
        return this.f11588a.getString("device_token");
    }

    public int getTtl() {
        return this.f11588a.getInt("ttl");
    }

    public int getUrgency() {
        int i = this.f11588a.getInt("urgency");
        if (i == 1 || i == 2) {
            return i;
        }
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.f11588a);
        parcel.writeSerializable(this.f11589b);
    }

    public RemoteMessage(Parcel parcel) {
        this.f11588a = parcel.readBundle();
        this.f11589b = (Notification) parcel.readSerializable();
    }

    /* renamed from: b */
    private static JSONObject m14306b(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject("notifyDetail");
        }
        return null;
    }

    /* renamed from: a */
    private Bundle m14308a(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5) {
        Bundle bundle = new Bundle();
        JsonUtil.transferJsonObjectToBundle(jSONObject3, bundle, f11584g);
        JsonUtil.transferJsonObjectToBundle(jSONObject4, bundle, f11585h);
        JsonUtil.transferJsonObjectToBundle(jSONObject, bundle, f11586i);
        JsonUtil.transferJsonObjectToBundle(jSONObject5, bundle, f11587j);
        bundle.putInt("notifyId", JsonUtil.getInt(jSONObject2, "notifyId", 0));
        return bundle;
    }

    /* renamed from: a */
    private static JSONObject m14309a(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject("msgContent");
        }
        return null;
    }
}
