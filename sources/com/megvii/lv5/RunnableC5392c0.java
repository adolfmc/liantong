package com.megvii.lv5;

import android.text.TextUtils;
import com.megvii.lv5.C5402d;
import com.megvii.lv5.C5462h2;
import com.megvii.lv5.sdk.result.LivenessFile;
import java.io.File;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.c0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC5392c0 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ C5404d0 f12409a;

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.c0$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5393a extends AbstractC5421e2 {
        public C5393a() {
        }

        @Override // com.megvii.lv5.AbstractC5421e2
        /* renamed from: a */
        public void mo12888a() {
        }

        @Override // com.megvii.lv5.AbstractC5421e2
        /* renamed from: a */
        public void mo12887a(File file) {
            C5404d0 c5404d0;
            int i;
            if (RunnableC5392c0.this.f12409a.f12488m == 12 || RunnableC5392c0.this.f12409a.f12488m == 14 || RunnableC5392c0.this.f12409a.f12488m == 17) {
                JSONObject jSONObject = null;
                if (RunnableC5392c0.this.f12409a.getView() != null && !TextUtils.isEmpty(RunnableC5392c0.this.f12409a.getView().mo12999e())) {
                    C5404d0 c5404d02 = RunnableC5392c0.this.f12409a;
                    C5402d.f12429a = c5404d02.f12482j;
                    C5402d c5402d = C5402d.C5403a.f12436a;
                    C5399c3.m13606a(c5402d.m13599a("scrn_video_generate", c5404d02.f12500s, c5404d02.f12502t));
                    RunnableC5392c0.this.f12409a.f12459V = file.toString();
                    C5404d0 c5404d03 = RunnableC5392c0.this.f12409a;
                    if (c5404d03.f12459V != null) {
                        C5402d.f12429a = c5404d03.f12482j;
                        C5399c3.m13606a(c5402d.m13599a("scrn_video_detect", c5404d03.f12500s, c5404d03.f12502t));
                        C5404d0 c5404d04 = RunnableC5392c0.this.f12409a;
                        if (c5404d04.m13589a(c5404d04.f12459V)) {
                            C5404d0 c5404d05 = RunnableC5392c0.this.f12409a;
                            C5402d.f12429a = c5404d05.f12482j;
                            C5399c3.m13606a(c5402d.m13599a("scrn_video_detect_pass", c5404d05.f12500s, c5404d05.f12502t));
                            System.currentTimeMillis();
                            File file2 = new File(RunnableC5392c0.this.f12409a.getView().getContext().getFilesDir(), "livenessFile");
                            System.currentTimeMillis();
                            String str = "";
                            C5404d0 c5404d06 = RunnableC5392c0.this.f12409a;
                            int i2 = c5404d06.f12502t;
                            if (i2 == 1) {
                                str = "still";
                            } else if (i2 == 2) {
                                str = "meglive";
                            } else if (i2 == 3) {
                                str = "flash";
                            }
                            if (c5404d06.m13573j()) {
                                C5404d0 c5404d07 = RunnableC5392c0.this.f12409a;
                                c5404d07.f12481i0.add(new LivenessFile(c5404d07.f12459V, "video", "", true));
                            }
                            List<LivenessFile> list = RunnableC5392c0.this.f12409a.f12481i0;
                            if (list != null && list.size() > 0) {
                                try {
                                    RunnableC5392c0.this.f12409a.mMegliveLocalFileInfo.setScrrenFilePath(C5527o2.m13244a(str, RunnableC5392c0.this.f12409a.f12481i0, file2.getAbsolutePath(), "screen_file.megvii", RunnableC5392c0.this.f12409a.getView().mo12999e()).getAbsolutePath());
                                    C5527o2.m13233b(RunnableC5392c0.this.f12409a.f12459V);
                                    new File(RunnableC5392c0.this.f12409a.f12459V).delete();
                                } catch (Throwable unused) {
                                    C5404d0 c5404d08 = RunnableC5392c0.this.f12409a;
                                    C5402d.f12429a = c5404d08.f12482j;
                                    String str2 = c5404d08.f12500s;
                                    int i3 = c5404d08.f12502t;
                                    if (!C5402d.f12432d) {
                                        try {
                                            JSONObject jSONObject2 = new JSONObject();
                                            jSONObject2.put("type", "track");
                                            jSONObject2.put("project", C5402d.f12429a);
                                            jSONObject2.put("event_id", UUID.randomUUID().toString());
                                            jSONObject2.put("time", System.currentTimeMillis());
                                            jSONObject2.put("event", "scrn_video_detect_fail");
                                            JSONObject jSONObject3 = new JSONObject();
                                            jSONObject3.put("liveness", i3);
                                            jSONObject3.put("biz_token", str2);
                                            jSONObject3.put("try_times", 0);
                                            int i4 = C5402d.f12431c + 1;
                                            C5402d.f12431c = i4;
                                            jSONObject3.put("index", i4);
                                            jSONObject2.put("properties", jSONObject3);
                                            C5402d.f12430b = "scrn_video_detect_fail";
                                            jSONObject = jSONObject2;
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        } else {
                            C5404d0 c5404d09 = RunnableC5392c0.this.f12409a;
                            C5402d.f12429a = c5404d09.f12482j;
                            C5399c3.m13606a(c5402d.m13599a("scrn_video_detect_fail", c5404d09.f12500s, c5404d09.f12502t));
                            c5404d0 = RunnableC5392c0.this.f12409a;
                            i = 5;
                            c5404d0.f12442E = i;
                        }
                    }
                } else if (RunnableC5392c0.this.f12409a.m13573j()) {
                    C5404d0 c5404d010 = RunnableC5392c0.this.f12409a;
                    C5402d.f12429a = c5404d010.f12482j;
                    String str3 = c5404d010.f12500s;
                    int i5 = c5404d010.f12502t;
                    if (!C5402d.f12432d) {
                        try {
                            JSONObject jSONObject4 = new JSONObject();
                            jSONObject4.put("type", "track");
                            jSONObject4.put("project", C5402d.f12429a);
                            jSONObject4.put("event_id", UUID.randomUUID().toString());
                            jSONObject4.put("time", System.currentTimeMillis());
                            jSONObject4.put("event", "scrn_video_detect_fail");
                            JSONObject jSONObject5 = new JSONObject();
                            jSONObject5.put("liveness", i5);
                            jSONObject5.put("biz_token", str3);
                            jSONObject5.put("try_times", 0);
                            int i6 = C5402d.f12431c + 1;
                            C5402d.f12431c = i6;
                            jSONObject5.put("index", i6);
                            jSONObject4.put("properties", jSONObject5);
                            C5402d.f12430b = "scrn_video_detect_fail";
                            jSONObject = jSONObject4;
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                    C5399c3.m13606a(jSONObject);
                    c5404d0 = RunnableC5392c0.this.f12409a;
                    i = 7;
                    c5404d0.f12442E = i;
                }
                C5404d0 c5404d011 = RunnableC5392c0.this.f12409a;
                if (c5404d011.f12442E != 0) {
                    if (c5404d011.getView() != null) {
                        RunnableC5392c0.this.f12409a.getView().mo13013a(RunnableC5392c0.this.f12409a.f12442E);
                        return;
                    }
                    return;
                }
                c5404d011.f12489m0 = true;
                RunnableC5392c0.this.f12409a.m13580d();
            }
        }
    }

    public RunnableC5392c0(C5404d0 c5404d0) {
        this.f12409a = c5404d0;
    }

    @Override // java.lang.Runnable
    public void run() {
        C5462h2.C5464b.f12721a.m13491a(new C5393a());
    }
}
