package com.baidu.platform.comapi.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.opengl.GLDebugHelper;
import android.opengl.GLException;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import com.baidu.platform.comapi.util.C3095g;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.nio.IntBuffer;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TextureView$SurfaceTextureListenerC3057h extends TextureView implements TextureView.SurfaceTextureListener {
    public static final int DEBUG_CHECK_GL_ERROR = 1;
    public static final int DEBUG_LOG_GL_CALLS = 2;
    public static final int RENDERMODE_CONTINUOUSLY = 1;
    public static final int RENDERMODE_WHEN_DIRTY = 0;

    /* renamed from: c */
    private static final C3064g f7907c = new C3064g(null);

    /* renamed from: a */
    private int f7908a;

    /* renamed from: b */
    private final View.OnLayoutChangeListener f7909b;

    /* renamed from: d */
    private final WeakReference<TextureView$SurfaceTextureListenerC3057h> f7910d;

    /* renamed from: e */
    private C3063f f7911e;

    /* renamed from: f */
    private InterfaceC3015aq f7912f;

    /* renamed from: g */
    private boolean f7913g;

    /* renamed from: h */
    private GLSurfaceView.EGLConfigChooser f7914h;

    /* renamed from: i */
    private GLSurfaceView.EGLContextFactory f7915i;

    /* renamed from: j */
    private GLSurfaceView.EGLWindowSurfaceFactory f7916j;

    /* renamed from: k */
    private GLSurfaceView.GLWrapper f7917k;

    /* renamed from: l */
    private int f7918l;

    /* renamed from: m */
    private int f7919m;

    /* renamed from: n */
    private boolean f7920n;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.h$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    abstract class AbstractC3058a implements GLSurfaceView.EGLConfigChooser {

        /* renamed from: a */
        protected int[] f7921a;

        public AbstractC3058a(int[] iArr) {
            this.f7921a = m17776a(iArr);
        }

        /* renamed from: a */
        private int[] m17776a(int[] iArr) {
            if (TextureView$SurfaceTextureListenerC3057h.this.f7919m != 2) {
                return iArr;
            }
            int length = iArr.length;
            int[] iArr2 = new int[length + 2];
            int i = length - 1;
            System.arraycopy(iArr, 0, iArr2, 0, i);
            iArr2[i] = 12352;
            iArr2[length] = 4;
            iArr2[length + 1] = 12344;
            return iArr2;
        }

        /* renamed from: a */
        abstract EGLConfig mo17774a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

        @Override // android.opengl.GLSurfaceView.EGLConfigChooser
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (egl10.eglChooseConfig(eGLDisplay, this.f7921a, null, 0, iArr)) {
                int i = iArr[0];
                if (i > 0) {
                    EGLConfig[] eGLConfigArr = new EGLConfig[i];
                    if (egl10.eglChooseConfig(eGLDisplay, this.f7921a, eGLConfigArr, i, iArr)) {
                        EGLConfig mo17774a = mo17774a(egl10, eGLDisplay, eGLConfigArr);
                        if (mo17774a != null) {
                            return mo17774a;
                        }
                        throw new IllegalArgumentException("No config chosen");
                    }
                    throw new IllegalArgumentException("eglChooseConfig#2 failed");
                }
                throw new IllegalArgumentException("No configs match configSpec");
            }
            throw new IllegalArgumentException("eglChooseConfig failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.h$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C3059b extends AbstractC3058a {

        /* renamed from: c */
        protected int f7923c;

        /* renamed from: d */
        protected int f7924d;

        /* renamed from: e */
        protected int f7925e;

        /* renamed from: f */
        protected int f7926f;

        /* renamed from: g */
        protected int f7927g;

        /* renamed from: h */
        protected int f7928h;

        /* renamed from: j */
        private int[] f7930j;

        public C3059b(int i, int i2, int i3, int i4, int i5, int i6) {
            super(new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344});
            this.f7930j = new int[1];
            this.f7923c = i;
            this.f7924d = i2;
            this.f7925e = i3;
            this.f7926f = i4;
            this.f7927g = i5;
            this.f7928h = i6;
        }

        /* renamed from: a */
        private int m17775a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.f7930j) ? this.f7930j[0] : i2;
        }

        @Override // com.baidu.platform.comapi.map.TextureView$SurfaceTextureListenerC3057h.AbstractC3058a
        /* renamed from: a */
        public EGLConfig mo17774a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int m17775a = m17775a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int m17775a2 = m17775a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (m17775a >= this.f7927g && m17775a2 >= this.f7928h) {
                    int m17775a3 = m17775a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int m17775a4 = m17775a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int m17775a5 = m17775a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    int m17775a6 = m17775a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (m17775a3 == this.f7923c && m17775a4 == this.f7924d && m17775a5 == this.f7925e && m17775a6 == this.f7926f) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.h$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C3060c implements GLSurfaceView.EGLContextFactory {

        /* renamed from: b */
        private int f7932b;

        private C3060c() {
            this.f7932b = 12440;
        }

        /* synthetic */ C3060c(TextureView$SurfaceTextureListenerC3057h textureView$SurfaceTextureListenerC3057h, View$OnLayoutChangeListenerC3067i view$OnLayoutChangeListenerC3067i) {
            this();
        }

        @Override // android.opengl.GLSurfaceView.EGLContextFactory
        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = {this.f7932b, TextureView$SurfaceTextureListenerC3057h.this.f7919m, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (TextureView$SurfaceTextureListenerC3057h.this.f7919m == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        @Override // android.opengl.GLSurfaceView.EGLContextFactory
        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                return;
            }
            Log.i("DefaultContextFactory", "tid=" + Thread.currentThread().getId());
            C3062e.m17771a("eglDestroyContex", egl10.eglGetError());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.h$d */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C3061d implements GLSurfaceView.EGLWindowSurfaceFactory {
        private C3061d() {
        }

        /* synthetic */ C3061d(View$OnLayoutChangeListenerC3067i view$OnLayoutChangeListenerC3067i) {
            this();
        }

        @Override // android.opengl.GLSurfaceView.EGLWindowSurfaceFactory
        public EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            try {
                return egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (IllegalArgumentException e) {
                Log.e("GLTextureView", "eglCreateWindowSurface", e);
                return null;
            }
        }

        @Override // android.opengl.GLSurfaceView.EGLWindowSurfaceFactory
        public void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.h$e */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C3062e {

        /* renamed from: a */
        EGL10 f7933a;

        /* renamed from: b */
        EGLDisplay f7934b;

        /* renamed from: c */
        EGLSurface f7935c;

        /* renamed from: d */
        EGLConfig f7936d;

        /* renamed from: e */
        EGLContext f7937e;

        /* renamed from: f */
        private WeakReference<TextureView$SurfaceTextureListenerC3057h> f7938f;

        public C3062e(WeakReference<TextureView$SurfaceTextureListenerC3057h> weakReference) {
            this.f7938f = weakReference;
        }

        /* renamed from: a */
        private void m17772a(String str) {
            m17771a(str, this.f7933a.eglGetError());
        }

        /* renamed from: a */
        public static void m17771a(String str, int i) {
            String m17768b = m17768b(str, i);
            Log.e("EglHelper", "throwEglException tid=" + Thread.currentThread().getId() + " " + m17768b);
            throw new RuntimeException(m17768b);
        }

        /* renamed from: a */
        public static void m17770a(String str, String str2, int i) {
        }

        /* renamed from: b */
        public static String m17768b(String str, int i) {
            return str + " EGL failed code: " + i;
        }

        /* renamed from: g */
        private void m17763g() {
            EGLSurface eGLSurface = this.f7935c;
            if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                return;
            }
            this.f7933a.eglMakeCurrent(this.f7934b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            TextureView$SurfaceTextureListenerC3057h textureView$SurfaceTextureListenerC3057h = this.f7938f.get();
            if (textureView$SurfaceTextureListenerC3057h != null) {
                textureView$SurfaceTextureListenerC3057h.f7916j.destroySurface(this.f7933a, this.f7934b, this.f7935c);
            }
            this.f7935c = null;
        }

        /* renamed from: a */
        public void m17773a() {
            Log.w("EglHelper", "start() tid=" + Thread.currentThread().getId());
            this.f7933a = (EGL10) EGLContext.getEGL();
            this.f7934b = this.f7933a.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.f7934b == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (!this.f7933a.eglInitialize(this.f7934b, new int[2])) {
                throw new RuntimeException("eglInitialize failed");
            }
            TextureView$SurfaceTextureListenerC3057h textureView$SurfaceTextureListenerC3057h = this.f7938f.get();
            if (textureView$SurfaceTextureListenerC3057h == null) {
                this.f7936d = null;
                this.f7937e = null;
            } else {
                this.f7936d = textureView$SurfaceTextureListenerC3057h.f7914h.chooseConfig(this.f7933a, this.f7934b);
                this.f7937e = textureView$SurfaceTextureListenerC3057h.f7915i.createContext(this.f7933a, this.f7934b, this.f7936d);
            }
            EGLContext eGLContext = this.f7937e;
            if (eGLContext == null || eGLContext == EGL10.EGL_NO_CONTEXT) {
                this.f7937e = null;
                m17772a("createContext");
            }
            Log.w("EglHelper", "createContext " + this.f7937e + " tid=" + Thread.currentThread().getId());
            this.f7935c = null;
        }

        /* renamed from: b */
        public boolean m17769b() {
            Log.w("EglHelper", "createSurface()  tid=" + Thread.currentThread().getId());
            if (this.f7933a != null) {
                if (this.f7934b != null) {
                    if (this.f7936d != null) {
                        m17763g();
                        TextureView$SurfaceTextureListenerC3057h textureView$SurfaceTextureListenerC3057h = this.f7938f.get();
                        this.f7935c = textureView$SurfaceTextureListenerC3057h != null ? textureView$SurfaceTextureListenerC3057h.f7916j.createWindowSurface(this.f7933a, this.f7934b, this.f7936d, textureView$SurfaceTextureListenerC3057h.getSurfaceTexture()) : null;
                        EGLSurface eGLSurface = this.f7935c;
                        if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                            if (this.f7933a.eglGetError() == 12299) {
                                Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                            }
                            return false;
                        }
                        EGL10 egl10 = this.f7933a;
                        EGLDisplay eGLDisplay = this.f7934b;
                        EGLSurface eGLSurface2 = this.f7935c;
                        if (egl10.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, this.f7937e)) {
                            return true;
                        }
                        m17770a("EGLHelper", "eglMakeCurrent", this.f7933a.eglGetError());
                        return false;
                    }
                    throw new RuntimeException("mEglConfig not initialized");
                }
                throw new RuntimeException("eglDisplay not initialized");
            }
            throw new RuntimeException("egl not initialized");
        }

        /* renamed from: c */
        GL m17767c() {
            GL gl = this.f7937e.getGL();
            TextureView$SurfaceTextureListenerC3057h textureView$SurfaceTextureListenerC3057h = this.f7938f.get();
            if (textureView$SurfaceTextureListenerC3057h != null) {
                if (textureView$SurfaceTextureListenerC3057h.f7917k != null) {
                    gl = textureView$SurfaceTextureListenerC3057h.f7917k.wrap(gl);
                }
                if ((textureView$SurfaceTextureListenerC3057h.f7918l & 3) != 0) {
                    return GLDebugHelper.wrap(gl, (textureView$SurfaceTextureListenerC3057h.f7918l & 1) != 0 ? 1 : 0, (textureView$SurfaceTextureListenerC3057h.f7918l & 2) != 0 ? new C3065h() : null);
                }
                return gl;
            }
            return gl;
        }

        /* renamed from: d */
        public int m17766d() {
            if (this.f7933a.eglSwapBuffers(this.f7934b, this.f7935c)) {
                return 12288;
            }
            return this.f7933a.eglGetError();
        }

        /* renamed from: e */
        public void m17765e() {
            Log.w("EglHelper", "destroySurface()  tid=" + Thread.currentThread().getId());
            m17763g();
        }

        /* renamed from: f */
        public void m17764f() {
            Log.w("EglHelper", "finish() tid=" + Thread.currentThread().getId());
            if (this.f7937e != null) {
                TextureView$SurfaceTextureListenerC3057h textureView$SurfaceTextureListenerC3057h = this.f7938f.get();
                if (textureView$SurfaceTextureListenerC3057h != null) {
                    textureView$SurfaceTextureListenerC3057h.f7915i.destroyContext(this.f7933a, this.f7934b, this.f7937e);
                }
                this.f7937e = null;
            }
            EGLDisplay eGLDisplay = this.f7934b;
            if (eGLDisplay != null) {
                this.f7933a.eglTerminate(eGLDisplay);
                this.f7934b = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.h$f */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C3063f extends Thread {

        /* renamed from: a */
        private boolean f7939a;

        /* renamed from: b */
        private boolean f7940b;

        /* renamed from: c */
        private boolean f7941c;

        /* renamed from: d */
        private boolean f7942d;

        /* renamed from: e */
        private boolean f7943e;

        /* renamed from: f */
        private boolean f7944f;

        /* renamed from: g */
        private boolean f7945g;

        /* renamed from: h */
        private boolean f7946h;

        /* renamed from: i */
        private boolean f7947i;

        /* renamed from: j */
        private boolean f7948j;

        /* renamed from: o */
        private boolean f7953o;

        /* renamed from: r */
        private C3062e f7956r;

        /* renamed from: s */
        private WeakReference<TextureView$SurfaceTextureListenerC3057h> f7957s;

        /* renamed from: p */
        private ArrayList<Runnable> f7954p = new ArrayList<>();

        /* renamed from: q */
        private boolean f7955q = true;

        /* renamed from: k */
        private int f7949k = 0;

        /* renamed from: l */
        private int f7950l = 0;

        /* renamed from: n */
        private boolean f7952n = true;

        /* renamed from: m */
        private int f7951m = 1;

        C3063f(WeakReference<TextureView$SurfaceTextureListenerC3057h> weakReference) {
            this.f7957s = weakReference;
        }

        /* renamed from: j */
        private void m17749j() {
            if (this.f7947i) {
                this.f7947i = false;
                this.f7956r.m17765e();
            }
        }

        /* renamed from: k */
        private void m17748k() {
            if (this.f7946h) {
                this.f7956r.m17764f();
                this.f7946h = false;
                TextureView$SurfaceTextureListenerC3057h.f7907c.m17739c(this);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:146:0x037b  */
        /* JADX WARN: Removed duplicated region for block: B:147:0x037d  */
        /* renamed from: l */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void m17747l() throws java.lang.InterruptedException {
            /*
                Method dump skipped, instructions count: 1109
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.map.TextureView$SurfaceTextureListenerC3057h.C3063f.m17747l():void");
        }

        /* renamed from: m */
        private boolean m17746m() {
            return !this.f7942d && this.f7943e && !this.f7944f && this.f7949k > 0 && this.f7950l > 0 && (this.f7952n || this.f7951m == 1);
        }

        /* renamed from: a */
        public void m17761a(int i) {
            if (i < 0 || i > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (TextureView$SurfaceTextureListenerC3057h.f7907c) {
                this.f7951m = i;
                TextureView$SurfaceTextureListenerC3057h.f7907c.notifyAll();
            }
        }

        /* renamed from: a */
        public void m17760a(int i, int i2) {
            synchronized (TextureView$SurfaceTextureListenerC3057h.f7907c) {
                this.f7949k = i;
                this.f7950l = i2;
                this.f7955q = true;
                this.f7952n = true;
                this.f7953o = false;
                TextureView$SurfaceTextureListenerC3057h.f7907c.notifyAll();
                while (!this.f7940b && !this.f7942d && !this.f7953o && m17762a()) {
                    Log.i("Main thread", "onWindowResize waiting for render complete from tid=" + getId());
                    try {
                        TextureView$SurfaceTextureListenerC3057h.f7907c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: a */
        public void m17758a(Runnable runnable) {
            if (runnable == null) {
                throw new IllegalArgumentException("r must not be null");
            }
            synchronized (TextureView$SurfaceTextureListenerC3057h.f7907c) {
                this.f7954p.add(runnable);
                TextureView$SurfaceTextureListenerC3057h.f7907c.notifyAll();
            }
        }

        /* renamed from: a */
        public boolean m17762a() {
            return this.f7946h && this.f7947i && m17746m();
        }

        /* renamed from: b */
        public int m17757b() {
            int i;
            synchronized (TextureView$SurfaceTextureListenerC3057h.f7907c) {
                i = this.f7951m;
            }
            return i;
        }

        /* renamed from: c */
        public void m17756c() {
            synchronized (TextureView$SurfaceTextureListenerC3057h.f7907c) {
                this.f7952n = true;
                TextureView$SurfaceTextureListenerC3057h.f7907c.notifyAll();
            }
        }

        /* renamed from: d */
        public void m17755d() {
            synchronized (TextureView$SurfaceTextureListenerC3057h.f7907c) {
                Log.i("GLThread", "surfaceCreated tid=" + getId());
                this.f7943e = true;
                TextureView$SurfaceTextureListenerC3057h.f7907c.notifyAll();
                while (this.f7945g && !this.f7940b) {
                    try {
                        TextureView$SurfaceTextureListenerC3057h.f7907c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: e */
        public void m17754e() {
            synchronized (TextureView$SurfaceTextureListenerC3057h.f7907c) {
                Log.i("GLThread", "surfaceDestroyed tid=" + getId());
                this.f7943e = false;
                TextureView$SurfaceTextureListenerC3057h.f7907c.notifyAll();
                while (!this.f7945g && !this.f7940b) {
                    try {
                        TextureView$SurfaceTextureListenerC3057h.f7907c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: f */
        public void m17753f() {
            synchronized (TextureView$SurfaceTextureListenerC3057h.f7907c) {
                Log.i("GLThread", "onPause tid=" + getId());
                this.f7941c = true;
                TextureView$SurfaceTextureListenerC3057h.f7907c.notifyAll();
                while (!this.f7940b && !this.f7942d) {
                    Log.i("Main thread", "onPause waiting for mPaused.");
                    try {
                        TextureView$SurfaceTextureListenerC3057h.f7907c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: g */
        public void m17752g() {
            synchronized (TextureView$SurfaceTextureListenerC3057h.f7907c) {
                Log.i("GLThread", "onResume tid=" + getId());
                this.f7941c = false;
                this.f7952n = true;
                this.f7953o = false;
                TextureView$SurfaceTextureListenerC3057h.f7907c.notifyAll();
                while (!this.f7940b && this.f7942d && !this.f7953o) {
                    Log.i("Main thread", "onResume waiting for !mPaused.");
                    try {
                        TextureView$SurfaceTextureListenerC3057h.f7907c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: h */
        public void m17751h() {
            synchronized (TextureView$SurfaceTextureListenerC3057h.f7907c) {
                this.f7939a = true;
                TextureView$SurfaceTextureListenerC3057h.f7907c.notifyAll();
                while (!this.f7940b) {
                    try {
                        TextureView$SurfaceTextureListenerC3057h.f7907c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: i */
        public void m17750i() {
            this.f7948j = true;
            TextureView$SurfaceTextureListenerC3057h.f7907c.notifyAll();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("GLThread " + getId());
            Log.i("GLThread", "starting tid=" + getId());
            try {
                m17747l();
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                TextureView$SurfaceTextureListenerC3057h.f7907c.m17744a(this);
                throw th;
            }
            TextureView$SurfaceTextureListenerC3057h.f7907c.m17744a(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.h$g */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C3064g {

        /* renamed from: a */
        private static String f7958a = "GLThreadManager";

        /* renamed from: b */
        private static final Class f7959b;

        /* renamed from: c */
        private static final Method f7960c;

        /* renamed from: d */
        private boolean f7961d;

        /* renamed from: e */
        private int f7962e;

        /* renamed from: f */
        private boolean f7963f;

        /* renamed from: g */
        private boolean f7964g;

        /* renamed from: h */
        private boolean f7965h;

        /* renamed from: i */
        private C3063f f7966i;

        static {
            try {
                f7959b = Class.forName("android.os.SystemProperties");
                f7960c = f7959b.getDeclaredMethod("getInt", String.class, Integer.TYPE);
                f7960c.setAccessible(true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private C3064g() {
        }

        /* synthetic */ C3064g(View$OnLayoutChangeListenerC3067i view$OnLayoutChangeListenerC3067i) {
            this();
        }

        /* renamed from: c */
        private void m17740c() {
            if (this.f7961d) {
                return;
            }
            try {
                this.f7962e = ((Integer) f7960c.invoke(null, "ro.opengles.version", 0)).intValue();
            } catch (Exception unused) {
                this.f7962e = 65536;
            }
            if (this.f7962e >= 131072) {
                this.f7964g = true;
            }
            String str = f7958a;
            Log.w(str, "checkGLESVersion mGLESVersion = " + this.f7962e + " mMultipleGLESContextsAllowed = " + this.f7964g);
            this.f7961d = true;
        }

        /* renamed from: a */
        public synchronized void m17744a(C3063f c3063f) {
            Log.i("GLThread", "exiting tid=" + c3063f.getId());
            c3063f.f7940b = true;
            if (this.f7966i == c3063f) {
                this.f7966i = null;
            }
            notifyAll();
        }

        /* renamed from: a */
        public synchronized void m17743a(GL10 gl10) {
            if (!this.f7963f) {
                m17740c();
                String glGetString = gl10.glGetString(7937);
                if (this.f7962e < 131072) {
                    this.f7964g = !glGetString.startsWith("Q3Dimension MSM7500 ");
                    notifyAll();
                }
                this.f7965h = this.f7964g ? false : true;
                String str = f7958a;
                Log.w(str, "checkGLDriver renderer = \"" + glGetString + "\" multipleContextsAllowed = " + this.f7964g + " mLimitedGLESContexts = " + this.f7965h);
                this.f7963f = true;
            }
        }

        /* renamed from: a */
        public synchronized boolean m17745a() {
            return this.f7965h;
        }

        /* renamed from: b */
        public synchronized boolean m17742b() {
            m17740c();
            return !this.f7964g;
        }

        /* renamed from: b */
        public boolean m17741b(C3063f c3063f) {
            C3063f c3063f2 = this.f7966i;
            if (c3063f2 == c3063f || c3063f2 == null) {
                this.f7966i = c3063f;
                notifyAll();
                return true;
            }
            m17740c();
            if (this.f7964g) {
                return true;
            }
            C3063f c3063f3 = this.f7966i;
            if (c3063f3 != null) {
                c3063f3.m17750i();
                return false;
            }
            return false;
        }

        /* renamed from: c */
        public void m17739c(C3063f c3063f) {
            if (this.f7966i == c3063f) {
                this.f7966i = null;
            }
            notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.h$h */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C3065h extends Writer {

        /* renamed from: a */
        private StringBuilder f7967a = new StringBuilder();

        C3065h() {
        }

        /* renamed from: a */
        private void m17738a() {
            if (this.f7967a.length() > 0) {
                StringBuilder sb = this.f7967a;
                sb.delete(0, sb.length());
            }
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            m17738a();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            m17738a();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == '\n') {
                    m17738a();
                } else {
                    this.f7967a.append(c);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.h$i */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C3066i extends C3059b {
        public C3066i(boolean z) {
            super(8, 8, 8, 0, z ? 16 : 0, 0);
        }
    }

    public TextureView$SurfaceTextureListenerC3057h(Context context) {
        super(context);
        this.f7908a = 60;
        this.f7909b = new View$OnLayoutChangeListenerC3067i(this);
        this.f7910d = new WeakReference<>(this);
        m17785b();
    }

    public TextureView$SurfaceTextureListenerC3057h(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7908a = 60;
        this.f7909b = new View$OnLayoutChangeListenerC3067i(this);
        this.f7910d = new WeakReference<>(this);
        m17785b();
    }

    public TextureView$SurfaceTextureListenerC3057h(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7908a = 60;
        this.f7909b = new View$OnLayoutChangeListenerC3067i(this);
        this.f7910d = new WeakReference<>(this);
        m17785b();
    }

    /* renamed from: a */
    private Bitmap m17787a(int i, int i2, int i3, int i4, GL10 gl10, Bitmap.Config config) {
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

    /* renamed from: b */
    private void m17785b() {
        setSurfaceTextureListener(this);
        addOnLayoutChangeListener(this.f7909b);
    }

    /* renamed from: c */
    private void m17783c() {
        if (this.f7911e != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    public Bitmap captureImageFromSurface(int i, int i2, int i3, int i4, Object obj, Bitmap.Config config) {
        return m17787a(i, i2, i3, i4, (GL10) obj, config);
    }

    protected void finalize() throws Throwable {
        try {
            if (this.f7911e != null) {
                this.f7911e.m17751h();
            }
        } finally {
            super.finalize();
        }
    }

    public int getDebugFlags() {
        return this.f7918l;
    }

    public int getFPS() {
        return this.f7908a;
    }

    public boolean getPreserveEGLContextOnPause() {
        return this.f7920n;
    }

    public int getRenderMode() {
        return this.f7911e.m17757b();
    }

    @Override // android.view.TextureView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("GLTextureView", "onAttachedToWindow reattach =" + this.f7913g);
        if (this.f7913g && this.f7912f != null) {
            C3063f c3063f = this.f7911e;
            int m17757b = c3063f != null ? c3063f.m17757b() : 1;
            this.f7911e = new C3063f(this.f7910d);
            if (m17757b != 1) {
                this.f7911e.m17761a(m17757b);
            }
            this.f7911e.start();
        }
        this.f7913g = false;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        Log.d("GLTextureView", "onDetachedFromWindow");
        C3063f c3063f = this.f7911e;
        if (c3063f != null) {
            c3063f.m17751h();
        }
        this.f7913g = true;
        super.onDetachedFromWindow();
    }

    public void onPause() {
        C3063f c3063f = this.f7911e;
        if (c3063f != null) {
            c3063f.m17753f();
        }
    }

    public void onResume() {
        C3063f c3063f = this.f7911e;
        if (c3063f != null) {
            c3063f.m17752g();
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        surfaceCreated(surfaceTexture);
        surfaceChanged(surfaceTexture, 0, i, i2);
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        surfaceDestroyed(surfaceTexture);
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        surfaceChanged(surfaceTexture, 0, i, i2);
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void queueEvent(Runnable runnable) {
        C3063f c3063f = this.f7911e;
        if (c3063f != null) {
            c3063f.m17758a(runnable);
        }
    }

    public void requestRender() {
        C3063f c3063f = this.f7911e;
        if (c3063f != null) {
            c3063f.m17756c();
        }
    }

    public void setDebugFlags(int i) {
        this.f7918l = i;
    }

    public void setEGLConfigChooser(int i, int i2, int i3, int i4, int i5, int i6) {
        setEGLConfigChooser(new C3059b(i, i2, i3, i4, i5, i6));
    }

    public void setEGLConfigChooser(GLSurfaceView.EGLConfigChooser eGLConfigChooser) {
        m17783c();
        this.f7914h = eGLConfigChooser;
    }

    public void setEGLConfigChooser(boolean z) {
        setEGLConfigChooser(new C3066i(z));
    }

    public void setEGLContextClientVersion(int i) {
        m17783c();
        this.f7919m = i;
    }

    public void setEGLContextFactory(GLSurfaceView.EGLContextFactory eGLContextFactory) {
        m17783c();
        this.f7915i = eGLContextFactory;
    }

    public void setEGLWindowSurfaceFactory(GLSurfaceView.EGLWindowSurfaceFactory eGLWindowSurfaceFactory) {
        m17783c();
        this.f7916j = eGLWindowSurfaceFactory;
    }

    public void setFPS(int i) {
        this.f7908a = i;
    }

    public void setGLWrapper(GLSurfaceView.GLWrapper gLWrapper) {
        this.f7917k = gLWrapper;
    }

    public void setPreserveEGLContextOnPause(boolean z) {
        this.f7920n = z;
    }

    public void setRenderMode(int i) {
        this.f7911e.m17761a(i);
    }

    public void setRenderer(InterfaceC3015aq interfaceC3015aq) {
        m17783c();
        if (this.f7914h == null) {
            try {
                if (C3095g.m17682a(8, 8, 8, 0, 24, 8)) {
                    setEGLConfigChooser(8, 8, 8, 0, 24, 8);
                } else {
                    setEGLConfigChooser(true);
                }
            } catch (IllegalArgumentException unused) {
                setEGLConfigChooser(true);
            }
        }
        if (this.f7915i == null) {
            this.f7915i = new C3060c(this, null);
        }
        if (this.f7916j == null) {
            this.f7916j = new C3061d(null);
        }
        this.f7912f = interfaceC3015aq;
        this.f7911e = new C3063f(this.f7910d);
        this.f7911e.start();
    }

    public void surfaceChanged(SurfaceTexture surfaceTexture, int i, int i2, int i3) {
        C3063f c3063f = this.f7911e;
        if (c3063f != null) {
            c3063f.m17760a(i2, i3);
        }
    }

    public void surfaceCreated(SurfaceTexture surfaceTexture) {
        C3063f c3063f = this.f7911e;
        if (c3063f != null) {
            c3063f.m17755d();
        }
    }

    public void surfaceDestroyed(SurfaceTexture surfaceTexture) {
        C3063f c3063f = this.f7911e;
        if (c3063f != null) {
            c3063f.m17754e();
        }
    }
}
