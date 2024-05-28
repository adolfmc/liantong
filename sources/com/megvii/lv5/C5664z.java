package com.megvii.lv5;

import android.text.TextUtils;
import com.megvii.lv5.C5402d;
import com.megvii.lv5.sdk.result.LivenessFile;
import java.io.File;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.z */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5664z extends AbstractC5421e2 {

    /* renamed from: a */
    public final /* synthetic */ C5641x f13945a;

    public C5664z(C5641x c5641x) {
        this.f13945a = c5641x;
    }

    @Override // com.megvii.lv5.AbstractC5421e2
    /* renamed from: a */
    public void mo12888a() {
    }

    @Override // com.megvii.lv5.AbstractC5421e2
    /* renamed from: a */
    public void mo12887a(File file) {
        C5641x c5641x;
        if (this.f13945a.f13832a == 14 || this.f13945a.f13832a == 17) {
            C5641x c5641x2 = this.f13945a;
            C5402d.f12429a = c5641x2.f13854m;
            C5402d c5402d = C5402d.C5403a.f12436a;
            C5525o0 c5525o0 = c5641x2.f13853l;
            C5399c3.m13606a(c5402d.m13599a("scrn_video_generate", c5525o0.f13093b, c5525o0.f13092a));
            this.f13945a.f13860s = file.toString();
            if (!TextUtils.isEmpty(this.f13945a.f13860s)) {
                C5641x c5641x3 = this.f13945a;
                C5402d.f12429a = c5641x3.f13854m;
                C5525o0 c5525o02 = c5641x3.f13853l;
                C5399c3.m13606a(c5402d.m13599a("scrn_video_detect", c5525o02.f13093b, c5525o02.f13092a));
                C5641x c5641x4 = this.f13945a;
                int i = 7;
                if (c5641x4.m12932a(c5641x4.f13860s)) {
                    C5641x c5641x5 = this.f13945a;
                    C5402d.f12429a = c5641x5.f13854m;
                    C5525o0 c5525o03 = c5641x5.f13853l;
                    C5399c3.m13606a(c5402d.m13599a("scrn_video_detect_pass", c5525o03.f13093b, c5525o03.f13092a));
                    if (this.f13945a.m12915i()) {
                        C5641x c5641x6 = this.f13945a;
                        c5641x6.f13810E.add(new LivenessFile(c5641x6.f13860s, "video", "", true));
                    }
                    if (this.f13945a.getView() != null && !TextUtils.isEmpty(this.f13945a.getView().mo13123j())) {
                        File file2 = new File(this.f13945a.getView().getContext().getFilesDir(), "livenessFile");
                        C5641x c5641x7 = this.f13945a;
                        int i2 = c5641x7.f13853l.f13092a;
                        String str = "";
                        if (i2 == 1) {
                            str = "still";
                        } else if (i2 == 2) {
                            str = "meglive";
                        } else if (i2 == 3) {
                            str = "flash";
                        }
                        List<LivenessFile> list = c5641x7.f13810E;
                        if (list != null && list.size() > 0) {
                            try {
                                this.f13945a.mMegliveLocalFileInfo.setScrrenFilePath(C5527o2.m13244a(str, this.f13945a.f13810E, file2.getAbsolutePath(), "screen_file.megvii", this.f13945a.getView().mo13123j()).getAbsolutePath());
                                C5527o2.m13233b(this.f13945a.f13860s);
                                new File(this.f13945a.f13860s).delete();
                            } catch (Throwable unused) {
                                C5641x c5641x8 = this.f13945a;
                                C5402d.f12429a = c5641x8.f13854m;
                                C5525o0 c5525o04 = c5641x8.f13853l;
                                String str2 = c5525o04.f13093b;
                                int i3 = c5525o04.f13092a;
                                JSONObject jSONObject = null;
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
                                C5399c3.m13606a(jSONObject);
                                c5641x = this.f13945a;
                            }
                        }
                    } else if (this.f13945a.m12915i()) {
                        C5641x c5641x9 = this.f13945a;
                        C5402d.f12429a = c5641x9.f13854m;
                        C5525o0 c5525o05 = c5641x9.f13853l;
                        C5399c3.m13606a(c5402d.m13599a("scrn_video_detect_fail", c5525o05.f13093b, c5525o05.f13092a));
                        c5641x = this.f13945a;
                    }
                } else {
                    C5641x c5641x10 = this.f13945a;
                    C5402d.f12429a = c5641x10.f13854m;
                    C5525o0 c5525o06 = c5641x10.f13853l;
                    C5399c3.m13606a(c5402d.m13599a("scrn_video_detect_fail", c5525o06.f13093b, c5525o06.f13092a));
                    c5641x = this.f13945a;
                    i = 5;
                }
                c5641x.f13861t = i;
            }
            C5641x c5641x11 = this.f13945a;
            if (c5641x11.f13861t != 0) {
                if (c5641x11.getView() != null) {
                    this.f13945a.getView().mo13147a(this.f13945a.f13861t);
                    return;
                }
                return;
            }
            c5641x11.f13835b0 = true;
            this.f13945a.m12921d();
        }
    }
}
