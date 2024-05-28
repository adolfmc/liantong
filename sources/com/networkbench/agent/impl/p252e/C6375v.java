package com.networkbench.agent.impl.p252e;

import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6633c;
import com.networkbench.agent.impl.util.C6645n;
import com.networkbench.agent.impl.util.C6653u;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.v */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6375v {

    /* renamed from: a */
    private static InterfaceC6393e f16061a = C6394f.m10150a();

    private C6375v() {
    }

    /* renamed from: a */
    public static Bitmap m10238a(View[] viewArr) {
        C6374u c6374u;
        List<C6374u> m10301a = C6358i.m10301a();
        Bitmap bitmap = null;
        if (m10301a.size() == 0) {
            return null;
        }
        int i = 0;
        while (true) {
            if (i >= m10301a.size()) {
                c6374u = null;
                break;
            }
            c6374u = m10301a.get(i);
            if (!m10240a(c6374u.m10250a().getClass().getName())) {
                i++;
            } else if (i != 0) {
                m10301a.set(0, c6374u);
                m10301a.set(i, m10301a.get(0));
            }
        }
        if (c6374u == null) {
            return null;
        }
        try {
            View decorView = ((Window) m10243a(c6374u.m10250a())).getDecorView();
            if (decorView.getWidth() * decorView.getHeight() * 4 < m10246a()) {
                bitmap = Bitmap.createBitmap(decorView.getWidth(), decorView.getHeight(), Bitmap.Config.ARGB_8888);
            } else {
                bitmap = Bitmap.createBitmap(decorView.getWidth(), decorView.getHeight(), Bitmap.Config.RGB_565);
            }
        } catch (IllegalArgumentException unused) {
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            f16061a.mo10121a("create bitmap error:", e);
        }
        m10239a(m10301a, bitmap, viewArr);
        return bitmap;
    }

    /* renamed from: a */
    private static boolean m10240a(String str) {
        if (Build.VERSION.SDK_INT >= 24) {
            return str.equals("android.widget.PopupWindow$PopupDecorView") || str.equals("com.android.internal.policy.DecorView");
        } else if (Build.VERSION.SDK_INT == 23) {
            return str.equals("com.android.internal.policy.PhoneWindow$DecorView") || str.equals("android.widget.PopupWindow$PopupDecorView");
        } else if (Build.VERSION.SDK_INT < 23) {
            return str.equals("com.android.internal.policy.impl.PhoneWindow$DecorView") || str.equals("android.widget.PopupWindow$PopupViewContainer");
        } else {
            return false;
        }
    }

    /* renamed from: a */
    public static Object m10243a(View view) throws Exception {
        if (Build.VERSION.SDK_INT < 24 || !view.getClass().getName().equals("com.android.internal.policy.DecorView")) {
            return C6645n.m8879a(view, "this$0");
        }
        return C6645n.m8879a(view, "mWindow");
    }

    /* renamed from: a */
    public static long m10246a() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) C6653u.m8714d().getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    /* renamed from: a */
    private static void m10239a(List<C6374u> list, Bitmap bitmap, View[] viewArr) {
        for (C6374u c6374u : list) {
            m10241a(c6374u, bitmap, viewArr);
        }
    }

    /* renamed from: a */
    private static void m10241a(C6374u c6374u, Bitmap bitmap, View[] viewArr) {
        if ((c6374u.m10247d().flags & 2) == 2) {
            new Canvas(bitmap).drawARGB((int) (c6374u.m10247d().dimAmount * 255.0f), 0, 0, 0);
        }
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(c6374u.m10248c(), c6374u.m10249b());
        int[] iArr = viewArr != null ? new int[viewArr.length] : null;
        if (viewArr != null) {
            for (int i = 0; i < viewArr.length; i++) {
                if (viewArr[i] != null) {
                    iArr[i] = viewArr[i].getVisibility();
                    viewArr[i].setVisibility(4);
                }
            }
        }
        c6374u.m10250a().draw(canvas);
        m10242a(c6374u.m10250a(), canvas);
        if (viewArr != null) {
            for (int i2 = 0; i2 < viewArr.length; i2++) {
                if (viewArr[i2] != null) {
                    viewArr[i2].setVisibility(iArr[i2]);
                }
            }
        }
    }

    /* renamed from: a */
    private static ArrayList<View> m10242a(View view, Canvas canvas) {
        if (!(view instanceof ViewGroup)) {
            ArrayList<View> arrayList = new ArrayList<>();
            arrayList.add(view);
            return arrayList;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(view);
            arrayList3.addAll(m10242a(childAt, canvas));
            if (Build.VERSION.SDK_INT >= 14 && (childAt instanceof TextureView)) {
                m10244a((TextureView) childAt, canvas);
            }
            if (childAt instanceof GLSurfaceView) {
                m10245a((GLSurfaceView) childAt, canvas);
            }
            arrayList2.addAll(arrayList3);
        }
        return arrayList2;
    }

    /* renamed from: a */
    private static void m10245a(GLSurfaceView gLSurfaceView, Canvas canvas) {
        int[] iArr;
        C6633c.m9119c("Drawing GLSurfaceView");
        if (gLSurfaceView.getWindowToken() != null) {
            gLSurfaceView.getLocationOnScreen(new int[2]);
            final int width = gLSurfaceView.getWidth();
            final int height = gLSurfaceView.getHeight();
            int[] iArr2 = new int[(height + 0) * width];
            final IntBuffer wrap = IntBuffer.wrap(iArr2);
            wrap.position(0);
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            gLSurfaceView.queueEvent(new Runnable() { // from class: com.networkbench.agent.impl.e.v.1
                @Override // java.lang.Runnable
                public void run() {
                    EGL10 egl10 = (EGL10) EGLContext.getEGL();
                    egl10.eglWaitGL();
                    GL10 gl10 = (GL10) egl10.eglGetCurrentContext().getGL();
                    gl10.glFinish();
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    gl10.glReadPixels(0, 0, width, height + 0, 6408, 5121, wrap);
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int[] iArr3 = new int[width * height];
            int i = 0;
            int i2 = 0;
            while (i < height) {
                for (int i3 = 0; i3 < width; i3++) {
                    int i4 = iArr2[(i * width) + i3];
                    iArr3[(((height - i2) - 1) * width) + i3] = (i4 & (-16711936)) | ((i4 << 16) & 16711680) | ((i4 >> 16) & 255);
                }
                i++;
                i2++;
            }
            Bitmap createBitmap = Bitmap.createBitmap(iArr3, width, height, Bitmap.Config.ARGB_8888);
            Paint paint = new Paint();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
            canvas.drawBitmap(createBitmap, iArr[0], iArr[1], paint);
            createBitmap.recycle();
        }
    }

    /* renamed from: a */
    private static void m10244a(TextureView textureView, Canvas canvas) {
        C6633c.m9119c("Drawing TextureView");
        int[] iArr = new int[2];
        textureView.getLocationOnScreen(iArr);
        Bitmap bitmap = textureView.getBitmap();
        if (bitmap != null) {
            Paint paint = new Paint();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
            canvas.drawBitmap(bitmap, iArr[0], iArr[1], paint);
            bitmap.recycle();
        }
    }
}
