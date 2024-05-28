package com.bytedance.p170dr;

import android.content.Context;
import android.text.TextUtils;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.supplier.IIdentifierListener;
import com.bytedance.applog.C3578e3;
import com.bytedance.applog.C3630m3;
import com.bytedance.applog.InterfaceC3645n3;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.bytedance.dr.VivoIdentifier */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class VivoIdentifier {
    public String oaid;
    public String resultCode;
    public final AtomicBoolean mSecondCallGetDeviceIds = new AtomicBoolean();
    public volatile boolean mProloading = false;
    public final Object mLock = new Object();
    public IIdentifierListener listener = new C37541();

    /* renamed from: com.bytedance.dr.VivoIdentifier$1 */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C37541 implements IIdentifierListener {
        public C37541() {
        }

        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException
            */
        public void OnSupport(boolean r0, com.bun.supplier.IdSupplier r1) {
            /*
                r-1 = this;
                r1.<init>()
                java.util.concurrent.atomic.AtomicBoolean r0 = new java.util.concurrent.atomic.AtomicBoolean
                r0.<init>()
                r1.mSecondCallGetDeviceIds = r0
                r0 = 0
                r1.mProloading = r0
                java.lang.Object r0 = new java.lang.Object
                r0.<init>()
                r1.mLock = r0
                com.bytedance.dr.VivoIdentifier$1 r0 = new com.bytedance.dr.VivoIdentifier$1
                r0.<init>()
                r1.listener = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bytedance.p170dr.VivoIdentifier.C37541.OnSupport(boolean, com.bun.supplier.IdSupplier):void");
        }
    }

    private int callFromReflect(Context context) {
        return MdidSdkHelper.InitSdk(context, true, this.listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setupOaid(String str) {
        String str2 = C3630m3.f8586j;
        C3578e3.m17305a(str2, "OaidMiit#setupOaid id=" + str + ", oaid=" + this.oaid, null);
        if (!TextUtils.isEmpty(str) && !str.equals(this.oaid)) {
            this.oaid = str;
        }
    }

    public InterfaceC3645n3.C3646a getOaid(Context context) {
        synchronized (this.mLock) {
            if (this.mProloading) {
                this.mLock.wait(10000L);
            }
        }
        InterfaceC3645n3.C3646a c3646a = new InterfaceC3645n3.C3646a();
        c3646a.f8617a = this.oaid;
        return c3646a;
    }

    public void preloadOaid(Context context) {
        synchronized (this.mLock) {
            this.mProloading = true;
            int callFromReflect = callFromReflect(context);
            if (callFromReflect == 1008612) {
                C3578e3.m17305a(C3630m3.f8586j, "OaidMiit#getDeviceIds 不支持的设备", null);
                this.resultCode = "不支持的设备";
            } else if (callFromReflect == 1008613) {
                C3578e3.m17305a(C3630m3.f8586j, "OaidMiit#getDeviceIds 加载配置文件出错", null);
                this.resultCode = "加载配置文件出错";
            } else if (callFromReflect == 1008611) {
                C3578e3.m17305a(C3630m3.f8586j, "OaidMiit#getDeviceIds 不支持的设备厂商", null);
                this.resultCode = "不支持的设备厂商";
            } else if (callFromReflect == 1008614) {
                C3578e3.m17305a(C3630m3.f8586j, "OaidMiit#getDeviceIds 获取接口是异步的，结果会在回调中返回，回调执行的回调可能在工作线程", null);
                if (this.mSecondCallGetDeviceIds.compareAndSet(false, true)) {
                    preloadOaid(context);
                } else {
                    this.resultCode = "结果会在回调中返回";
                }
            } else if (callFromReflect == 1008615) {
                C3578e3.m17305a(C3630m3.f8586j, "OaidMiit#getDeviceIds 反射调用出错", null);
                this.resultCode = "反射调用出错";
            } else if (callFromReflect == 0) {
                C3578e3.m17305a(C3630m3.f8586j, "OaidMiit#getDeviceIds 正确", null);
                this.resultCode = "正确";
            } else {
                this.resultCode = "未知 resultCode=" + callFromReflect;
                String str = C3630m3.f8586j;
                C3578e3.m17305a(str, "OaidMiit#getDeviceIds 未知 resultCode=" + callFromReflect, null);
            }
        }
    }
}
