package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wechat.utils.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WXLaunchMiniProgram {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: WXLaunchMiniProgram.java */
    /* renamed from: cn.sharesdk.wechat.utils.i$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class C1873b extends WechatResp {

        /* renamed from: a */
        public String f3284a;

        @Override // cn.sharesdk.wechat.utils.WechatResp
        /* renamed from: a */
        public final int mo21305a() {
            return 19;
        }

        public C1873b(Bundle bundle) {
            mo21304a(bundle);
        }

        @Override // cn.sharesdk.wechat.utils.WechatResp
        /* renamed from: a */
        public final void mo21304a(Bundle bundle) {
            super.mo21304a(bundle);
            this.f3284a = bundle.getString("_launch_wxminiprogram_ext_msg");
        }

        @Override // cn.sharesdk.wechat.utils.WechatResp
        /* renamed from: b */
        public final void mo21303b(Bundle bundle) {
            super.mo21303b(bundle);
            bundle.putString("_launch_wxminiprogram_ext_msg", this.f3284a);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: WXLaunchMiniProgram.java */
    /* renamed from: cn.sharesdk.wechat.utils.i$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class C1872a extends WechatReq {

        /* renamed from: a */
        public String f3280a;

        /* renamed from: b */
        public String f3281b = "";

        /* renamed from: c */
        public int f3282c = 0;

        /* renamed from: d */
        public String f3283d = "";

        @Override // cn.sharesdk.wechat.utils.WechatReq
        /* renamed from: a */
        public final int mo21237a() {
            return 19;
        }

        @Override // cn.sharesdk.wechat.utils.WechatReq
        /* renamed from: b */
        public final boolean mo21235b() {
            String str = this.f3280a;
            if (str == null || str.length() == 0 || this.f3280a.length() > 10240) {
                SSDKLog.m21740b().m21744a("checkArgs fail, userName is invalid", new Object[0]);
                return false;
            }
            int i = this.f3282c;
            if (i < 0 || i > 2) {
                SSDKLog.m21740b().m21744a("checkArgs fail", "miniprogram type should between MINIPTOGRAM_TYPE_RELEASE and MINIPROGRAM_TYPE_PREVIEW");
                return false;
            }
            return true;
        }

        @Override // cn.sharesdk.wechat.utils.WechatReq
        /* renamed from: b */
        public final void mo21234b(Bundle bundle) {
            super.mo21234b(bundle);
            bundle.putString("_launch_wxminiprogram_username", this.f3280a);
            bundle.putString("_launch_wxminiprogram_path", this.f3281b);
            bundle.putInt("_launch_wxminiprogram_type", this.f3282c);
            bundle.putString("_launch_wxminiprogram_extData", this.f3283d);
        }
    }
}
