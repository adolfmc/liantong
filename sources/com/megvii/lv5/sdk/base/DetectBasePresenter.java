package com.megvii.lv5.sdk.base;

import com.megvii.lv5.C5452g1;
import com.megvii.lv5.C5492l;
import com.megvii.lv5.C5527o2;
import com.megvii.lv5.sdk.base.BaseModel;
import com.megvii.lv5.sdk.base.BaseView;
import com.megvii.lv5.sdk.bean.MegliveLocalFileInfo;
import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DetectBasePresenter<V extends BaseView, M extends BaseModel> {
    public static final int DEFAULT_VALUE = -1;
    public static final int LIVENESS_FAILURE = 3003;
    public static final int LIVENESS_SUCCESS = 0;
    public static final int LIVENESS_TIMEOUT = 3002;
    private static final String TAG = "DetectBasePresenter";
    public C5492l mCameraManager;
    public BlockingQueue<C5452g1> mFrameDataQueue;
    public BlockingQueue<byte[]> mFrameDataQueueHD;
    public MegliveLocalFileInfo mMegliveLocalFileInfo;
    private M mModel;
    private V mView;
    public byte[] cameraData = null;
    private long waitChange = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.base.DetectBasePresenter$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class C5566a implements InvocationHandler {

        /* renamed from: a */
        public final /* synthetic */ BaseView f13281a;

        public C5566a(BaseView baseView) {
            this.f13281a = baseView;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (DetectBasePresenter.this.mView == null) {
                return null;
            }
            return method.invoke(this.f13281a, objArr);
        }
    }

    public boolean attach(V v) {
        this.mView = (V) Proxy.newProxyInstance(v.getClass().getClassLoader(), v.getClass().getInterfaces(), new C5566a(v));
        try {
            this.mModel = (M) ((Class) ((ParameterizedType) Objects.requireNonNull(getClass().getGenericSuperclass())).getActualTypeArguments()[1]).newInstance();
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean changeExposure(int i, boolean z) {
        C5492l c5492l = this.mCameraManager;
        if (c5492l != null && z) {
            try {
                int[] mo13265b = c5492l.f12846a.mo13265b();
                int i2 = mo13265b[0];
                int i3 = mo13265b[1];
                int i4 = mo13265b[2];
                if (i == 7) {
                    if (i2 > i4) {
                        long j = this.waitChange;
                        this.waitChange = 1 + j;
                        if (j % 5 == 0) {
                            this.mCameraManager.f12846a.mo13264b(i2 - 1);
                        }
                        return true;
                    }
                } else if (i == 6) {
                    if (i2 < i3) {
                        long j2 = this.waitChange;
                        this.waitChange = 1 + j2;
                        if (j2 % 5 == 0) {
                            this.mCameraManager.f12846a.mo13264b(i2 + 1);
                        }
                        return true;
                    }
                }
                this.waitChange = 0L;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void cleanFiles(int i) {
        try {
            File filesDir = getView().getContext().getFilesDir();
            if (i != 0) {
                C5527o2.m13247a(new File(filesDir, "livenessFile"));
            }
            C5527o2.m13247a(new File(filesDir, "megviiVideo"));
            C5527o2.m13247a(new File(filesDir, "flashImage"));
            C5527o2.m13247a(new File(filesDir, "hd_image"));
            C5527o2.m13247a(new File(filesDir, "megviiImage"));
            C5527o2.m13247a(new File(filesDir, "tmp_data"));
        } catch (Throwable unused) {
        }
    }

    public void closeCamera() {
        try {
            this.mCameraManager.f12846a.mo13277a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detach() {
        this.mView = null;
        this.mModel = null;
    }

    public int getCameraHeight() {
        if (this.mCameraManager != null) {
            String str = "flash getCameraHeight: " + this.mCameraManager.f12849d;
            return this.mCameraManager.f12849d;
        }
        return 0;
    }

    public int getCameraWidth() {
        if (this.mCameraManager != null) {
            String str = "flash cameraWidth: " + this.mCameraManager.f12848c;
            return this.mCameraManager.f12848c;
        }
        return 0;
    }

    public M getModel() {
        return this.mModel;
    }

    public V getView() {
        return this.mView;
    }

    public void init() {
        this.mCameraManager = new C5492l(this.mView.getActivity());
        this.mFrameDataQueue = new LinkedBlockingDeque(1);
        this.mFrameDataQueueHD = new LinkedBlockingDeque(1);
        this.mMegliveLocalFileInfo = new MegliveLocalFileInfo();
    }

    public boolean openCamera() {
        try {
            C5492l c5492l = this.mCameraManager;
            c5492l.f12846a.mo13266a(!c5492l.m13444b(), c5492l.f12853h.get().getApplicationContext(), c5492l.f12854i);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
