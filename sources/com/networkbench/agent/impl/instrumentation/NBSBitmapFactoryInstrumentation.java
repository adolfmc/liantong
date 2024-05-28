package com.networkbench.agent.impl.instrumentation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.TypedValue;
import com.networkbench.agent.impl.harvest.type.MetricCategory;
import com.networkbench.agent.impl.util.C6653u;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSBitmapFactoryInstrumentation {
    private static final ArrayList<String> categoryParams = new ArrayList<>(Arrays.asList("category", MetricCategory.class.getName(), "IMAGE"));

    @Deprecated
    /* renamed from: a */
    void m9897a() {
    }

    @NBSReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeFile(String str, BitmapFactory.Options options) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "BitmapFactory#decodeFile", categoryParams);
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        NBSTraceEngine.exitMethod();
        return decodeFile;
    }

    @NBSReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeFile(String str) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "BitmapFactory#decodeFile", categoryParams);
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        NBSTraceEngine.exitMethod();
        return decodeFile;
    }

    @NBSReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, Rect rect, BitmapFactory.Options options) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "BitmapFactory#decodeResourceStream", categoryParams);
        Bitmap decodeResourceStream = BitmapFactory.decodeResourceStream(resources, typedValue, inputStream, rect, options);
        NBSTraceEngine.exitMethod();
        return decodeResourceStream;
    }

    @NBSReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeResource(Resources resources, int i, BitmapFactory.Options options) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "BitmapFactory#decodeResource", categoryParams);
        Bitmap decodeResource = BitmapFactory.decodeResource(resources, i, options);
        NBSTraceEngine.exitMethod();
        return decodeResource;
    }

    @NBSReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeResource(Resources resources, int i) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "BitmapFactory#decodeResource", categoryParams);
        Bitmap decodeResource = BitmapFactory.decodeResource(resources, i);
        NBSTraceEngine.exitMethod();
        return decodeResource;
    }

    @NBSReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeByteArray(byte[] bArr, int i, int i2, BitmapFactory.Options options) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "BitmapFactory#decodeByteArray", categoryParams);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, i, i2, options);
        NBSTraceEngine.exitMethod();
        return decodeByteArray;
    }

    @NBSReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeByteArray(byte[] bArr, int i, int i2) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "BitmapFactory#decodeByteArray", categoryParams);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, i, i2);
        NBSTraceEngine.exitMethod();
        return decodeByteArray;
    }

    @NBSReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeStream(InputStream inputStream, Rect rect, BitmapFactory.Options options) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "BitmapFactory#decodeStream", categoryParams);
        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, rect, options);
        NBSTraceEngine.exitMethod();
        return decodeStream;
    }

    @NBSReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeStream(InputStream inputStream) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "BitmapFactory#decodeStream", categoryParams);
        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
        NBSTraceEngine.exitMethod();
        return decodeStream;
    }

    @NBSReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor, Rect rect, BitmapFactory.Options options) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "BitmapFactory#decodeFileDescriptor", categoryParams);
        Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
        NBSTraceEngine.exitMethod();
        return decodeFileDescriptor;
    }

    @NBSReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "BitmapFactory#decodeFileDescriptor", categoryParams);
        Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        NBSTraceEngine.exitMethod();
        return decodeFileDescriptor;
    }
}
