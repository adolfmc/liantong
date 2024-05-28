package com.megvii.lv5;

import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.d */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5402d {

    /* renamed from: a */
    public static String f12429a = "";

    /* renamed from: b */
    public static String f12430b = "";

    /* renamed from: c */
    public static int f12431c;

    /* renamed from: d */
    public static volatile boolean f12432d;

    /* renamed from: e */
    public static final String[] f12433e = {"no_fail", "no_face_found", "pitch_too_big", "yaw_too_big", "raw_too_big", "face_area_too_small", "face_too_dark", "face_too_bright", "face_width_too_small", "face_width_too_big", "face_too_blurry", "face_out_of_rect", "eye_occlusion", "mouth_occlusion", "need_keeping", "not_vertical", "multiple_faces", "env_too_dark", "env_too_bright"};

    /* renamed from: f */
    public static final String[] f12434f = {"face_none", "other_action", "incontinuous_image", "time_out", "no_face_found", "no_face_sometimes", "face_lost", "action_too_fast", "face_occlusion", "mask", "face_aimless", "go_to_background"};

    /* renamed from: g */
    public static final String[] f12435g = {"fail_photo", "fail_read_ev"};

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.d$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class C5403a {

        /* renamed from: a */
        public static final C5402d f12436a = new C5402d();
    }

    /* renamed from: a */
    public static String m13602a(String str, int i) {
        StringBuilder sb;
        String str2;
        if (i == 1) {
            sb = new StringBuilder();
            sb.append(str);
            str2 = "_blink";
        } else if (i == 2) {
            sb = new StringBuilder();
            sb.append(str);
            str2 = "_mouth";
        } else if (i == 3) {
            sb = new StringBuilder();
            sb.append(str);
            str2 = "_shake";
        } else if (i == 4) {
            sb = new StringBuilder();
            sb.append(str);
            str2 = "_nod";
        } else {
            sb = new StringBuilder();
            sb.append(str);
            str2 = "_else";
        }
        sb.append(str2);
        return sb.toString();
    }

    /* renamed from: a */
    public JSONObject m13601a(String str, int i, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event_id", UUID.randomUUID().toString());
            jSONObject.put("type", "track");
            jSONObject.put("project", f12429a);
            jSONObject.put("time", System.currentTimeMillis());
            jSONObject.put("event", m13602a(str, i));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("biz_token", str2);
            jSONObject2.put("liveness", 2);
            jSONObject2.put("try_times", 0);
            int i2 = f12431c + 1;
            f12431c = i2;
            jSONObject2.put("index", i2);
            jSONObject.put("properties", jSONObject2);
            f12430b = str;
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public JSONObject m13600a(String str, int i, String str2, int i2, int i3) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "track");
            jSONObject.put("project", f12429a);
            jSONObject.put("event_id", UUID.randomUUID().toString());
            jSONObject.put("time", System.currentTimeMillis());
            String str3 = i3 == 1 ? "time_out" : f12434f[i2];
            jSONObject.put("event", m13602a(str, i) + ":" + str3);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("biz_token", str2);
            jSONObject2.put("liveness", 2);
            jSONObject2.put("try_times", 0);
            int i4 = f12431c + 1;
            f12431c = i4;
            jSONObject2.put("index", i4);
            jSONObject.put("properties", jSONObject2);
            f12430b = str;
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public JSONObject m13599a(String str, String str2, int i) {
        if (!f12432d || str.contains("fail_detect")) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", "track");
                jSONObject.put("project", f12429a);
                jSONObject.put("event_id", UUID.randomUUID().toString());
                jSONObject.put("time", System.currentTimeMillis());
                jSONObject.put("event", str);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("liveness", i);
                jSONObject2.put("biz_token", str2);
                jSONObject2.put("try_times", 0);
                int i2 = f12431c + 1;
                f12431c = i2;
                jSONObject2.put("index", i2);
                jSONObject.put("properties", jSONObject2);
                f12430b = str;
                return jSONObject;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    public JSONObject m13598a(String str, String str2, int i, int i2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "track");
            jSONObject.put("project", f12429a);
            jSONObject.put("event_id", UUID.randomUUID().toString());
            jSONObject.put("time", System.currentTimeMillis());
            jSONObject.put("event", str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("liveness", i);
            jSONObject2.put("biz_token", str2);
            jSONObject2.put("try_times", 0);
            jSONObject2.put("index", i2);
            jSONObject.put("properties", jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public JSONObject m13597a(String str, String str2, int i, String str3) {
        if (!f12432d || str.contains("fail_detect")) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", "track");
                jSONObject.put("project", f12429a);
                jSONObject.put("event_id", UUID.randomUUID().toString());
                jSONObject.put("time", System.currentTimeMillis());
                jSONObject.put("event", str);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("liveness", i);
                jSONObject2.put("biz_token", str2);
                jSONObject2.put("try_times", 0);
                int i2 = f12431c + 1;
                f12431c = i2;
                jSONObject2.put("index", i2);
                jSONObject2.put("image_quality_param", new JSONObject(str3));
                jSONObject.put("properties", jSONObject2);
                f12430b = str;
                return jSONObject;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    public JSONObject m13596a(Map<String, Object> map) {
        try {
            JSONObject jSONObject = new JSONObject(map);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("properties", jSONObject);
            jSONObject2.put("time", System.currentTimeMillis());
            jSONObject2.put("type", "profile_set");
            jSONObject2.put("project", f12429a);
            jSONObject2.put("event_id", UUID.randomUUID().toString());
            jSONObject2.put("event", "set_header");
            f12430b = "set_header";
            return jSONObject2;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
