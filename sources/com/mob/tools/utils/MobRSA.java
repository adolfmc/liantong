package com.mob.tools.utils;

import com.mob.commons.C5873u;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MobRSA implements PublicMemberKeeper {

    /* renamed from: a */
    private int f15250a;

    public MobRSA(int i) {
        this.f15250a = i;
    }

    public byte[] encode(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) throws Throwable {
        DataOutputStream dataOutputStream;
        int i = this.f15250a / 8;
        int i2 = i - 11;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            int i3 = 0;
            while (bArr.length > i3) {
                try {
                    int min = Math.min(bArr.length - i3, i2);
                    byte[] m11154a = m11154a(bArr, i3, min, bigInteger, bigInteger2, i);
                    dataOutputStream.writeInt(m11154a.length);
                    dataOutputStream.write(m11154a);
                    i3 += min;
                } catch (Throwable th) {
                    th = th;
                    C5873u.m12179a(dataOutputStream, byteArrayOutputStream);
                    throw th;
                }
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            C5873u.m12179a(dataOutputStream, byteArrayOutputStream);
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            dataOutputStream = null;
        }
    }

    /* renamed from: a */
    private byte[] m11154a(byte[] bArr, int i, int i2, BigInteger bigInteger, BigInteger bigInteger2, int i3) throws Throwable {
        if (bArr.length != i2 || i != 0) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            bArr = bArr2;
        }
        BigInteger bigInteger3 = new BigInteger(m11155a(bArr, i3));
        if (bigInteger3.compareTo(bigInteger2) > 0) {
            throw new Throwable("the message must be smaller than the modulue");
        }
        return bigInteger3.modPow(bigInteger, bigInteger2).toByteArray();
    }

    /* renamed from: a */
    private byte[] m11155a(byte[] bArr, int i) throws Throwable {
        if (bArr.length > i - 1) {
            throw new Throwable("Message too large");
        }
        byte[] bArr2 = new byte[i];
        bArr2[0] = 1;
        int length = bArr.length;
        bArr2[1] = (byte) (length >> 24);
        bArr2[2] = (byte) (length >> 16);
        bArr2[3] = (byte) (length >> 8);
        bArr2[4] = (byte) length;
        System.arraycopy(bArr, 0, bArr2, i - length, length);
        return bArr2;
    }
}
