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
/* renamed from: com.megvii.lv5.h0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5459h0 extends AbstractC5421e2 {

    /* renamed from: a */
    public final /* synthetic */ C5469i0 f12695a;

    public C5459h0(C5469i0 c5469i0) {
        this.f12695a = c5469i0;
    }

    @Override // com.megvii.lv5.AbstractC5421e2
    /* renamed from: a */
    public void mo12888a() {
    }

    @Override // com.megvii.lv5.AbstractC5421e2
    /* renamed from: a */
    public void mo12887a(File file) {
        C5469i0 c5469i0;
        int i;
        if (this.f12695a.f12772k == 14 || this.f12695a.f12772k == 17) {
            JSONObject jSONObject = null;
            if (this.f12695a.getView() != null && !TextUtils.isEmpty(this.f12695a.getView().mo12976e())) {
                C5469i0 c5469i02 = this.f12695a;
                C5402d.f12429a = c5469i02.f12768i;
                C5402d c5402d = C5402d.C5403a.f12436a;
                C5399c3.m13606a(c5402d.m13599a("scrn_video_generate", c5469i02.f12778n, c5469i02.f12779o));
                this.f12695a.f12767h0 = file.toString();
                C5469i0 c5469i03 = this.f12695a;
                if (c5469i03.f12767h0 != null) {
                    C5402d.f12429a = c5469i03.f12768i;
                    C5399c3.m13606a(c5402d.m13599a("scrn_video_detect", c5469i03.f12778n, c5469i03.f12779o));
                    C5469i0 c5469i04 = this.f12695a;
                    if (c5469i04.m13486a(c5469i04.f12767h0)) {
                        C5469i0 c5469i05 = this.f12695a;
                        C5402d.f12429a = c5469i05.f12768i;
                        C5399c3.m13606a(c5402d.m13599a("scrn_video_detect_pass", c5469i05.f12778n, c5469i05.f12779o));
                        System.currentTimeMillis();
                        File file2 = new File(this.f12695a.getView().getContext().getFilesDir(), "livenessFile");
                        System.currentTimeMillis();
                        String str = "";
                        C5469i0 c5469i06 = this.f12695a;
                        int i2 = c5469i06.f12779o;
                        if (i2 == 1) {
                            str = "still";
                        } else if (i2 == 2) {
                            str = "meglive";
                        } else if (i2 == 3) {
                            str = "flash";
                        }
                        if (c5469i06.m13470k()) {
                            C5469i0 c5469i07 = this.f12695a;
                            c5469i07.f12728C.add(new LivenessFile(c5469i07.f12767h0, "video", "", true));
                        }
                        List<LivenessFile> list = this.f12695a.f12728C;
                        if (list != null && list.size() > 0) {
                            try {
                                this.f12695a.mMegliveLocalFileInfo.setScrrenFilePath(C5527o2.m13244a(str, this.f12695a.f12728C, file2.getAbsolutePath(), "screen_file.megvii", this.f12695a.getView().mo12976e()).getAbsolutePath());
                                C5527o2.m13233b(this.f12695a.f12767h0);
                                new File(this.f12695a.f12767h0).delete();
                            } catch (Throwable unused) {
                                C5469i0 c5469i08 = this.f12695a;
                                C5402d.f12429a = c5469i08.f12768i;
                                String str2 = c5469i08.f12778n;
                                int i3 = c5469i08.f12779o;
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
                        C5469i0 c5469i09 = this.f12695a;
                        C5402d.f12429a = c5469i09.f12768i;
                        C5399c3.m13606a(c5402d.m13599a("scrn_video_detect_fail", c5469i09.f12778n, c5469i09.f12779o));
                        c5469i0 = this.f12695a;
                        i = 5;
                        c5469i0.f12788x = i;
                    }
                }
            } else if (this.f12695a.m13470k()) {
                C5469i0 c5469i010 = this.f12695a;
                C5402d.f12429a = c5469i010.f12768i;
                String str3 = c5469i010.f12778n;
                int i5 = c5469i010.f12779o;
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
                c5469i0 = this.f12695a;
                i = 7;
                c5469i0.f12788x = i;
            }
            C5469i0 c5469i011 = this.f12695a;
            if (c5469i011.f12788x != 0) {
                if (c5469i011.getView() != null) {
                    this.f12695a.getView().mo12987a(this.f12695a.f12788x);
                    return;
                }
                return;
            }
            c5469i011.f12742Q = true;
            this.f12695a.m13480d();
        }
    }
}
