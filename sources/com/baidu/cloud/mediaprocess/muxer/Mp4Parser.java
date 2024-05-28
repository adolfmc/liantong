package com.baidu.cloud.mediaprocess.muxer;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Mp4Parser {

    /* renamed from: a */
    public static Mp4Parser f4834a;

    /* renamed from: c */
    public RandomAccessFile f4836c;

    /* renamed from: b */
    public HashMap<String, Long> f4835b = new HashMap<>();

    /* renamed from: d */
    public long f4837d = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class StsdBox {

        /* renamed from: a */
        public RandomAccessFile f4838a;

        /* renamed from: c */
        public long f4840c;

        /* renamed from: d */
        public long f4841d;

        /* renamed from: b */
        public byte[] f4839b = new byte[4];

        /* renamed from: e */
        public final char[] f4842e = {'a', 'v', 'c', 'C'};

        /* renamed from: f */
        public final char[] f4843f = {'h', 'v', 'c', 'C'};

        public StsdBox(Mp4Parser mp4Parser, RandomAccessFile randomAccessFile, long j, long j2) {
            this.f4840c = 0L;
            this.f4841d = 0L;
            this.f4838a = randomAccessFile;
            this.f4840c = j;
            this.f4841d = j2;
        }

        /* renamed from: a */
        public final byte[] m19848a(char[] cArr) {
            this.f4840c += 8;
            this.f4838a.seek(this.f4840c);
            while (this.f4840c < this.f4841d) {
                while (this.f4838a.read() != cArr[0]) {
                    this.f4840c++;
                }
                this.f4838a.read(this.f4839b, 1, 3);
                this.f4840c += 4;
                byte[] bArr = this.f4839b;
                if (bArr[1] == cArr[1] && bArr[2] == cArr[2] && bArr[3] == cArr[3]) {
                    break;
                }
            }
            long j = this.f4840c;
            if (j >= this.f4841d) {
                Log.w("MP4Parser", "Parsing decoder-configuration-record failed!");
                return null;
            }
            this.f4838a.seek(j - 8);
            this.f4838a.read(this.f4839b, 0, 4);
            byte[] bArr2 = this.f4839b;
            int i = (bArr2[0] << 24) + (bArr2[1] << 16) + (bArr2[2] << 8) + bArr2[3];
            this.f4838a.read(bArr2, 0, 4);
            byte[] bArr3 = new byte[i];
            this.f4838a.read(bArr3, 0, i);
            return bArr3;
        }

        public byte[] getAvcDecoderConfigurationRecord() {
            return m19848a(this.f4842e);
        }

        public byte[] getHevcDecoderConfigurationRecord() {
            return m19848a(this.f4843f);
        }
    }

    public static Mp4Parser getInstance() {
        if (f4834a == null) {
            f4834a = new Mp4Parser();
        }
        return f4834a;
    }

    public static String toHexString(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = i; i3 < i + i2; i3++) {
            String hexString = Integer.toHexString(bArr[i3] & 255);
            if (hexString.length() < 2) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public final long m19850a(String str) {
        if (this.f4835b.get(str) != null) {
            return this.f4835b.get(str).longValue();
        }
        throw new IOException("Box not found: " + str);
    }

    /* renamed from: a */
    public final void m19849a(String str, long j) {
        boolean z;
        long j2;
        byte[] bArr = new byte[8];
        if (!str.equals("")) {
            this.f4835b.put(str, Long.valueOf(this.f4837d - 8));
        }
        long j3 = 0;
        while (j3 < j) {
            this.f4836c.read(bArr, 0, 8);
            this.f4837d += 8;
            long j4 = j3 + 8;
            for (int i = 0; i < 4; i++) {
                int i2 = i + 4;
                if ((bArr[i2] < 97 || bArr[i2] > 122) && (bArr[i2] <= 48 || bArr[i2] > 57)) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                String str2 = new String(bArr, 4, 4);
                if ((((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255)) == 1) {
                    this.f4836c.read(bArr, 0, 8);
                    this.f4837d += 8;
                    j4 += 8;
                    j2 = ByteBuffer.wrap(bArr, 0, 8).getLong() - 16;
                } else {
                    j2 = ByteBuffer.wrap(bArr, 0, 4).getInt() - 8;
                }
                if (j2 < 0 || j2 == 1061109559) {
                    throw new IOException();
                }
                j3 = j4 + j2;
                m19849a(str + '/' + str2, j2);
            } else if (j < 8) {
                RandomAccessFile randomAccessFile = this.f4836c;
                randomAccessFile.seek((randomAccessFile.getFilePointer() - 8) + j);
                j3 = j4 + (j - 8);
            } else {
                long j5 = j - 8;
                int i3 = (int) j5;
                if (this.f4836c.skipBytes(i3) < i3) {
                    throw new IOException();
                }
                this.f4837d += j5;
                j3 = j4 + j5;
            }
        }
    }

    public void close() {
        try {
            this.f4836c.close();
        } catch (Exception unused) {
        }
    }

    public StsdBox getStsdBox() {
        try {
            return new StsdBox(this, this.f4836c, m19850a("/moov/trak/mdia/minf/stbl/stsd"), this.f4836c.length());
        } catch (IOException unused) {
            throw new IOException("stsd box could not be found");
        }
    }

    public void parse(String str) {
        this.f4836c = new RandomAccessFile(new File(str), "r");
        try {
            this.f4837d = 0L;
            m19849a("", this.f4836c.length());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Parse error: malformed mp4 file");
        }
    }
}
