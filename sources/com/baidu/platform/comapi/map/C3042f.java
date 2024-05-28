package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;
import android.opengl.GLDebugHelper;
import android.opengl.GLException;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.baidu.platform.comapi.map.C3006ai;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.nio.IntBuffer;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3042f implements InterfaceSurfaceHolder$Callback2C3005ah {

    /* renamed from: c */
    private static final C3052j f7848c = new C3052j();

    /* renamed from: a */
    private WeakReference<SurfaceView> f7849a;

    /* renamed from: b */
    private int f7850b = 60;

    /* renamed from: d */
    private final WeakReference<C3042f> f7851d = new WeakReference<>(this);

    /* renamed from: e */
    private C3051i f7852e;

    /* renamed from: f */
    private InterfaceC3015aq f7853f;

    /* renamed from: g */
    private boolean f7854g;

    /* renamed from: h */
    private InterfaceC3047e f7855h;

    /* renamed from: i */
    private InterfaceC3048f f7856i;

    /* renamed from: j */
    private InterfaceC3049g f7857j;

    /* renamed from: k */
    private InterfaceC3053k f7858k;

    /* renamed from: l */
    private int f7859l;

    /* renamed from: m */
    private int f7860m;

    /* renamed from: n */
    private boolean f7861n;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.f$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    abstract class AbstractC3043a implements InterfaceC3047e {

        /* renamed from: a */
        protected int[] f7862a;

        public AbstractC3043a(int[] iArr) {
            this.f7862a = m17830a(iArr);
        }

        /* renamed from: a */
        private int[] m17830a(int[] iArr) {
            if (C3042f.this.f7860m == 2 || C3042f.this.f7860m == 3) {
                int length = iArr.length;
                int[] iArr2 = new int[length + 2];
                int i = length - 1;
                System.arraycopy(iArr, 0, iArr2, 0, i);
                iArr2[i] = 12352;
                if (C3042f.this.f7860m == 2) {
                    iArr2[length] = 4;
                } else {
                    iArr2[length] = 64;
                }
                iArr2[length + 1] = 12344;
                return iArr2;
            }
            return iArr;
        }

        @Override // com.baidu.platform.comapi.map.C3042f.InterfaceC3047e
        /* renamed from: a */
        public EGLConfig mo17827a(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (egl10.eglChooseConfig(eGLDisplay, this.f7862a, null, 0, iArr)) {
                int i = iArr[0];
                if (i > 0) {
                    EGLConfig[] eGLConfigArr = new EGLConfig[i];
                    if (egl10.eglChooseConfig(eGLDisplay, this.f7862a, eGLConfigArr, i, iArr)) {
                        EGLConfig mo17828a = mo17828a(egl10, eGLDisplay, eGLConfigArr);
                        if (mo17828a != null) {
                            return mo17828a;
                        }
                        throw new IllegalArgumentException("No config chosen");
                    }
                    throw new IllegalArgumentException("eglChooseConfig#2 failed");
                }
                throw new IllegalArgumentException("No configs match configSpec");
            }
            throw new IllegalArgumentException("eglChooseConfig failed");
        }

        /* renamed from: a */
        abstract EGLConfig mo17828a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.f$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C3044b extends AbstractC3043a {

        /* renamed from: c */
        protected int f7864c;

        /* renamed from: d */
        protected int f7865d;

        /* renamed from: e */
        protected int f7866e;

        /* renamed from: f */
        protected int f7867f;

        /* renamed from: g */
        protected int f7868g;

        /* renamed from: h */
        protected int f7869h;

        /* renamed from: i */
        protected int f7870i;

        /* renamed from: j */
        protected int f7871j;

        /* renamed from: l */
        private int[] f7873l;

        public C3044b(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            super(new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12338, i7, 12337, i8, 12344});
            this.f7873l = new int[1];
            this.f7864c = i;
            this.f7865d = i2;
            this.f7866e = i3;
            this.f7867f = i4;
            this.f7868g = i5;
            this.f7869h = i6;
            this.f7870i = i7;
            this.f7871j = i8;
        }

        /* renamed from: a */
        private int m17829a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.f7873l) ? this.f7873l[0] : i2;
        }

        @Override // com.baidu.platform.comapi.map.C3042f.AbstractC3043a
        /* renamed from: a */
        public EGLConfig mo17828a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            EGLConfig eGLConfig = null;
            for (EGLConfig eGLConfig2 : eGLConfigArr) {
                int m17829a = m17829a(egl10, eGLDisplay, eGLConfig2, 12325, 0);
                int m17829a2 = m17829a(egl10, eGLDisplay, eGLConfig2, 12326, 0);
                if (m17829a >= this.f7868g && m17829a2 >= this.f7869h) {
                    int m17829a3 = m17829a(egl10, eGLDisplay, eGLConfig2, 12324, 0);
                    int m17829a4 = m17829a(egl10, eGLDisplay, eGLConfig2, 12323, 0);
                    int m17829a5 = m17829a(egl10, eGLDisplay, eGLConfig2, 12322, 0);
                    int m17829a6 = m17829a(egl10, eGLDisplay, eGLConfig2, 12321, 0);
                    if (m17829a3 == this.f7864c && m17829a4 == this.f7865d && m17829a5 == this.f7866e && m17829a6 == this.f7867f) {
                        if (eGLConfig == null) {
                            eGLConfig = eGLConfig2;
                        }
                        if (m17829a(egl10, eGLDisplay, eGLConfig2, 12337, 0) == this.f7871j) {
                            return eGLConfig2;
                        }
                    }
                }
            }
            return eGLConfig;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.f$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C3045c implements InterfaceC3048f {

        /* renamed from: b */
        private int f7875b;

        private C3045c() {
            this.f7875b = 12440;
        }

        @Override // com.baidu.platform.comapi.map.C3042f.InterfaceC3048f
        /* renamed from: a */
        public EGLContext mo17826a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = {this.f7875b, C3042f.this.f7860m, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (C3042f.this.f7860m == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        @Override // com.baidu.platform.comapi.map.C3042f.InterfaceC3048f
        /* renamed from: a */
        public void mo17825a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                return;
            }
            Log.e("DefaultContextFactory", "display:" + eGLDisplay + " context: " + eGLContext);
            C3050h.m17819a("eglDestroyContex", egl10.eglGetError());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.f$d */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C3046d implements InterfaceC3049g {
        private C3046d() {
        }

        @Override // com.baidu.platform.comapi.map.C3042f.InterfaceC3049g
        /* renamed from: a */
        public EGLSurface mo17824a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            try {
                return egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (IllegalArgumentException e) {
                Log.e("GLRenderControl", "eglCreateWindowSurface", e);
                return null;
            }
        }

        @Override // com.baidu.platform.comapi.map.C3042f.InterfaceC3049g
        /* renamed from: a */
        public void mo17823a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.f$e */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC3047e {
        /* renamed from: a */
        EGLConfig mo17827a(EGL10 egl10, EGLDisplay eGLDisplay);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.f$f */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC3048f {
        /* renamed from: a */
        EGLContext mo17826a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        /* renamed from: a */
        void mo17825a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.f$g */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC3049g {
        /* renamed from: a */
        EGLSurface mo17824a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj);

        /* renamed from: a */
        void mo17823a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.f$h */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C3050h {

        /* renamed from: a */
        EGL10 f7876a;

        /* renamed from: b */
        EGLDisplay f7877b;

        /* renamed from: c */
        EGLSurface f7878c;

        /* renamed from: d */
        EGLConfig f7879d;

        /* renamed from: e */
        EGLContext f7880e;

        /* renamed from: f */
        private WeakReference<C3042f> f7881f;

        public C3050h(WeakReference<C3042f> weakReference) {
            this.f7881f = weakReference;
        }

        /* renamed from: a */
        private static String m17821a(int i) {
            switch (i) {
                case 12288:
                    return "EGL_SUCCESS";
                case 12289:
                    return "EGL_NOT_INITIALIZED";
                case 12290:
                    return "EGL_BAD_ACCESS";
                case 12291:
                    return "EGL_BAD_ALLOC";
                case 12292:
                    return "EGL_BAD_ATTRIBUTE";
                case 12293:
                    return "EGL_BAD_CONFIG";
                case 12294:
                    return "EGL_BAD_CONTEXT";
                case 12295:
                    return "EGL_BAD_CURRENT_SURFACE";
                case 12296:
                    return "EGL_BAD_DISPLAY";
                case 12297:
                    return "EGL_BAD_MATCH";
                case 12298:
                    return "EGL_BAD_NATIVE_PIXMAP";
                case 12299:
                    return "EGL_BAD_NATIVE_WINDOW";
                case 12300:
                    return "EGL_BAD_PARAMETER";
                case 12301:
                    return "EGL_BAD_SURFACE";
                case 12302:
                    return "EGL_CONTEXT_LOST";
                default:
                    return m17816b(i);
            }
        }

        /* renamed from: a */
        private void m17820a(String str) {
            m17819a(str, this.f7876a.eglGetError());
        }

        /* renamed from: a */
        public static void m17819a(String str, int i) {
            throw new RuntimeException(m17815b(str, i));
        }

        /* renamed from: a */
        public static void m17818a(String str, String str2, int i) {
            Log.w(str, m17815b(str2, i));
        }

        /* renamed from: b */
        private static String m17816b(int i) {
            return "0x" + Integer.toHexString(i);
        }

        /* renamed from: b */
        public static String m17815b(String str, int i) {
            return str + " failed: " + m17821a(i);
        }

        /* renamed from: g */
        private void m17810g() {
            EGLSurface eGLSurface = this.f7878c;
            if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                return;
            }
            this.f7876a.eglMakeCurrent(this.f7877b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            C3042f c3042f = this.f7881f.get();
            if (c3042f != null) {
                c3042f.f7857j.mo17823a(this.f7876a, this.f7877b, this.f7878c);
            }
            this.f7878c = null;
        }

        /* renamed from: a */
        public void m17822a() {
            this.f7876a = (EGL10) EGLContext.getEGL();
            this.f7877b = this.f7876a.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.f7877b == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (!this.f7876a.eglInitialize(this.f7877b, new int[2])) {
                throw new RuntimeException("eglInitialize failed");
            }
            C3042f c3042f = this.f7881f.get();
            if (c3042f == null) {
                this.f7879d = null;
                this.f7880e = null;
            } else {
                this.f7879d = c3042f.f7855h.mo17827a(this.f7876a, this.f7877b);
                this.f7880e = c3042f.f7856i.mo17826a(this.f7876a, this.f7877b, this.f7879d);
            }
            EGLContext eGLContext = this.f7880e;
            if (eGLContext == null || eGLContext == EGL10.EGL_NO_CONTEXT) {
                this.f7880e = null;
                m17820a("createContext");
            }
            this.f7878c = null;
        }

        /* renamed from: b */
        public boolean m17817b() {
            if (this.f7876a != null) {
                if (this.f7877b != null) {
                    if (this.f7879d != null) {
                        m17810g();
                        C3042f c3042f = this.f7881f.get();
                        this.f7878c = c3042f != null ? c3042f.f7857j.mo17824a(this.f7876a, this.f7877b, this.f7879d, c3042f.m17865a()) : null;
                        EGLSurface eGLSurface = this.f7878c;
                        if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                            if (this.f7876a.eglGetError() == 12299) {
                                Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                            }
                            return false;
                        }
                        EGL10 egl10 = this.f7876a;
                        EGLDisplay eGLDisplay = this.f7877b;
                        EGLSurface eGLSurface2 = this.f7878c;
                        if (egl10.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, this.f7880e)) {
                            return true;
                        }
                        m17818a("EGLHelper", "eglMakeCurrent", this.f7876a.eglGetError());
                        return false;
                    }
                    throw new RuntimeException("mEglConfig not initialized");
                }
                throw new RuntimeException("eglDisplay not initialized");
            }
            throw new RuntimeException("egl not initialized");
        }

        /* renamed from: c */
        GL m17814c() {
            GL gl = this.f7880e.getGL();
            C3042f c3042f = this.f7881f.get();
            if (c3042f != null) {
                if (c3042f.f7858k != null) {
                    gl = c3042f.f7858k.m17790a(gl);
                }
                if ((c3042f.f7859l & 3) != 0) {
                    return GLDebugHelper.wrap(gl, (c3042f.f7859l & 1) != 0 ? 1 : 0, (c3042f.f7859l & 2) != 0 ? new C3054l() : null);
                }
                return gl;
            }
            return gl;
        }

        /* renamed from: d */
        public int m17813d() {
            if (this.f7876a.eglSwapBuffers(this.f7877b, this.f7878c)) {
                return 12288;
            }
            return this.f7876a.eglGetError();
        }

        /* renamed from: e */
        public void m17812e() {
            m17810g();
        }

        /* renamed from: f */
        public void m17811f() {
            if (this.f7880e != null) {
                C3042f c3042f = this.f7881f.get();
                if (c3042f != null) {
                    c3042f.f7856i.mo17825a(this.f7876a, this.f7877b, this.f7880e);
                }
                this.f7880e = null;
            }
            EGLDisplay eGLDisplay = this.f7877b;
            if (eGLDisplay != null) {
                this.f7876a.eglTerminate(eGLDisplay);
                this.f7877b = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.f$i */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C3051i extends Thread {

        /* renamed from: a */
        private boolean f7882a;

        /* renamed from: b */
        private boolean f7883b;

        /* renamed from: c */
        private boolean f7884c;

        /* renamed from: d */
        private boolean f7885d;

        /* renamed from: e */
        private boolean f7886e;

        /* renamed from: f */
        private boolean f7887f;

        /* renamed from: g */
        private boolean f7888g;

        /* renamed from: h */
        private boolean f7889h;

        /* renamed from: i */
        private boolean f7890i;

        /* renamed from: j */
        private boolean f7891j;

        /* renamed from: k */
        private boolean f7892k;

        /* renamed from: q */
        private boolean f7898q;

        /* renamed from: u */
        private C3050h f7902u;

        /* renamed from: v */
        private WeakReference<C3042f> f7903v;

        /* renamed from: r */
        private ArrayList<Runnable> f7899r = new ArrayList<>();

        /* renamed from: s */
        private boolean f7900s = true;

        /* renamed from: t */
        private Runnable f7901t = null;

        /* renamed from: l */
        private int f7893l = 0;

        /* renamed from: m */
        private int f7894m = 0;

        /* renamed from: o */
        private boolean f7896o = true;

        /* renamed from: n */
        private int f7895n = 1;

        /* renamed from: p */
        private boolean f7897p = false;

        C3051i(WeakReference<C3042f> weakReference) {
            this.f7903v = weakReference;
            setPriority(10);
        }

        /* renamed from: i */
        private void m17796i() {
            if (this.f7890i) {
                this.f7890i = false;
                this.f7902u.m17812e();
            }
        }

        /* renamed from: j */
        private void m17795j() {
            if (this.f7889h) {
                this.f7902u.m17811f();
                this.f7889h = false;
                C3042f.f7848c.m17791b(this);
            }
        }

        /* renamed from: k */
        private void m17794k() throws InterruptedException {
            boolean z;
            boolean z2;
            boolean z3;
            Runnable runnable;
            int i;
            boolean z4;
            boolean z5;
            Runnable runnable2;
            boolean z6;
            boolean z7;
            C3051i c3051i = this;
            c3051i.f7902u = new C3050h(c3051i.f7903v);
            c3051i.f7889h = false;
            c3051i.f7890i = false;
            c3051i.f7897p = false;
            boolean z8 = false;
            boolean z9 = false;
            boolean z10 = false;
            boolean z11 = false;
            boolean z12 = false;
            boolean z13 = false;
            boolean z14 = false;
            int i2 = 0;
            int i3 = 0;
            boolean z15 = false;
            Runnable runnable3 = null;
            GL10 gl10 = null;
            while (true) {
                Runnable runnable4 = null;
                while (true) {
                    try {
                        synchronized (C3042f.f7848c) {
                            while (!c3051i.f7882a) {
                                if (c3051i.f7899r.isEmpty()) {
                                    if (c3051i.f7885d != c3051i.f7884c) {
                                        z5 = c3051i.f7884c;
                                        c3051i.f7885d = c3051i.f7884c;
                                        C3042f.f7848c.notifyAll();
                                    } else {
                                        z5 = false;
                                    }
                                    if (c3051i.f7892k) {
                                        m17796i();
                                        m17795j();
                                        c3051i.f7892k = false;
                                        z10 = true;
                                    }
                                    if (z8) {
                                        m17796i();
                                        m17795j();
                                        z8 = false;
                                    }
                                    if (z5 && c3051i.f7890i) {
                                        m17796i();
                                    }
                                    if (z5 && c3051i.f7889h) {
                                        C3042f c3042f = c3051i.f7903v.get();
                                        if (!(c3042f == null ? false : c3042f.f7861n)) {
                                            m17795j();
                                        }
                                    }
                                    if (!c3051i.f7886e && !c3051i.f7888g) {
                                        if (c3051i.f7890i) {
                                            m17796i();
                                        }
                                        c3051i.f7888g = true;
                                        c3051i.f7887f = false;
                                        C3042f.f7848c.notifyAll();
                                    }
                                    if (c3051i.f7886e && c3051i.f7888g) {
                                        c3051i.f7888g = false;
                                        C3042f.f7848c.notifyAll();
                                    }
                                    if (z9) {
                                        c3051i.f7897p = false;
                                        c3051i.f7898q = true;
                                        C3042f.f7848c.notifyAll();
                                        z9 = false;
                                    }
                                    if (c3051i.f7901t != null) {
                                        Runnable runnable5 = c3051i.f7901t;
                                        c3051i.f7901t = null;
                                        runnable2 = runnable5;
                                    } else {
                                        runnable2 = runnable3;
                                    }
                                    if (m17793l()) {
                                        if (!c3051i.f7889h) {
                                            if (z10) {
                                                z10 = false;
                                            } else {
                                                try {
                                                    c3051i.f7902u.m17822a();
                                                    c3051i.f7889h = true;
                                                    C3042f.f7848c.notifyAll();
                                                    z15 = true;
                                                } catch (RuntimeException e) {
                                                    C3042f.f7848c.m17791b(c3051i);
                                                    throw e;
                                                }
                                            }
                                        }
                                        if (!c3051i.f7889h || c3051i.f7890i) {
                                            z6 = z11;
                                        } else {
                                            c3051i.f7890i = true;
                                            z6 = true;
                                            z12 = true;
                                            z13 = true;
                                        }
                                        if (c3051i.f7890i) {
                                            if (c3051i.f7900s) {
                                                i2 = c3051i.f7893l;
                                                i3 = c3051i.f7894m;
                                                c3051i.f7897p = true;
                                                c3051i.f7900s = false;
                                                z6 = true;
                                                z7 = false;
                                                z13 = true;
                                            } else {
                                                z7 = false;
                                            }
                                            c3051i.f7896o = z7;
                                            C3042f.f7848c.notifyAll();
                                            if (c3051i.f7897p) {
                                                z11 = z6;
                                                runnable3 = runnable2;
                                                z14 = true;
                                            } else {
                                                z11 = z6;
                                                runnable3 = runnable2;
                                            }
                                        } else {
                                            z11 = z6;
                                        }
                                    } else if (runnable2 != null) {
                                        Log.w("GLRenderControl", "Warning, !readyToDraw() but waiting for draw finished! Early reporting draw finished.");
                                        runnable2.run();
                                        runnable3 = null;
                                        C3042f.f7848c.wait();
                                        c3051i = this;
                                    }
                                    runnable3 = runnable2;
                                    C3042f.f7848c.wait();
                                    c3051i = this;
                                } else {
                                    runnable4 = c3051i.f7899r.remove(0);
                                }
                            }
                            synchronized (C3042f.f7848c) {
                                m17796i();
                                m17795j();
                            }
                            return;
                        }
                        if (runnable4 != null) {
                            break;
                        }
                        if (z11) {
                            if (c3051i.f7902u.m17817b()) {
                                synchronized (C3042f.f7848c) {
                                    c3051i.f7891j = true;
                                    C3042f.f7848c.notifyAll();
                                }
                                z11 = false;
                            } else {
                                synchronized (C3042f.f7848c) {
                                    c3051i.f7891j = true;
                                    c3051i.f7887f = true;
                                    C3042f.f7848c.notifyAll();
                                }
                            }
                        }
                        if (z12) {
                            gl10 = (GL10) c3051i.f7902u.m17814c();
                            z12 = false;
                        }
                        if (z15) {
                            C3042f c3042f2 = c3051i.f7903v.get();
                            if (c3042f2 != null) {
                                z = z8;
                                z2 = z9;
                                z3 = false;
                                runnable = null;
                                c3042f2.f7853f.mo17731a(null, c3042f2.m17850c(), c3042f2.m17847d(), 0);
                                Log.d("GLRenderControl", "mRenderer.onSurfaceCreated");
                            } else {
                                z = z8;
                                z2 = z9;
                                z3 = false;
                                runnable = null;
                            }
                            z15 = z3;
                        } else {
                            z = z8;
                            z2 = z9;
                            z3 = false;
                            runnable = null;
                        }
                        if (z13) {
                            C3042f c3042f3 = c3051i.f7903v.get();
                            if (c3042f3 != null) {
                                c3042f3.f7853f.mo17733a(i2, i3);
                                Log.d("GLRenderControl", "mRenderer.onSurfaceChanged");
                            }
                            z13 = z3;
                        }
                        long currentTimeMillis = System.currentTimeMillis();
                        C3042f c3042f4 = c3051i.f7903v.get();
                        if (c3042f4 != null) {
                            c3042f4.f7853f.mo17724a(gl10);
                            if (runnable3 != null) {
                                runnable3.run();
                                runnable3 = runnable;
                            }
                            i = c3042f4.mo17844e();
                        } else {
                            i = 60;
                        }
                        int m17813d = c3051i.f7902u.m17813d();
                        if (m17813d == 12288) {
                            z4 = true;
                        } else if (m17813d != 12302) {
                            C3050h.m17818a("GLThread", "eglSwapBuffers", m17813d);
                            synchronized (C3042f.f7848c) {
                                z4 = true;
                                c3051i.f7887f = true;
                                C3042f.f7848c.notifyAll();
                            }
                        } else {
                            z4 = true;
                            z = true;
                        }
                        if (z14) {
                            z9 = z4;
                            z14 = false;
                        } else {
                            z9 = z2;
                        }
                        long currentTimeMillis2 = System.currentTimeMillis();
                        if (i < 60 && i > 0) {
                            long j = (1000 / i) - (currentTimeMillis2 - currentTimeMillis);
                            if (j > 1) {
                                synchronized (C3042f.f7848c) {
                                    C3042f.f7848c.wait(j);
                                }
                            }
                        }
                        z8 = z;
                        c3051i = this;
                    } catch (Throwable th) {
                        synchronized (C3042f.f7848c) {
                            m17796i();
                            m17795j();
                            throw th;
                        }
                    }
                }
                runnable4.run();
            }
        }

        /* renamed from: l */
        private boolean m17793l() {
            return !this.f7885d && this.f7886e && !this.f7887f && this.f7893l > 0 && this.f7894m > 0 && (this.f7896o || this.f7895n == 1);
        }

        /* renamed from: a */
        public void m17808a(int i) {
            if (i < 0 || i > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (C3042f.f7848c) {
                this.f7895n = i;
                C3042f.f7848c.notifyAll();
            }
        }

        /* renamed from: a */
        public void m17807a(int i, int i2) {
            synchronized (C3042f.f7848c) {
                this.f7893l = i;
                this.f7894m = i2;
                this.f7900s = true;
                this.f7896o = true;
                this.f7898q = false;
                if (Thread.currentThread() == this) {
                    return;
                }
                C3042f.f7848c.notifyAll();
                while (!this.f7883b && !this.f7885d && !this.f7898q && m17809a()) {
                    try {
                        C3042f.f7848c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: a */
        public void m17805a(Runnable runnable) {
            synchronized (C3042f.f7848c) {
                if (Thread.currentThread() == this) {
                    return;
                }
                this.f7897p = true;
                this.f7896o = true;
                this.f7898q = false;
                this.f7901t = runnable;
                C3042f.f7848c.notifyAll();
            }
        }

        /* renamed from: a */
        public boolean m17809a() {
            return this.f7889h && this.f7890i && m17793l();
        }

        /* renamed from: b */
        public int m17804b() {
            int i;
            synchronized (C3042f.f7848c) {
                i = this.f7895n;
            }
            return i;
        }

        /* renamed from: b */
        public void m17803b(Runnable runnable) {
            if (runnable == null) {
                throw new IllegalArgumentException("r must not be null");
            }
            synchronized (C3042f.f7848c) {
                this.f7899r.add(runnable);
                C3042f.f7848c.notifyAll();
            }
        }

        /* renamed from: c */
        public void m17802c() {
            synchronized (C3042f.f7848c) {
                this.f7896o = true;
                C3042f.f7848c.notifyAll();
            }
        }

        /* renamed from: d */
        public void m17801d() {
            synchronized (C3042f.f7848c) {
                this.f7886e = true;
                this.f7891j = false;
                C3042f.f7848c.notifyAll();
                while (this.f7888g && !this.f7891j && !this.f7883b) {
                    try {
                        C3042f.f7848c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: e */
        public void m17800e() {
            synchronized (C3042f.f7848c) {
                this.f7886e = false;
                C3042f.f7848c.notifyAll();
                while (!this.f7888g && !this.f7883b) {
                    try {
                        C3042f.f7848c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: f */
        public void m17799f() {
            synchronized (C3042f.f7848c) {
                this.f7884c = true;
                C3042f.f7848c.notifyAll();
                while (!this.f7883b && !this.f7885d) {
                    try {
                        C3042f.f7848c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: g */
        public void m17798g() {
            synchronized (C3042f.f7848c) {
                this.f7884c = false;
                this.f7896o = true;
                this.f7898q = false;
                C3042f.f7848c.notifyAll();
                while (!this.f7883b && this.f7885d && !this.f7898q) {
                    try {
                        C3042f.f7848c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: h */
        public void m17797h() {
            synchronized (C3042f.f7848c) {
                this.f7882a = true;
                C3042f.f7848c.notifyAll();
                while (!this.f7883b) {
                    try {
                        C3042f.f7848c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("GLThread " + getId());
            try {
                m17794k();
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                C3042f.f7848c.m17792a(this);
                throw th;
            }
            C3042f.f7848c.m17792a(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.f$j */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C3052j {

        /* renamed from: a */
        private static String f7904a = "GLThreadManager";

        private C3052j() {
        }

        /* renamed from: a */
        public synchronized void m17792a(C3051i c3051i) {
            c3051i.f7883b = true;
            notifyAll();
        }

        /* renamed from: b */
        public void m17791b(C3051i c3051i) {
            notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.f$k */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC3053k {
        /* renamed from: a */
        GL m17790a(GL gl);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.f$l */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C3054l extends Writer {

        /* renamed from: a */
        private StringBuilder f7905a = new StringBuilder();

        C3054l() {
        }

        /* renamed from: a */
        private void m17789a() {
            if (this.f7905a.length() > 0) {
                Log.v("GLSurfaceView26", this.f7905a.toString());
                StringBuilder sb = this.f7905a;
                sb.delete(0, sb.length());
            }
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            m17789a();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            m17789a();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == '\n') {
                    m17789a();
                } else {
                    this.f7905a.append(c);
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.f$m */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C3055m extends C3044b {
        public C3055m(boolean z) {
            super(8, 8, 8, 0, z ? 16 : 0, 0, 1, 4);
        }
    }

    public C3042f(SurfaceView surfaceView) {
        this.f7849a = new WeakReference<>(surfaceView);
    }

    /* renamed from: a */
    private Bitmap m17861a(int i, int i2, int i3, int i4, GL10 gl10, Bitmap.Config config) {
        int i5 = i3 * i4;
        int[] iArr = new int[i5];
        int[] iArr2 = new int[i5];
        IntBuffer wrap = IntBuffer.wrap(iArr);
        wrap.position(0);
        try {
            gl10.glReadPixels(i, i2, i3, i4, 6408, 5121, wrap);
            for (int i6 = 0; i6 < i4; i6++) {
                int i7 = i6 * i3;
                int i8 = ((i4 - i6) - 1) * i3;
                for (int i9 = 0; i9 < i3; i9++) {
                    int i10 = iArr[i7 + i9];
                    iArr2[i8 + i9] = (i10 & (-16711936)) | ((i10 << 16) & 16711680) | ((i10 >> 16) & 255);
                }
            }
            return config == null ? Bitmap.createBitmap(iArr2, i3, i4, Bitmap.Config.ARGB_8888) : Bitmap.createBitmap(iArr2, i3, i4, config);
        } catch (GLException unused) {
            return null;
        } catch (OutOfMemoryError e) {
            Log.e("OutOfMemoryError", " createBitmap cause OutOfMemoryError : " + e.getMessage());
            return null;
        }
    }

    /* renamed from: n */
    private void m17831n() {
        if (this.f7852e != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: a */
    public Bitmap mo17862a(int i, int i2, int i3, int i4, Object obj, Bitmap.Config config) {
        return m17861a(i, i2, i3, i4, (GL10) obj, config);
    }

    /* renamed from: a */
    public SurfaceHolder m17865a() {
        SurfaceView surfaceView = this.f7849a.get();
        if (surfaceView != null) {
            return surfaceView.getHolder();
        }
        return null;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: a */
    public void mo17864a(int i) {
        if (i <= 0) {
            return;
        }
        if (i > 60) {
            i = 60;
        }
        this.f7850b = i;
    }

    /* renamed from: a */
    public void m17863a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        m17859a(new C3044b(i, i2, i3, i4, i5, i6, i7, i8));
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: a */
    public void mo17860a(InterfaceC3015aq interfaceC3015aq) {
        m17831n();
        if (this.f7855h == null) {
            this.f7855h = new C3055m(true);
        }
        if (this.f7856i == null) {
            this.f7856i = new C3045c();
        }
        if (this.f7857j == null) {
            this.f7857j = new C3046d();
        }
        this.f7853f = interfaceC3015aq;
        this.f7852e = new C3051i(this.f7851d);
        this.f7852e.start();
    }

    /* renamed from: a */
    public void m17859a(InterfaceC3047e interfaceC3047e) {
        m17831n();
        this.f7855h = interfaceC3047e;
    }

    /* renamed from: a */
    public void m17858a(InterfaceC3048f interfaceC3048f) {
        m17831n();
        this.f7856i = interfaceC3048f;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: a */
    public void mo17856a(Runnable runnable) {
        this.f7852e.m17803b(runnable);
    }

    /* renamed from: a */
    public void m17855a(boolean z) {
        this.f7861n = z;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: b */
    public C3006ai.EnumC3007a mo17854b() {
        return C3006ai.EnumC3007a.OPENGL_ES;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: b */
    public void mo17853b(int i) {
        this.f7859l = i;
    }

    /* renamed from: b */
    public void m17851b(boolean z) {
        m17859a(new C3055m(z));
    }

    /* renamed from: c */
    public int m17850c() {
        SurfaceView surfaceView = this.f7849a.get();
        if (surfaceView != null) {
            return surfaceView.getWidth();
        }
        return 0;
    }

    /* renamed from: c */
    public void m17849c(int i) {
        m17831n();
        this.f7860m = i;
    }

    /* renamed from: d */
    public int m17847d() {
        SurfaceView surfaceView = this.f7849a.get();
        if (surfaceView != null) {
            return surfaceView.getHeight();
        }
        return 0;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: d */
    public void mo17846d(int i) {
        this.f7852e.m17808a(i);
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: e */
    public int mo17844e() {
        return this.f7850b;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: f */
    public int mo17842f() {
        return this.f7859l;
    }

    protected void finalize() throws Throwable {
        try {
            if (this.f7852e != null) {
                this.f7852e.m17797h();
            }
        } finally {
            super.finalize();
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: g */
    public int mo17840g() {
        return this.f7852e.m17804b();
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: h */
    public void mo17838h() {
        this.f7852e.m17802c();
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: i */
    public void mo17836i() {
        this.f7852e.m17799f();
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: j */
    public void mo17835j() {
        this.f7852e.m17798g();
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: k */
    public void mo17834k() {
        if (this.f7854g && this.f7853f != null) {
            C3051i c3051i = this.f7852e;
            int m17804b = c3051i != null ? c3051i.m17804b() : 1;
            this.f7852e = new C3051i(this.f7851d);
            if (m17804b != 1) {
                this.f7852e.m17808a(m17804b);
            }
            this.f7852e.start();
        }
        this.f7854g = false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: l */
    public void mo17833l() {
        C3051i c3051i = this.f7852e;
        if (c3051i != null) {
            c3051i.m17797h();
        }
        this.f7854g = true;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.f7852e.m17807a(i2, i3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.f7852e.m17801d();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.f7852e.m17800e();
    }

    @Override // android.view.SurfaceHolder.Callback2
    @Deprecated
    public void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }

    @Override // android.view.SurfaceHolder.Callback2
    public void surfaceRedrawNeededAsync(SurfaceHolder surfaceHolder, Runnable runnable) {
        C3051i c3051i = this.f7852e;
        if (c3051i != null) {
            c3051i.m17805a(runnable);
        }
    }
}
