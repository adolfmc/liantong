package group.pals.android.lib.p392ui.lockpattern.widget;

import group.pals.android.lib.p392ui.lockpattern.collect.Lists;
import group.pals.android.lib.p392ui.lockpattern.util.Randoms;
import group.pals.android.lib.p392ui.lockpattern.widget.LockPatternView;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: group.pals.android.lib.ui.lockpattern.widget.LockPatternUtils */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class LockPatternUtils {
    private static final String CLASSNAME = "group.pals.android.lib.ui.lockpattern.widget.LockPatternUtils";
    public static final String SHA1 = "SHA-1";
    public static final String UTF8 = "UTF-8";

    public static List<LockPatternView.Cell> stringToPattern(String str) {
        byte[] bytes;
        ArrayList newArrayList = Lists.newArrayList();
        try {
            for (byte b : str.getBytes("UTF-8")) {
                newArrayList.add(LockPatternView.Cell.m2012of(b / 3, b % 3));
            }
        } catch (UnsupportedEncodingException unused) {
        }
        return newArrayList;
    }

    public static String patternToString(List<LockPatternView.Cell> list) {
        if (list == null) {
            return "";
        }
        int size = list.size();
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            LockPatternView.Cell cell = list.get(i);
            bArr[i] = (byte) ((cell.getRow() * 3) + cell.getColumn());
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public static String patternToSha1(List<LockPatternView.Cell> list) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(patternToString(list).getBytes("UTF-8"));
            byte[] digest = messageDigest.digest();
            BigInteger bigInteger = new BigInteger(1, digest);
            return String.format(null, "%0" + (digest.length * 2) + "x", bigInteger).toLowerCase();
        } catch (UnsupportedEncodingException unused) {
            return "";
        } catch (NoSuchAlgorithmException unused2) {
            return "";
        }
    }

    public static ArrayList<LockPatternView.Cell> genCaptchaPattern(int i) throws IndexOutOfBoundsException {
        int i2 = i;
        if (i2 <= 0 || i2 > 9) {
            throw new IndexOutOfBoundsException("`size` must be in range [1, `LockPatternView.MATRIX_SIZE`]");
        }
        ArrayList<Integer> newArrayList = Lists.newArrayList();
        int randInt = Randoms.randInt(9);
        newArrayList.add(Integer.valueOf(randInt));
        while (newArrayList.size() < i2) {
            int i3 = randInt / 3;
            int i4 = 3;
            int i5 = randInt % 3;
            int max = Math.max(Math.max(i3, 3 - i3), Math.max(i5, 3 - i5));
            int i6 = 1;
            int i7 = -1;
            while (i6 <= max) {
                int i8 = i3 - i6;
                int i9 = i5 - i6;
                int i10 = i3 + i6;
                int i11 = i5 + i6;
                int[] randIntArray = Randoms.randIntArray(4);
                int length = randIntArray.length;
                int i12 = 0;
                int i13 = i7;
                int i14 = 0;
                while (true) {
                    if (i14 < length) {
                        switch (randIntArray[i14]) {
                            case 0:
                                if (i8 >= 0) {
                                    int i15 = 0;
                                    int[] randIntArray2 = Randoms.randIntArray(Math.max(0, i9), Math.min(3, i11 + 1));
                                    int length2 = randIntArray2.length;
                                    while (true) {
                                        if (i15 >= length2) {
                                            break;
                                        } else {
                                            int i16 = (i8 * 3) + randIntArray2[i15];
                                            if (!newArrayList.contains(Integer.valueOf(i16))) {
                                                i13 = i16;
                                                break;
                                            } else {
                                                i15++;
                                                i13 = -1;
                                            }
                                        }
                                    }
                                }
                                break;
                            case 1:
                                int i17 = i4;
                                if (i11 < i17) {
                                    int[] randIntArray3 = Randoms.randIntArray(Math.max(0, i8 + 1), Math.min(i17, i10 + 1));
                                    int length3 = randIntArray3.length;
                                    int i18 = 0;
                                    while (i18 < length3) {
                                        i13 = (randIntArray3[i18] * 3) + i11;
                                        if (!newArrayList.contains(Integer.valueOf(i13))) {
                                            break;
                                        } else {
                                            i18++;
                                            i13 = -1;
                                        }
                                    }
                                    break;
                                }
                                break;
                            case 2:
                                int i19 = i4;
                                if (i10 < i19) {
                                    int[] randIntArray4 = Randoms.randIntArray(Math.max(0, i9), Math.min(i19, i11));
                                    int length4 = randIntArray4.length;
                                    int i20 = 0;
                                    while (i20 < length4) {
                                        i13 = (i10 * 3) + randIntArray4[i20];
                                        if (!newArrayList.contains(Integer.valueOf(i13))) {
                                            break;
                                        } else {
                                            i20++;
                                            i13 = -1;
                                        }
                                    }
                                    break;
                                }
                                break;
                            case 3:
                                if (i9 >= 0) {
                                    int[] randIntArray5 = Randoms.randIntArray(Math.max(i12, i8 + 1), Math.min(i4, i10));
                                    int length5 = randIntArray5.length;
                                    int i21 = 0;
                                    while (i21 < length5) {
                                        i13 = (randIntArray5[i21] * 3) + i9;
                                        if (!newArrayList.contains(Integer.valueOf(i13))) {
                                            break;
                                        } else {
                                            i21++;
                                            i13 = -1;
                                        }
                                    }
                                    break;
                                }
                                break;
                        }
                        if (i13 >= 0) {
                            i7 = i13;
                        } else {
                            i14++;
                            i4 = 3;
                            i12 = 0;
                        }
                    } else {
                        i7 = i13;
                    }
                }
                if (i7 >= 0) {
                    break;
                }
                i6++;
                i4 = 3;
            }
            randInt = i7;
            newArrayList.add(Integer.valueOf(randInt));
            i2 = i;
        }
        ArrayList<LockPatternView.Cell> newArrayList2 = Lists.newArrayList();
        for (Integer num : newArrayList) {
            newArrayList2.add(LockPatternView.Cell.m2013of(num.intValue()));
        }
        return newArrayList2;
    }
}
