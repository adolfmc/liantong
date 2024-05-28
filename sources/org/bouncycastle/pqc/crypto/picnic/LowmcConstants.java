package org.bouncycastle.pqc.crypto.picnic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.bouncycastle.util.Exceptions;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.encoders.Hex;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class LowmcConstants {
    private static final KMatrices KMatrix_L1;
    private static final KMatrices KMatrix_L1_full;
    private static final KMatrices KMatrix_L1_inv;
    private static final KMatrices KMatrix_L3;
    private static final KMatrices KMatrix_L3_full;
    private static final KMatrices KMatrix_L3_inv;
    private static final KMatrices KMatrix_L5;
    private static final KMatrices KMatrix_L5_full;
    private static final KMatrices KMatrix_L5_inv;
    private static final KMatrices LMatrix_L1;
    private static final KMatrices LMatrix_L1_full;
    private static final KMatrices LMatrix_L1_inv;
    private static final KMatrices LMatrix_L3;
    private static final KMatrices LMatrix_L3_full;
    private static final KMatrices LMatrix_L3_inv;
    private static final KMatrices LMatrix_L5;
    private static final KMatrices LMatrix_L5_full;
    private static final KMatrices LMatrix_L5_inv;
    private static final KMatrices RConstants_L1;
    private static final KMatrices RConstants_L1_full;
    private static final KMatrices RConstants_L3;
    private static final KMatrices RConstants_L3_full;
    private static final KMatrices RConstants_L5;
    private static final KMatrices RConstants_L5_full;
    private static final int[] keyMatrices_L1;
    private static final int[] keyMatrices_L1_full;
    private static final int[] keyMatrices_L1_inv;
    private static final int[] keyMatrices_L3;
    private static final int[] keyMatrices_L3_full;
    private static final int[] keyMatrices_L3_inv;
    private static final int[] keyMatrices_L5;
    private static final int[] keyMatrices_L5_full;
    private static final int[] keyMatrices_L5_inv;
    private static final int[] linearMatrices_L1;
    private static final int[] linearMatrices_L1_full;
    private static final int[] linearMatrices_L1_inv;
    private static final int[] linearMatrices_L3;
    private static final int[] linearMatrices_L3_full;
    private static final int[] linearMatrices_L3_inv;
    private static final int[] linearMatrices_L5;
    private static final int[] linearMatrices_L5_full;
    private static final int[] linearMatrices_L5_inv;
    private static final int[] roundConstants_L1;
    private static final int[] roundConstants_L1_full;
    private static final int[] roundConstants_L3;
    private static final int[] roundConstants_L3_full;
    private static final int[] roundConstants_L5;
    private static final int[] roundConstants_L5_full;

    static {
        InputStream resourceAsStream = LowmcConstants.class.getResourceAsStream("lowmc.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            linearMatrices_L1 = ReadFromProperty(properties, "linearMatrices_L1", 40960);
            roundConstants_L1 = ReadFromProperty(properties, "roundConstants_L1", 320);
            keyMatrices_L1 = ReadFromProperty(properties, "keyMatrices_L1", 43008);
            LMatrix_L1 = new KMatrices(20, 128, 4, linearMatrices_L1);
            KMatrix_L1 = new KMatrices(21, 128, 4, keyMatrices_L1);
            RConstants_L1 = new KMatrices(0, 1, 4, roundConstants_L1);
            linearMatrices_L1_full = ReadFromProperty(properties, "linearMatrices_L1_full", 12800);
            keyMatrices_L1_full = ReadFromProperty(properties, "keyMatrices_L1_full", 12900);
            keyMatrices_L1_inv = ReadFromProperty(properties, "keyMatrices_L1_inv", 2850);
            linearMatrices_L1_inv = ReadFromProperty(properties, "linearMatrices_L1_inv", 12800);
            roundConstants_L1_full = ReadFromProperty(properties, "roundConstants_L1_full", 80);
            LMatrix_L1_full = new KMatrices(4, 129, 5, linearMatrices_L1_full);
            LMatrix_L1_inv = new KMatrices(4, 129, 5, linearMatrices_L1_inv);
            KMatrix_L1_full = new KMatrices(5, 129, 5, keyMatrices_L1_full);
            KMatrix_L1_inv = new KMatrices(1, 129, 5, keyMatrices_L1_inv);
            RConstants_L1_full = new KMatrices(4, 1, 5, roundConstants_L1_full);
            linearMatrices_L3 = ReadFromProperty(properties, "linearMatrices_L3", 138240);
            roundConstants_L3 = ReadFromProperty(properties, "roundConstants_L3", 720);
            keyMatrices_L3 = ReadFromProperty(properties, "keyMatrices_L3", 142848);
            LMatrix_L3 = new KMatrices(30, 192, 6, linearMatrices_L3);
            KMatrix_L3 = new KMatrices(31, 192, 6, keyMatrices_L3);
            RConstants_L3 = new KMatrices(30, 1, 6, roundConstants_L3);
            linearMatrices_L3_full = ReadFromProperty(properties, "linearMatrices_L3_full", 18432);
            linearMatrices_L3_inv = ReadFromProperty(properties, "linearMatrices_L3_inv", 18432);
            roundConstants_L3_full = ReadFromProperty(properties, "roundConstants_L3_full", 96);
            keyMatrices_L3_full = ReadFromProperty(properties, "keyMatrices_L3_full", 23040);
            keyMatrices_L3_inv = ReadFromProperty(properties, "keyMatrices_L3_inv", 4608);
            LMatrix_L3_full = new KMatrices(4, 192, 6, linearMatrices_L3_full);
            LMatrix_L3_inv = new KMatrices(4, 192, 6, linearMatrices_L3_inv);
            KMatrix_L3_full = new KMatrices(5, 192, 6, keyMatrices_L3_full);
            KMatrix_L3_inv = new KMatrices(1, 192, 6, keyMatrices_L3_inv);
            RConstants_L3_full = new KMatrices(4, 1, 6, roundConstants_L3_full);
            linearMatrices_L5 = ReadFromProperty(properties, "linearMatrices_L5", 311296);
            roundConstants_L5 = ReadFromProperty(properties, "roundConstants_L5", 1216);
            keyMatrices_L5 = ReadFromProperty(properties, "keyMatrices_L5", 319488);
            LMatrix_L5 = new KMatrices(38, 256, 8, linearMatrices_L5);
            KMatrix_L5 = new KMatrices(39, 256, 8, keyMatrices_L5);
            RConstants_L5 = new KMatrices(38, 1, 8, roundConstants_L5);
            linearMatrices_L5_full = ReadFromProperty(properties, "linearMatrices_L5_full", 32768);
            linearMatrices_L5_inv = ReadFromProperty(properties, "linearMatrices_L5_inv", 32768);
            roundConstants_L5_full = ReadFromProperty(properties, "roundConstants_L5_full", 128);
            keyMatrices_L5_full = ReadFromProperty(properties, "keyMatrices_L5_full", 40960);
            keyMatrices_L5_inv = ReadFromProperty(properties, "keyMatrices_L5_inv", 8160);
            LMatrix_L5_full = new KMatrices(4, 255, 8, linearMatrices_L5_full);
            LMatrix_L5_inv = new KMatrices(4, 255, 8, linearMatrices_L5_inv);
            KMatrix_L5_full = new KMatrices(5, 255, 8, keyMatrices_L5_full);
            KMatrix_L5_inv = new KMatrices(1, 255, 8, keyMatrices_L5_inv);
            RConstants_L5_full = new KMatrices(4, 1, 8, roundConstants_L5_full);
        } catch (IOException e) {
            throw Exceptions.illegalStateException("unable to load Picnic properties: " + e.getMessage(), e);
        }
    }

    LowmcConstants() {
    }

    private static KMatricesWithPointer GET_MAT(KMatrices kMatrices, int i) {
        KMatricesWithPointer kMatricesWithPointer = new KMatricesWithPointer(kMatrices);
        kMatricesWithPointer.setMatrixPointer(i * kMatricesWithPointer.getSize());
        return kMatricesWithPointer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KMatricesWithPointer KMatrix(PicnicEngine picnicEngine, int i) {
        KMatrices kMatrices;
        if (picnicEngine.stateSizeBits == 128) {
            kMatrices = KMatrix_L1;
        } else if (picnicEngine.stateSizeBits == 129) {
            kMatrices = KMatrix_L1_full;
        } else if (picnicEngine.stateSizeBits == 192) {
            kMatrices = picnicEngine.numRounds == 4 ? KMatrix_L3_full : KMatrix_L3;
        } else if (picnicEngine.stateSizeBits == 255) {
            kMatrices = KMatrix_L5_full;
        } else if (picnicEngine.stateSizeBits != 256) {
            return null;
        } else {
            kMatrices = KMatrix_L5;
        }
        return GET_MAT(kMatrices, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KMatricesWithPointer KMatrixInv(PicnicEngine picnicEngine) {
        KMatrices kMatrices;
        if (picnicEngine.stateSizeBits == 129) {
            kMatrices = KMatrix_L1_inv;
        } else if (picnicEngine.stateSizeBits == 192 && picnicEngine.numRounds == 4) {
            kMatrices = KMatrix_L3_inv;
        } else if (picnicEngine.stateSizeBits != 255) {
            return null;
        } else {
            kMatrices = KMatrix_L5_inv;
        }
        return GET_MAT(kMatrices, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KMatricesWithPointer LMatrix(PicnicEngine picnicEngine, int i) {
        KMatrices kMatrices;
        if (picnicEngine.stateSizeBits == 128) {
            kMatrices = LMatrix_L1;
        } else if (picnicEngine.stateSizeBits == 129) {
            kMatrices = LMatrix_L1_full;
        } else if (picnicEngine.stateSizeBits == 192) {
            kMatrices = picnicEngine.numRounds == 4 ? LMatrix_L3_full : LMatrix_L3;
        } else if (picnicEngine.stateSizeBits == 255) {
            kMatrices = LMatrix_L5_full;
        } else if (picnicEngine.stateSizeBits != 256) {
            return null;
        } else {
            kMatrices = LMatrix_L5;
        }
        return GET_MAT(kMatrices, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KMatricesWithPointer LMatrixInv(PicnicEngine picnicEngine, int i) {
        KMatrices kMatrices;
        if (picnicEngine.stateSizeBits == 129) {
            kMatrices = LMatrix_L1_inv;
        } else if (picnicEngine.stateSizeBits == 192 && picnicEngine.numRounds == 4) {
            kMatrices = LMatrix_L3_inv;
        } else if (picnicEngine.stateSizeBits != 255) {
            return null;
        } else {
            kMatrices = LMatrix_L5_inv;
        }
        return GET_MAT(kMatrices, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KMatricesWithPointer RConstant(PicnicEngine picnicEngine, int i) {
        KMatrices kMatrices;
        if (picnicEngine.stateSizeBits == 128) {
            kMatrices = RConstants_L1;
        } else if (picnicEngine.stateSizeBits == 129) {
            kMatrices = RConstants_L1_full;
        } else if (picnicEngine.stateSizeBits == 192) {
            kMatrices = picnicEngine.numRounds == 4 ? RConstants_L3_full : RConstants_L3;
        } else if (picnicEngine.stateSizeBits == 255) {
            kMatrices = RConstants_L5_full;
        } else if (picnicEngine.stateSizeBits != 256) {
            return null;
        } else {
            kMatrices = RConstants_L5;
        }
        return GET_MAT(kMatrices, i);
    }

    private static int[] ReadFromProperty(Properties properties, String str, int i) {
        byte[] decode = Hex.decode(removeCommas(properties.getProperty(str)));
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < decode.length / 4; i2++) {
            iArr[i2] = Pack.littleEndianToInt(decode, i2 * 4);
        }
        return iArr;
    }

    private static byte[] removeCommas(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != str.length(); i++) {
            if (str.charAt(i) != ',') {
                byteArrayOutputStream.write(str.charAt(i));
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
