package com.bytedance.pangle.res.p181a;

import android.text.TextUtils;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.C3945e;
import com.bytedance.pangle.util.C3947g;
import com.bytedance.pangle.util.C3948h;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.bytedance.pangle.res.a.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3910c {
    /* renamed from: a */
    public final int m16705a(File file, boolean z, StringBuilder sb) {
        ZipFile zipFile;
        String byteArrayOutputStream;
        ZipFile zipFile2 = null;
        try {
            zipFile = new ZipFile(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            ZipEntry entry = zipFile.getEntry("assets/ZeusResMapping");
            if (entry == null) {
                C3947g.m16630a(zipFile);
                return 200;
            }
            if (z) {
                File file2 = new File(file.getParentFile(), "resMappingBak");
                if (!file2.exists()) {
                    ZeusLogger.errReport("Zeus/install_pangle", "resMappingBakFile is not exists. " + file2.getAbsolutePath());
                    sb.append("resMappingBakFile is not exists. ");
                    sb.append(file2.getAbsolutePath());
                    C3947g.m16630a(zipFile);
                    return 300;
                }
                FileInputStream fileInputStream = new FileInputStream(file2);
                FileChannel channel = fileInputStream.getChannel();
                byteArrayOutputStream = Charset.defaultCharset().newDecoder().decode(channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size()).asReadOnlyBuffer()).toString();
                channel.close();
                fileInputStream.close();
            } else {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                InputStream inputStream = zipFile.getInputStream(entry);
                if (inputStream != null) {
                    ReadableByteChannel newChannel = Channels.newChannel(inputStream);
                    WritableByteChannel newChannel2 = Channels.newChannel(byteArrayOutputStream2);
                    C3948h.m16626a(newChannel, newChannel2);
                    newChannel.close();
                    newChannel2.close();
                }
                byteArrayOutputStream = byteArrayOutputStream2.toString();
            }
            if (TextUtils.isEmpty(byteArrayOutputStream)) {
                ZeusLogger.errReport("Zeus/install_pangle", "zeusResMappingContent empty, useBakFile:".concat(String.valueOf(z)));
                sb.append("zeusResMappingContent isEmpty. useBakFile:");
                sb.append(z);
                C3947g.m16630a(zipFile);
                return 300;
            }
            JSONObject jSONObject = new JSONObject(byteArrayOutputStream);
            JSONObject jSONObject2 = new JSONObject(byteArrayOutputStream);
            JSONArray jSONArray = (JSONArray) jSONObject.get("fileNames");
            final HashSet hashSet = new HashSet();
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add((String) jSONArray.get(i));
            }
            final JSONObject jSONObject3 = (JSONObject) jSONObject.get("resMapping");
            final JSONObject jSONObject4 = (JSONObject) jSONObject2.get("resMapping");
            final int[] iArr = {0};
            m16706a(file, hashSet, new InterfaceC3916h() { // from class: com.bytedance.pangle.res.a.c.1
                @Override // com.bytedance.pangle.res.p181a.InterfaceC3916h
                /* renamed from: a */
                public final int mo16695a(int i2) {
                    String str = "0x" + Integer.toHexString(i2);
                    String str2 = (String) jSONObject3.opt(str);
                    if (str2 == null) {
                        return i2;
                    }
                    int identifier = Zeus.getAppApplication().getResources().getIdentifier(str2.split(" ")[1], str2.split(" ")[0], Zeus.getAppApplication().getPackageName());
                    if (identifier == 0) {
                        identifier = Zeus.getAppApplication().getResources().getIdentifier(str2.split(" ")[1].replaceAll("_", "."), str2.split(" ")[0], Zeus.getAppApplication().getPackageName());
                    }
                    if (identifier == 0) {
                        ZeusLogger.m16788w("Zeus/install_pangle", "getIdentifier failed. resName is ".concat(String.valueOf(str2)));
                        return i2;
                    }
                    String str3 = "0x" + Integer.toHexString(identifier);
                    jSONObject4.remove(str);
                    try {
                        jSONObject4.put(str3, str2);
                    } catch (Throwable th2) {
                        ZeusLogger.errReport("Zeus/install_pangle", "update resMappingBak failed.", th2);
                    }
                    if (identifier != i2) {
                        int[] iArr2 = iArr;
                        iArr2[0] = iArr2[0] + 1;
                    }
                    return identifier;
                }

                @Override // com.bytedance.pangle.res.p181a.InterfaceC3916h
                /* renamed from: a */
                public final boolean mo16694a(String str) {
                    return hashSet.contains(str);
                }
            });
            ZeusLogger.m16794d("Zeus/install_pangle", "modifyRes count = " + iArr[0]);
            if (C3948h.m16628a(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2), new File(file.getParentFile(), "resMappingBak"), sb)) {
                C3947g.m16630a(zipFile);
                return 100;
            }
            ZeusLogger.errReport("Zeus/install_pangle", "writeText failed." + sb.toString());
            sb.append("writeText failed.");
            C3947g.m16630a(zipFile);
            return 300;
        } catch (Throwable th2) {
            th = th2;
            zipFile2 = zipFile;
            try {
                ZeusLogger.errReport("Zeus/install_pangle", "modifyRes failed. catch: " + th.getMessage());
                sb.append("modifyRes failed. catch: ");
                sb.append(th.getMessage());
                if (zipFile2 != null) {
                    C3947g.m16630a(zipFile2);
                }
                return 300;
            } catch (Throwable th3) {
                ZipFile zipFile3 = zipFile2;
                if (zipFile3 != null) {
                    C3947g.m16630a(zipFile3);
                }
                throw th3;
            }
        }
    }

    /* renamed from: a */
    private static void m16706a(File file, HashSet<String> hashSet, InterfaceC3916h interfaceC3916h) {
        int i;
        MappedByteBuffer m16707a = m16707a(file);
        int capacity = m16707a.capacity();
        if (capacity >= 22) {
            int i2 = capacity - 22;
            int min = Math.min(i2, 65535);
            for (int i3 = 0; i3 < min; i3++) {
                i = i2 - i3;
                if (m16707a.getInt(i) == 101010256 && m16707a.getShort(i + 20) == i3) {
                    break;
                }
            }
        }
        i = -1;
        if (i == -1) {
            throw new Throwable("endOfCentralPosition == -1");
        }
        int i4 = m16707a.getInt(i + 12);
        int i5 = m16707a.getInt(i + 16);
        int i6 = i4 + i5;
        while (i5 < i6) {
            if (m16707a.getInt(i5) != 33639248) {
                throw new RuntimeException("Expected: 0x02014b50, got: " + m16707a.getInt(i5));
            }
            int i7 = m16707a.getShort(i5 + 28);
            short s = m16707a.getShort(i5 + 30);
            byte[] bArr = new byte[i7];
            for (int i8 = 0; i8 < bArr.length; i8++) {
                bArr[i8] = m16707a.get(i5 + 46 + i8);
            }
            String str = new String(bArr);
            int i9 = m16707a.getInt(i5 + 20);
            int i10 = m16707a.getInt(i5 + 24);
            if (hashSet.contains(str)) {
                if (i9 != i10) {
                    throw new Throwable(str + " is compressed.");
                }
                C3945e<Integer, byte[]> m16704a = m16704a(m16707a, m16707a.getInt(i5 + 42), str);
                try {
                    byte[] bArr2 = m16704a.f9370b;
                    if (!TextUtils.isEmpty(str) && interfaceC3916h.mo16694a(str)) {
                        if (str.equals("AndroidManifest.xml")) {
                            C3919k.m16685a(bArr2, interfaceC3916h);
                        } else if ((str.endsWith(".xml") && str.startsWith("res/")) || TextUtils.equals(str, "AndroidManifest.xml")) {
                            C3919k.m16685a(bArr2, interfaceC3916h);
                        } else if (str.equals("resources.arsc")) {
                            new C3906a(bArr2, interfaceC3916h).m16726a();
                        }
                    }
                    for (int i11 = 0; i11 < m16704a.f9370b.length; i11++) {
                        m16707a.put(m16704a.f9369a.intValue() + i11, m16704a.f9370b[i11]);
                    }
                } catch (Throwable th) {
                    throw new RuntimeException(th);
                }
            }
            i5 += i7 + 46 + s;
        }
    }

    /* renamed from: a */
    private static C3945e<Integer, byte[]> m16704a(MappedByteBuffer mappedByteBuffer, int i, String str) {
        if (mappedByteBuffer.getInt(i) != 67324752) {
            throw new RuntimeException("Expected: 0x04034b50, got: " + mappedByteBuffer.getInt(i) + " FileName:" + str);
        }
        int i2 = mappedByteBuffer.getInt(i + 18);
        int i3 = mappedByteBuffer.getInt(i + 22);
        if (i2 != i3) {
            throw new RuntimeException(str + " is compressed. compressSize:" + i2 + " size:" + i3);
        }
        byte[] bArr = new byte[i3];
        int i4 = i + 30 + mappedByteBuffer.getShort(i + 26) + mappedByteBuffer.getShort(i + 28);
        for (int i5 = 0; i5 < i3; i5++) {
            bArr[i5] = mappedByteBuffer.get(i4 + i5);
        }
        return new C3945e<>(Integer.valueOf(i4), bArr);
    }

    /* renamed from: a */
    private static MappedByteBuffer m16707a(File file) {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
        } catch (Throwable th) {
            th = th;
            randomAccessFile = null;
        }
        try {
            FileChannel channel = randomAccessFile.getChannel();
            long size = channel.size();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0L, size);
            byte[] bArr = new byte[4194304];
            long j = size / 4194304;
            int i = (int) (size % 4194304);
            for (int i2 = 0; i2 < j; i2++) {
                map.get(bArr);
            }
            if (i > 0) {
                map.get(new byte[i]);
            }
            map.order(ByteOrder.LITTLE_ENDIAN);
            randomAccessFile.close();
            return map;
        } catch (Throwable th2) {
            th = th2;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }
}
