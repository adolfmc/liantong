package com.bytedance.pangle.p177e;

import com.bytedance.pangle.util.C3947g;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.bytedance.pangle.e.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3817h implements Closeable {

    /* renamed from: a */
    private final FileInputStream f9108a;

    /* renamed from: b */
    private C3818a f9109b;

    /* renamed from: c */
    private C3819b[] f9110c;

    /* renamed from: d */
    private C3820c[] f9111d;

    /* renamed from: e */
    private final Map<String, C3820c> f9112e = new HashMap();

    /* renamed from: a */
    public static boolean m16883a(File file) {
        try {
            C3947g.m16635a(new C3817h(file));
            return true;
        } catch (IOException unused) {
            C3947g.m16635a((Closeable) null);
            return false;
        } catch (Throwable th) {
            C3947g.m16635a((Closeable) null);
            throw th;
        }
    }

    private C3817h(File file) {
        C3820c[] c3820cArr;
        this.f9109b = null;
        this.f9110c = null;
        this.f9111d = null;
        this.f9108a = new FileInputStream(file);
        FileChannel channel = this.f9108a.getChannel();
        this.f9109b = new C3818a(channel, (byte) 0);
        ByteBuffer allocate = ByteBuffer.allocate(128);
        allocate.limit(this.f9109b.f9122j);
        allocate.order(this.f9109b.f9113a[5] == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
        channel.position(this.f9109b.f9118f);
        this.f9110c = new C3819b[this.f9109b.f9123k];
        for (int i = 0; i < this.f9110c.length; i++) {
            m16880b(channel, allocate, "failed to read phdr.");
            this.f9110c[i] = new C3819b(allocate, this.f9109b.f9113a[4], (byte) 0);
        }
        channel.position(this.f9109b.f9119g);
        allocate.limit(this.f9109b.f9124l);
        this.f9111d = new C3820c[this.f9109b.f9125m];
        for (int i2 = 0; i2 < this.f9111d.length; i2++) {
            m16880b(channel, allocate, "failed to read shdr.");
            this.f9111d[i2] = new C3820c(allocate, this.f9109b.f9113a[4], (byte) 0);
        }
        if (this.f9109b.f9126n > 0) {
            C3820c c3820c = this.f9111d[this.f9109b.f9126n];
            ByteBuffer allocate2 = ByteBuffer.allocate((int) c3820c.f9140f);
            this.f9108a.getChannel().position(c3820c.f9139e);
            m16880b(this.f9108a.getChannel(), allocate2, "failed to read section: " + c3820c.f9145k);
            for (C3820c c3820c2 : this.f9111d) {
                allocate2.position(c3820c2.f9135a);
                c3820c2.f9145k = m16882a(allocate2);
                this.f9112e.put(c3820c2.f9145k, c3820c2);
            }
        }
    }

    /* renamed from: a */
    private static String m16882a(ByteBuffer byteBuffer) {
        byte[] array = byteBuffer.array();
        int position = byteBuffer.position();
        while (byteBuffer.hasRemaining() && array[byteBuffer.position()] != 0) {
            byteBuffer.position(byteBuffer.position() + 1);
        }
        byteBuffer.position(byteBuffer.position() + 1);
        return new String(array, position, (byteBuffer.position() - position) - 1, Charset.forName("ASCII"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m16880b(FileChannel fileChannel, ByteBuffer byteBuffer, String str) {
        byteBuffer.rewind();
        int read = fileChannel.read(byteBuffer);
        if (read != byteBuffer.limit()) {
            throw new IOException(str + " Rest bytes insufficient, expect to read " + byteBuffer.limit() + " bytes but only " + read + " bytes were read.");
        }
        byteBuffer.flip();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f9108a.close();
        this.f9112e.clear();
        this.f9110c = null;
        this.f9111d = null;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.e.h$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C3818a {

        /* renamed from: a */
        public final byte[] f9113a;

        /* renamed from: b */
        public final short f9114b;

        /* renamed from: c */
        public final short f9115c;

        /* renamed from: d */
        public final int f9116d;

        /* renamed from: e */
        public final long f9117e;

        /* renamed from: f */
        public final long f9118f;

        /* renamed from: g */
        public final long f9119g;

        /* renamed from: h */
        public final int f9120h;

        /* renamed from: i */
        public final short f9121i;

        /* renamed from: j */
        public final short f9122j;

        /* renamed from: k */
        public final short f9123k;

        /* renamed from: l */
        public final short f9124l;

        /* renamed from: m */
        public final short f9125m;

        /* renamed from: n */
        public final short f9126n;

        /* synthetic */ C3818a(FileChannel fileChannel, byte b) {
            this(fileChannel);
        }

        private C3818a(FileChannel fileChannel) {
            this.f9113a = new byte[16];
            fileChannel.position(0L);
            fileChannel.read(ByteBuffer.wrap(this.f9113a));
            byte[] bArr = this.f9113a;
            if (bArr[0] != Byte.MAX_VALUE || bArr[1] != 69 || bArr[2] != 76 || bArr[3] != 70) {
                throw new IOException(String.format("bad elf magic: %x %x %x %x.", Byte.valueOf(this.f9113a[0]), Byte.valueOf(this.f9113a[1]), Byte.valueOf(this.f9113a[2]), Byte.valueOf(this.f9113a[3])));
            }
            byte b = bArr[4];
            C3817h.m16884a(b, 2, "bad elf class: " + ((int) this.f9113a[4]));
            byte b2 = this.f9113a[5];
            C3817h.m16884a(b2, 2, "bad elf data encoding: " + ((int) this.f9113a[5]));
            ByteBuffer allocate = ByteBuffer.allocate(this.f9113a[4] == 1 ? 36 : 48);
            allocate.order(this.f9113a[5] == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
            C3817h.m16880b(fileChannel, allocate, "failed to read rest part of ehdr.");
            this.f9114b = allocate.getShort();
            this.f9115c = allocate.getShort();
            this.f9116d = allocate.getInt();
            int i = this.f9116d;
            C3817h.m16884a(i, 1, "bad elf version: " + this.f9116d);
            switch (this.f9113a[4]) {
                case 1:
                    this.f9117e = allocate.getInt();
                    this.f9118f = allocate.getInt();
                    this.f9119g = allocate.getInt();
                    break;
                case 2:
                    this.f9117e = allocate.getLong();
                    this.f9118f = allocate.getLong();
                    this.f9119g = allocate.getLong();
                    break;
                default:
                    throw new IOException("Unexpected elf class: " + ((int) this.f9113a[4]));
            }
            this.f9120h = allocate.getInt();
            this.f9121i = allocate.getShort();
            this.f9122j = allocate.getShort();
            this.f9123k = allocate.getShort();
            this.f9124l = allocate.getShort();
            this.f9125m = allocate.getShort();
            this.f9126n = allocate.getShort();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.e.h$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C3819b {

        /* renamed from: a */
        public final int f9127a;

        /* renamed from: b */
        public final int f9128b;

        /* renamed from: c */
        public final long f9129c;

        /* renamed from: d */
        public final long f9130d;

        /* renamed from: e */
        public final long f9131e;

        /* renamed from: f */
        public final long f9132f;

        /* renamed from: g */
        public final long f9133g;

        /* renamed from: h */
        public final long f9134h;

        /* synthetic */ C3819b(ByteBuffer byteBuffer, int i, byte b) {
            this(byteBuffer, i);
        }

        private C3819b(ByteBuffer byteBuffer, int i) {
            switch (i) {
                case 1:
                    this.f9127a = byteBuffer.getInt();
                    this.f9129c = byteBuffer.getInt();
                    this.f9130d = byteBuffer.getInt();
                    this.f9131e = byteBuffer.getInt();
                    this.f9132f = byteBuffer.getInt();
                    this.f9133g = byteBuffer.getInt();
                    this.f9128b = byteBuffer.getInt();
                    this.f9134h = byteBuffer.getInt();
                    return;
                case 2:
                    this.f9127a = byteBuffer.getInt();
                    this.f9128b = byteBuffer.getInt();
                    this.f9129c = byteBuffer.getLong();
                    this.f9130d = byteBuffer.getLong();
                    this.f9131e = byteBuffer.getLong();
                    this.f9132f = byteBuffer.getLong();
                    this.f9133g = byteBuffer.getLong();
                    this.f9134h = byteBuffer.getLong();
                    return;
                default:
                    throw new IOException("Unexpected elf class: ".concat(String.valueOf(i)));
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.e.h$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C3820c {

        /* renamed from: a */
        public final int f9135a;

        /* renamed from: b */
        public final int f9136b;

        /* renamed from: c */
        public final long f9137c;

        /* renamed from: d */
        public final long f9138d;

        /* renamed from: e */
        public final long f9139e;

        /* renamed from: f */
        public final long f9140f;

        /* renamed from: g */
        public final int f9141g;

        /* renamed from: h */
        public final int f9142h;

        /* renamed from: i */
        public final long f9143i;

        /* renamed from: j */
        public final long f9144j;

        /* renamed from: k */
        public String f9145k;

        /* synthetic */ C3820c(ByteBuffer byteBuffer, int i, byte b) {
            this(byteBuffer, i);
        }

        private C3820c(ByteBuffer byteBuffer, int i) {
            switch (i) {
                case 1:
                    this.f9135a = byteBuffer.getInt();
                    this.f9136b = byteBuffer.getInt();
                    this.f9137c = byteBuffer.getInt();
                    this.f9138d = byteBuffer.getInt();
                    this.f9139e = byteBuffer.getInt();
                    this.f9140f = byteBuffer.getInt();
                    this.f9141g = byteBuffer.getInt();
                    this.f9142h = byteBuffer.getInt();
                    this.f9143i = byteBuffer.getInt();
                    this.f9144j = byteBuffer.getInt();
                    break;
                case 2:
                    this.f9135a = byteBuffer.getInt();
                    this.f9136b = byteBuffer.getInt();
                    this.f9137c = byteBuffer.getLong();
                    this.f9138d = byteBuffer.getLong();
                    this.f9139e = byteBuffer.getLong();
                    this.f9140f = byteBuffer.getLong();
                    this.f9141g = byteBuffer.getInt();
                    this.f9142h = byteBuffer.getInt();
                    this.f9143i = byteBuffer.getLong();
                    this.f9144j = byteBuffer.getLong();
                    break;
                default:
                    throw new IOException("Unexpected elf class: ".concat(String.valueOf(i)));
            }
            this.f9145k = null;
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m16884a(int i, int i2, String str) {
        if (i <= 0 || i > i2) {
            throw new IOException(str);
        }
    }
}
