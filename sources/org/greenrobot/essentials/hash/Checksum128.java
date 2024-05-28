package org.greenrobot.essentials.hash;

import java.math.BigInteger;
import java.util.zip.Checksum;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface Checksum128 extends Checksum {
    BigInteger getValueBigInteger();

    byte[] getValueBytesBigEndian();

    byte[] getValueBytesLittleEndian();

    String getValueHexString();

    long getValueHigh();
}
