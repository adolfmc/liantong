package com.mob.tools.p237a;

import android.content.Context;
import android.os.Build;
import android.util.Base64;
import com.mob.commons.C5855l;
import com.mob.commons.C5873u;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* renamed from: com.mob.tools.a.h */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C6040h {

    /* renamed from: a */
    private static final String f14872a = C5855l.m12238a("014!hfhjjhjghilhjfjlSf$hjfejjfflh");

    /* renamed from: b */
    private static C6040h f14873b;

    /* renamed from: c */
    private InterfaceC6041a f14874c;

    /* renamed from: d */
    private volatile boolean f14875d = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.tools.a.h$a */
    /* loaded from: E:\567196_dexfile_execute.dex */
    interface InterfaceC6041a {
        /* renamed from: a */
        <T> T mo11638a(Class cls, Object obj, String str, Class[] clsArr, Object[] objArr) throws Throwable;

        /* renamed from: a */
        <T> T mo11637a(String str) throws Throwable;

        /* renamed from: a */
        <T> T mo11636a(String str, Object obj, String str2, Class[] clsArr, Object[] objArr) throws Throwable;

        /* renamed from: a */
        <T> T mo11635a(String str, String str2, Object obj) throws Throwable;

        /* renamed from: a */
        <T> T mo11634a(String str, Class[] clsArr, Object[] objArr) throws Throwable;
    }

    /* renamed from: a */
    public static synchronized C6040h m11645a(Context context) {
        C6040h c6040h;
        synchronized (C6040h.class) {
            if (f14873b == null && context != null) {
                f14873b = new C6040h(context, context.getApplicationInfo().targetSdkVersion);
            }
            c6040h = f14873b;
        }
        return c6040h;
    }

    /* renamed from: b */
    public boolean m11639b(Context context) {
        if (!this.f14875d) {
            try {
                File file = new File(context.getFilesDir(), f14872a);
                if (file.exists()) {
                    this.f14875d = file.delete();
                }
            } catch (Throwable unused) {
            }
        }
        return this.f14875d;
    }

    private C6040h(Context context, int i) {
        if (i >= 30 && Build.VERSION.SDK_INT >= 30) {
            this.f14874c = new C6042b(context);
        } else {
            this.f14874c = new C6043c();
        }
    }

    /* renamed from: a */
    public <T> T m11644a(Class cls, Object obj, String str, Class[] clsArr, Object[] objArr) throws Throwable {
        return (T) this.f14874c.mo11638a(cls, obj, str, clsArr, objArr);
    }

    /* renamed from: a */
    public <T> T m11642a(String str, Object obj, String str2, Class[] clsArr, Object[] objArr) throws Throwable {
        return (T) this.f14874c.mo11636a(str, obj, str2, clsArr, objArr);
    }

    /* renamed from: a */
    public <T> T m11643a(String str) throws Throwable {
        return (T) this.f14874c.mo11637a(str);
    }

    /* renamed from: a */
    public <T> T m11641a(String str, String str2, Object obj) throws Throwable {
        return (T) this.f14874c.mo11635a(str, str2, obj);
    }

    /* renamed from: a */
    public <T> T m11640a(String str, Class[] clsArr, Object[] objArr) throws Throwable {
        return (T) this.f14874c.mo11634a(str, clsArr, objArr);
    }

    /* renamed from: com.mob.tools.a.h$b */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    static class C6042b implements InterfaceC6041a {

        /* renamed from: a */
        private Method f14876a;

        /* renamed from: b */
        private Method f14877b;

        /* renamed from: c */
        private Method f14878c;

        /* renamed from: d */
        private Method f14879d;

        /* renamed from: e */
        private Method f14880e;

        /* renamed from: f */
        private Method f14881f;

        /* renamed from: g */
        private Method f14882g;

        /* renamed from: h */
        private boolean f14883h;

        public C6042b(Context context) {
            FileOutputStream fileOutputStream;
            FileOutputStream fileOutputStream2 = null;
            this.f14876a = null;
            this.f14877b = null;
            this.f14878c = null;
            this.f14879d = null;
            this.f14880e = null;
            this.f14881f = null;
            this.f14882g = null;
            this.f14883h = false;
            try {
                ResHelper.deleteFileAndFolder(new File(context.getFilesDir(), C5855l.m12238a("014.hfhjjhjghilhjfjl9f1hjfejjffjh")));
            } catch (Throwable unused) {
            }
            try {
                File file = new File(context.getFilesDir(), C6040h.f14872a);
                if (!file.exists()) {
                    byte[] decode = Base64.decode("UEsDBBQACAgIAG2HfFYAAAAAAAAAAAAAAAAUAAQATUVUQS1JTkYvTUFOSUZFU1QuTUb+ygAA803My0xLLS7RDUstKs7Mz7NSMNQz4OVySa3Q9clPTiwBCyXnJBYXpxbrpaRW8HI5F6UmlqSm6DpVWimkVACVG5rxcvFyAQBQSwcI8N6zmEcAAABJAAAAUEsDBBQACAgIAG2HfFYAAAAAAAAAAAAAAAALAAAAY2xhc3Nlcy5kZXidV11sVEUUPnPn/uy9e3e7vWC3wEILW6H8yIKgAtsgpQrVbBWkaQwlxmX3Uq52d8vubcGfGDXgz4OJSkxIRKMPNTyY+BPiDw8mxN8HH9Qn9UXRaHzQRBMf0ETjNzN3t1tpYuIm3z1nzpzzzZk5c2fnlv0TzqYt15HfaYw/tO77Qw+en+UXjNXPnf+zu48u6qczCaIpIjoxttWj6Fd2iQZJ2TuAkBHBjV5n1PqlgIJGJEyfQm5yiH6GvMtCPBAAx4Bp4CTwBPAUcBp4HngX+BL4HbBiRMuAHLAHmAAeBV4EXgZmgXPAq8BrwPvAl8AvwGXgL4DbRBlgA7AF2AnsBcaAcaABnALOAG8AF4APgc+BH4BfgD8AhnkkgC5gM7AHOAQcB04BzwIvAOeAN4GLwFfAN8CPwG8AaAiC4gCWUq5dMlpLsW6dgFjsRcBVQDewBFgKGAAHfjWJMC3Sgcumsgs9hvUyIz1lzdnb/bvFmkZ6f5u+tc1/V8Qjch22VC5OVNvFkb4P9q5oHncKElg16eFST+S7Vj5NWiclo41SGpHU6XopLSk5RlsjrYrHQsY3SBmnvJgbrH1SqraNiGukVG01gsqfInkOk9jhqrbgfA/Jfh1X+ndt+t9tetKd01dFuuBVkkmdxRX/VMqW1euAVdTFhP1uUb+Et8Fb5jkZk9NSaxVl33Gx/wwasWNOdVOMZmJoy741lP1kri9ju0LGIx+WfSsBnxU0YpmIS9Jnpmv0GJyyHwn7gLRn30Z8LEkjMctBOy789psu78GmV37rpd9IotWn9WjoeyVBov2F6epinJGkGmPI7Ijb0XxymE9GzCftmRkN+XLMJcR4BvI1dFPkea3uMk/LNsAQ46ZgWMnFDuDYyQk8Bc+N4BE18myvO6ODxwDPY+CxDJGbLXhGkZenZ0+BxzFswbPbUDwqYvVCETx7Ev5xXfpzZNLDYlTtTdAjeLp0kVy9UxcVMmQeh+JqT3r9nuV1qRmhAidbM7IyGLN9ZtlHBb9mjbianNszmsjJBnsHKi0sLyVcrVMT77Eh358Gxuj932t27JBOT18QjE5Ug8fBt0HwdXtWhoNPB98psX/AZxqW4BsywKfWwtYtwbdZj9ZORtxA2WmXIk85rvDRuMs7ebNGZzHOrVGNtsUdWnl+m27RNteglR9sh3YsZeLNFOv5fyt4dRTh/kcFLaxuXFbQkRXs/7j5v2K1vef9XL2jQor8U1y9k+J/RtThshadhVzFZbiKM1rnqA7pyvO3eW5oUiYin7l+LtuG9FH6XFwiihM2Fp2HTJ4K+trR0TzxgdEdZA4E1SDcSdrOfkoMB7uDatmvb7ynOFMkrVAgXsBDL4inWZA/yhRKtUquUs6VimHusPTPNQPztLxQLk7OBPfmitVqLSyGQa2aOxBMVIvhdN3P09IFukeP1mvHG3nqLIhhc5PF6kRuaLLYgMlrM91++B6/FM63HQjrQXUiT1e12SRd8fAkRutpM9f9I5OIzw3Vqo2wPl0Ka8h2yQIOewJ/siwyvbJrxA+P1tDHxkgbO0jsIGkHC8TGyRu/MvdF4wskP8/YzN4uiYjbihWfeGnyftJLyJHSaqU2Nu5rhH5l49jIHdPVMIBP0j/hV6bE0jWGB/fdQsYRkTHZUkgW60itLpVEpKjEiU/4IXXhcZOPIet+uW0xKNVml2tAnW2WiCF9hSnqsNAxPITkbaGoeAdqM2kPemsKUYwZVGdq9/rkKCknY1ZUn6Okmo7SG7SkEh7du3D2i+Z3qQQWzzdGwwqWA0gyKJf96uBUcHNrOcmt+seHbwFrsVryKY5Wq2FPFevFSkNOUamoKcXqfskPZvw6JRp+OFgq+Y1GgL1HXY2FR9DDo0GDjJni5DQ4ZyqtorZUuX1woFlJ2suutW69xKnPYV6Ks7XO+9wY1NniFOfXO99x7Ron4Pou4wTtYhlrwyXO+pzznJbrtJ5nVuWFRYsslzjf6sxyttIeoLSxPL95+w4rz7wOzq4DKa7BA7TCyEhzO0/ffN5Zrm1zaCvPCLfZ/doDy+eNsEWMsCI2QBpjOzPxNEsn0jzt4plMpyHT0EjT0Me6zXk+yX/5iD6j1afaVqudbPnYLVsrVt4nH3lYf91kT7JPTGafiTH7W+Anm9mfOsw+G2f2SXH16mg7s5uy+f2g0dw3BKe57whx/ja/IUya+47gKdUWZzzrVXfaTQg0e5WPuO+xlDqDxZ1X61Vjie8OHvnLu1uv4hH3QYpi5T0xpXTxjfMPUEsHCKFWFIudBgAAHA0AAFBLAQIUABQACAgIAG2HfFbw3rOYRwAAAEkAAAAUAAQAAAAAAAAAAAAAAAAAAABNRVRBLUlORi9NQU5JRkVTVC5NRv7KAABQSwECFAAUAAgICABth3xWoVYUi50GAAAcDQAACwAAAAAAAAAAAAAAAACNAAAAY2xhc3Nlcy5kZXhQSwUGAAAAAAIAAgB/AAAAYwcAAAAA", 2);
                    try {
                        fileOutputStream = new FileOutputStream(file);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        fileOutputStream.write(decode);
                        C5873u.m12179a(fileOutputStream);
                        file.setReadOnly();
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream2 = fileOutputStream;
                        C5873u.m12179a(fileOutputStream2);
                        throw th;
                    }
                }
                Class cls = (Class) ReflectHelper.invokeInstanceMethod(ReflectHelper.newInstance(ReflectHelper.importClass(C5855l.m12238a("021:feGfi]fffkfnhfhjfmhj=khKfhhfhmTh[gehnfk<ih")), file), C5855l.m12238a("009iYgfCfCfeilDifLhjhj"), new Object[]{C5855l.m12238a("026e0gffhhffhfehfGefk6hfhgfk3g(fe>hVflhfhlfkhkfkOgMfe$h fl"), null}, new Class[]{String.class, ClassLoader.class});
                this.f14876a = cls.getDeclaredMethod("exemptionsHAPI", String[].class);
                this.f14876a.setAccessible(true);
                this.f14877b = cls.getDeclaredMethod(C5855l.m12238a("010]fkSgCffgffnGh:hlgnimgk"), Class.class, Object.class, String.class, Class[].class, Object[].class);
                this.f14877b.setAccessible(true);
                this.f14878c = cls.getDeclaredMethod(C5855l.m12238a("0109fkPg]ffgffn7hVhlgnimgk"), String.class, Object.class, String.class, Class[].class, Object[].class);
                this.f14878c.setAccessible(true);
                this.f14879d = cls.getDeclaredMethod(C5855l.m12238a("012ghAhhhlgk^gJhjEkfgeh"), String.class);
                this.f14879d.setAccessible(true);
                this.f14880e = cls.getDeclaredMethod(C5855l.m12238a("012gh-hhhlgk?g*hjJkfgeh"), String.class, Class[].class, Object[].class);
                this.f14880e.setAccessible(true);
                this.f14881f = cls.getDeclaredMethod(C5855l.m12238a("009>gg=hk]hlhnfk.hiUfe"), String.class, String.class, Object.class);
                this.f14881f.setAccessible(true);
                this.f14882g = cls.getDeclaredMethod("getHClz", String.class);
                this.f14882g.setAccessible(true);
                this.f14883h = true;
            } catch (Throwable unused2) {
            }
        }

        @Override // com.mob.tools.p237a.C6040h.InterfaceC6041a
        /* renamed from: a */
        public <T> T mo11638a(Class cls, Object obj, String str, Class[] clsArr, Object[] objArr) throws Throwable {
            Method method = this.f14877b;
            if (method != null) {
                return (T) method.invoke(null, cls, obj, str, clsArr, objArr);
            }
            throw new Throwable("IHA is null");
        }

        @Override // com.mob.tools.p237a.C6040h.InterfaceC6041a
        /* renamed from: a */
        public <T> T mo11636a(String str, Object obj, String str2, Class[] clsArr, Object[] objArr) throws Throwable {
            Method method = this.f14878c;
            if (method != null) {
                return (T) method.invoke(null, str, obj, str2, clsArr, objArr);
            }
            throw new Throwable("IHABC is null");
        }

        @Override // com.mob.tools.p237a.C6040h.InterfaceC6041a
        /* renamed from: a */
        public <T> T mo11637a(String str) throws Throwable {
            Method method = this.f14879d;
            if (method != null) {
                return (T) method.invoke(null, str);
            }
            throw new Throwable("nHI is null");
        }

        @Override // com.mob.tools.p237a.C6040h.InterfaceC6041a
        /* renamed from: a */
        public <T> T mo11635a(String str, String str2, Object obj) throws Throwable {
            Method method = this.f14881f;
            if (method != null) {
                return (T) method.invoke(null, str, str2, obj);
            }
            throw new Throwable("mthGetHField is null");
        }

        @Override // com.mob.tools.p237a.C6040h.InterfaceC6041a
        /* renamed from: a */
        public <T> T mo11634a(String str, Class[] clsArr, Object[] objArr) throws Throwable {
            Method method = this.f14880e;
            if (method != null) {
                return (T) method.invoke(null, str, clsArr, objArr);
            }
            throw new Throwable("mthNewHInstanceByParams is null");
        }
    }

    /* renamed from: com.mob.tools.a.h$c */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    static class C6043c implements InterfaceC6041a {
        C6043c() {
        }

        @Override // com.mob.tools.p237a.C6040h.InterfaceC6041a
        /* renamed from: a */
        public <T> T mo11638a(Class cls, Object obj, String str, Class[] clsArr, Object[] objArr) throws Throwable {
            Method method = (Method) Class.class.getDeclaredMethod(C5855l.m12238a("017.gg@hkWhm?heifQfl0h feje hkjSgffe"), String.class, Class[].class).invoke(cls, str, clsArr);
            method.setAccessible(true);
            return (T) method.invoke(obj, objArr);
        }

        @Override // com.mob.tools.p237a.C6040h.InterfaceC6041a
        /* renamed from: a */
        public <T> T mo11636a(String str, Object obj, String str2, Class[] clsArr, Object[] objArr) throws Throwable {
            return (T) mo11638a((Class) Class.class.getDeclaredMethod(C5855l.m12238a("007Tghgfflgj)fVfh1h"), String.class).invoke(null, str), obj, str2, clsArr, objArr);
        }

        @Override // com.mob.tools.p237a.C6040h.InterfaceC6041a
        /* renamed from: a */
        public <T> T mo11637a(String str) throws Throwable {
            return (T) Class.class.getDeclaredMethod(C5855l.m12238a("011ghZhhgkMgOhjZkfgeh"), new Class[0]).invoke((Class) Class.class.getDeclaredMethod(C5855l.m12238a("007Xghgfflgj+fJfh,h"), String.class).invoke(null, str), new Object[0]);
        }

        @Override // com.mob.tools.p237a.C6040h.InterfaceC6041a
        /* renamed from: a */
        public <T> T mo11635a(String str, String str2, Object obj) throws Throwable {
            Field field = (Field) Class.class.getDeclaredMethod(C5855l.m12238a("0161gg8hkXhmBheif:flVhZfehnfk'hiMfe"), String.class).invoke((Class) Class.class.getDeclaredMethod(C5855l.m12238a("007Dghgfflgj'fYfhWh"), String.class).invoke(null, str), str2);
            field.setAccessible(true);
            return (T) field.get(obj);
        }

        @Override // com.mob.tools.p237a.C6040h.InterfaceC6041a
        /* renamed from: a */
        public <T> T mo11634a(String str, Class[] clsArr, Object[] objArr) throws Throwable {
            if (clsArr == null || clsArr.length == 0 || objArr == null || objArr.length == 0) {
                return (T) mo11637a(str);
            }
            Constructor constructor = (Constructor) Class.class.getDeclaredMethod("getDeclaredConstructor", Class[].class).invoke((Class) Class.class.getDeclaredMethod(C5855l.m12238a("007Qghgfflgj7fNfhDh"), String.class).invoke(null, str), clsArr);
            constructor.setAccessible(true);
            return (T) constructor.newInstance(objArr);
        }
    }
}
